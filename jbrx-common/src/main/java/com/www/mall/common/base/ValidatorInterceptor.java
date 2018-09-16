package com.www.mall.common.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.www.mall.common.bean.Ploy;
import com.www.mall.common.bean.RC;
import com.www.mall.common.bean.Ret;
import com.www.mall.common.utils.ParamUtil;
import com.www.mall.common.utils.RegexUtils;
import com.www.mall.common.validator.IVRule;
import com.www.mall.common.validator.VRule;
import com.www.mall.common.validator.VRules;
import com.www.mall.common.validator.Validator;
import com.www.mall.common.validator.VGroup;
import com.www.mall.common.validator.VRenderType;
import com.www.mall.common.validator.ValidatorRule;
import com.www.mall.common.validator.VType;
import com.www.mall.common.validator.Version;
import com.xiaoleilu.hutool.util.IdcardUtil;
import com.xiaoleilu.hutool.util.StrUtil;

import io.jboot.web.controller.JbootController;

/**
 * ------------------------------
 * 参数验证拦截器
 * ------------------------------
 *
 * @author wdm（l311576@sina.com）  @date 2018年5月31日
 * @version 1.0
 */
public class ValidatorInterceptor implements Interceptor {

    @Override
    public void intercept(Invocation inv) {
        Version version = inv.getMethod().getAnnotation(Version.class);
        if (version != null && !verifyVersion(inv, version)) {
            return;
        }

        Validator validator = inv.getMethod().getAnnotation(Validator.class);
        if (validator != null && !validate(inv, validator)) {
            return;
        }
        inv.invoke();
    }

    private boolean verifyVersion(Invocation inv, Version version) {
        int vers = version.version();
        String message = StringUtils.isBlank(version.message()) ? "该功能已升级，如要继续使用请升级app到最新版本" : version.message();
        Controller controller = inv.getController();

        if (vers > -1) {
            int verifyVersion = -1;
            String versionStr = controller.getHeader("version");//验证版本号
            if (versionStr != null) {
                try {
                    verifyVersion = Integer.valueOf(versionStr);
                } catch (Exception e) {
                    //版本信息错误  必须是int类型数字
                    e.printStackTrace();
                }
            }

            if (verifyVersion < vers) {
                renderError(controller, VRenderType.json, "", RC.VERSION_FAIL, message);
                return false;
            }

        }
        return true;
    }

    /**
     * 验证JSON格式的数据
     *
     * @param inv
     * @param validator
     * @return
     */
    private boolean validate(Invocation inv, Validator validator) {
        VRenderType vRenderType = validator.render();
        VGroup group = validator.group();
        Controller controller = inv.getController();
        Class<?> clazz = validator.bean();
        String validatorBeanName = clazz.getName();

        //初始化校验规则
        initValidatorRule(validatorBeanName, clazz);
        List<ValidatorRule> list = IVRule.BEAN_VALIDATOT_RULE.get(validatorBeanName);

        if (list == null) {//没有验证规则
            return true;
        }

        JSONObject jsonObject = json(controller.getPara("param"), controller.getHeader("ploy"));
        String beanName = StrUtil.isBlank(validator.beanName()) ? "" : validator.beanName() + ".";
        for (int i = 0; i < list.size(); i++) {//循环每一条数据规则
            ValidatorRule validatorRule = list.get(i);
            List<VGroup> groups = validatorRule.getValidatorGroups();
            if (groups.contains(group)) {//包含分组-需要验证该组数据
                String field = beanName + validatorRule.getValidatorField();
                String message = validatorRule.getMessage();
                List<VType> validatorRules = validatorRule.getValidatorTypes();
                for (int j = 0; j < validatorRules.size(); j++) {
                    VType vType = validatorRules.get(j);

                    String value = jsonObject == null ? null : jsonObject.getString(field);//controller.getPara(field);

                    if (!validate(vType, value)) {
                        renderError(controller, vRenderType, validator.uri(), RC.PARAM_FAIL, message);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 初始化验证规则
     *
     * @param isInit
     * @param bean
     */
    private void initValidatorRule(String validatorBeanName, Class<?> clazz) {
        Boolean isInit = IVRule.IS_INIT_VALIDATOT_RULE.get(validatorBeanName);
        if (isInit != null && isInit) {//是否已经初始化
            return;
        }
        VRules vRules = clazz.getAnnotation(VRules.class);
        if (vRules != null) {
            VRule[] rules = vRules.rules();
            if (rules.length > 0) {
                List<ValidatorRule> validatorRules = new ArrayList<ValidatorRule>();
                for (int i = 0; i < rules.length; i++) {
                    VRule vRule = rules[i];
                    String attrName = vRule.attrName();
                    String message = vRule.message();
                    VGroup[] validatorGroups = vRule.groups();
                    VType[] validatorTypes = vRule.types();
                    validatorRules.add(ValidatorRule.bulid(attrName, message, ValidatorRule.group(validatorGroups), ValidatorRule.type(validatorTypes)));
                }
                IVRule.BEAN_VALIDATOT_RULE.put(validatorBeanName, validatorRules);
            }
        }
        IVRule.IS_INIT_VALIDATOT_RULE.put(validatorBeanName, true);
    }

    protected JSONObject json(String json, String ploy) {
        if (Ploy.ENCRYPT.equals(ploy)) {//参数解密
            json = ParamUtil.decryptParam(json);
            if (json == null) {
                return null;
            }
        }
        return JSON.parseObject(json);
    }

    /**
     * 有需要再追加
     *
     * @param vType
     * @param value
     * @return
     */
    private boolean validate(VType vType, String value) {
        if (vType == VType.notnull) {
            return com.xiaoleilu.hutool.lang.Validator.isNotEmpty(value);
        } else if (vType == VType.email) {
            return com.xiaoleilu.hutool.lang.Validator.isEmail(value);
        } else if (vType == VType.idcard) {
            return IdcardUtil.isvalidCard18(value);
        } else if (vType == VType.ne_email) {
            if (com.xiaoleilu.hutool.lang.Validator.isNotEmpty(value)) {
                return true;
            }
            return RegexUtils.checkEmail(value);
        } else if (vType == VType.ne_idcard) {
            if (com.xiaoleilu.hutool.lang.Validator.isNotEmpty(value)) {
                return true;
            }
            return IdcardUtil.isvalidCard18(value);
        } else if (vType == VType.mobilePhone) {
            return com.xiaoleilu.hutool.lang.Validator.isMobile(value) && value.length() == 11;
        } else if (vType == VType.password) {
            return value != null && value.length() > 5;
        } else if (vType == VType.number) {
            return com.xiaoleilu.hutool.lang.Validator.isNumber(value);
        }

        return true;
    }

    private void renderError(Controller controller, VRenderType vRenderType, String redirect, RC result, String message) {
        if (controller instanceof JbootController) {
            if (vRenderType == VRenderType.json) {
                controller.renderJson(Ret.paramFail(message));
            } else if (vRenderType == VRenderType.redirect) {
                if (StringUtils.isNotBlank(redirect)) {
                    controller.redirect(redirect);
                }
//            	 ((JbootController) controller).setFlashAttr("message", message);
//            	 ((JbootController) controller).setFlashAttr("result", result);
                controller.renderJson(Ret.result(result.getState(), message));
            } else if (vRenderType == VRenderType.render) {
                controller.keepPara();
                if (StringUtils.isNotBlank(redirect)) {
                    controller.render(redirect);
                }
//            	 ((JbootController) controller).setFlashAttr("message", message);
//            	 ((JbootController) controller).setFlashAttr("result", result);
                controller.renderJson(Ret.result(result.getState(), message));
            } else {
                if (StringUtils.isNotBlank(redirect)) {
                    controller.redirect(redirect);
                }

//            	 ((JbootController) controller).setFlashAttr("message", message);
//            	 ((JbootController) controller).setFlashAttr("result", result);
                controller.renderJson(Ret.result(result.getState(), message));
            }
        }
    }

}
