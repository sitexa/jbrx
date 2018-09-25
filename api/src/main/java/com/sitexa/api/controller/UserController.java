package com.sitexa.api.controller;

import com.gavin.model.Response;
import com.jfinal.aop.Before;
import com.sitexa.api.interceptor.AuthInterceptor;
import com.sitexa.common.base.BaseController;
import com.sitexa.common.base.BaseImgUrl;
import com.sitexa.common.bean.Ret;
import com.sitexa.common.bean.UserVo;
import com.sitexa.common.constants.RC;
import com.sitexa.common.shiro.principal.UserPrincipal;
import com.sitexa.common.utils.RegexUtils;
import com.sitexa.common.utils.StringUtils;
import com.sitexa.common.validator.VGroup;
import com.sitexa.common.validator.Validator;
import com.sitexa.facade.interfaces.UserService;
import com.sitexa.facade.vo.Register;
import com.sitexa.msg.interfaces.MessageService;
import com.sitexa.msg.vo.MessageRecord;
import com.sitexa.msg.vo.TemplateType;
import com.xiaoleilu.hutool.util.RandomUtil;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;
import org.apache.shiro.crypto.hash.Md5Hash;


@RequestMapping("/api/user")
public class UserController extends BaseController {
    @JbootrpcService
    private UserService usersService;
    @JbootrpcService
    private MessageService messageService;

    /**
     * 注册
     */
    @Validator(bean = Register.class, group = VGroup.save)
    public void register() {
        //1.获取注册信息
        Register register = getBeanByJsonParam(Register.class);

        //2.验证短信是否匹配
        Ret ret = messageService.verifyCode(register.getMessageId(), register.getVerifyCode());
        if (!ret.success()) {
            result(ret);
            return;
        }

        //3.保存用户
        String password = register.getPassword();
        String salt = RandomUtil.simpleUUID();
        String saltPassword = new Md5Hash(password, salt, 3).toString();
        UserPrincipal users = new UserPrincipal();
        users.setMobilePhone(register.getMobilePhone());
        users.setPassword(saltPassword);
        users.setSalt(salt);
        users.setNickName(register.getNickName());

        Response response = usersService.saveUser(users);
        result(response);
    }

    /**
     * 注册-发送验证码
     */
    @Validator(bean = Register.class, group = VGroup.excutor)
    public void sendRegisterCode() {

        //1.获取注册信息
        Register users = getBeanByJsonParam(Register.class);
        String mobilePhone = users.getMobilePhone();

        //2.判断用户是否存在
        Response response = usersService.queryUserNameExist(mobilePhone);
        if (response.fail()) {
            result(RC.REQUEST_FAIL, "用户服务暂不可用,请稍后再试");
            return;
        }
        Long userCount = response.getLong("userCount");
        if (userCount != null && userCount > 0) {
            result(RC.BUSINESS_FAIL, "当前手机号码已被注册");
            return;
        }

        //3.发送短信
        MessageRecord messageRecord = new MessageRecord();
        messageRecord.setServiceType(TemplateType.REGISTER.name());//这个一定要按业务填写，否则有问题
        messageRecord.setContent(TemplateType.REGISTER.name());
        messageRecord.setTitle("发送注册短信验证码");
        messageRecord.setMessageType(1);
        messageRecord.setReceiver(mobilePhone);
        messageRecord.setMessageSn(RandomUtil.simpleUUID());

        result(messageService.sendCode(messageRecord));
    }

    /**
     * 修改密码-需要登录
     */
    @Before(AuthInterceptor.class)
    public void updatePassword() {
        String password = getParam("password");
        String oldPassword = getParam("oldPassword");
        if (password == null) {
            result(RC.PARAM_FAIL, "密码不能为空");
            return;
        }
        if (oldPassword == null) {
            result(RC.PARAM_FAIL, "原密码不能为空");
            return;
        }

        UserPrincipal user = usersService.queryUserById(getUserId());
        if (user == null) {
            result(RC.REQUEST_FAIL, "查询用户信息失败");
            return;
        }
        String currentPassword = user.getPassword();
        String verfyPassword = new Md5Hash(oldPassword, user.getSalt(), 3).toString();
        if (!currentPassword.equals(verfyPassword)) {
            result(RC.BUSINESS_FAIL, "原密码错误,请重新输入");
            return;
        }

        String newSalt = RandomUtil.simpleUUID();
        String newPassword = new Md5Hash(password, newSalt, 3).toString();
        result(usersService.updatePassword(user.getId(), newPassword, newSalt));
    }


    /**
     * 忘记密码重设密码
     */
    public void resetPassword() {
        String mobilePhone = getParam("mobilePhone");
        String password = getParam("password");
        String verifyCode = getParam("verifyCode");
        String messageId = getParam("messageId");
        if (password == null) {
            result(RC.PARAM_FAIL, "密码不能为空");
            return;
        }
        if (verifyCode == null || verifyCode.length() != 6) {
            result(RC.PARAM_FAIL, "请输入正确的短信验证码");
            return;
        }
        if (messageId == null) {
            result(RC.PARAM_FAIL, "短信验证码失败");
            return;
        }
        Response response = usersService.queryUserByUsersName(mobilePhone);
        if (response == null || response.fail()) {
            result(RC.PARAM_FAIL, "查询用户信息失败，请重试");
            return;
        }

        //2.验证短信是否匹配
        Ret ret = messageService.verifyCode(messageId, verifyCode);
        if (!ret.success()) {
            result(ret);
            return;
        }

        String newSalt = RandomUtil.simpleUUID();
        String newPassword = new Md5Hash(password, newSalt, 3).toString();
        result(usersService.updatePassword(response.getLong("id"), newPassword, newSalt));
    }

    /**
     * 未登陆忘记密码-发送短信
     */
    public void sendCodeByForgetPassword() {
        String mobilePhone = getParam("mobilePhone");
//		//1.获取注册信息
        if (mobilePhone == null || mobilePhone.length() != 11) {
            result(RC.BUSINESS_FAIL, "请输入正确的手机号码");
            return;
        }

        //2.判断用户是否存在
        Response response = usersService.queryUserNameExist(mobilePhone);
        if (response.fail()) {
            result(RC.REQUEST_FAIL, "用户服务暂不可用,请稍后再试");
            return;
        }
        Long userCount = response.getLong("userCount");
        if (userCount == 0) {
            result(RC.BUSINESS_FAIL, "请输入正确的手机号码");
            return;
        }

        //3.发送短信
        MessageRecord messageRecord = new MessageRecord();
        messageRecord.setServiceType(TemplateType.UPDATE_PWD.name());//这个一定要按业务填写，否则有问题
        messageRecord.setContent(TemplateType.UPDATE_PWD.name());
        messageRecord.setTitle("发送忘记密码短信验证码");
        messageRecord.setMessageType(1);
        messageRecord.setReceiver(mobilePhone);
        messageRecord.setMessageSn(RandomUtil.simpleUUID());

        result(messageService.sendCode(messageRecord));
    }

    /**
     * 已登录忘记密码-发送短信
     */
    public void sendCodeByLoginUserForgetPassword() {

        UserVo user = getUser();
        String mobilePhone = null;
        if (user == null) {
            mobilePhone = getParam("mobilePhone");
        } else {
            mobilePhone = user.getMobilePhone();
        }

        //3.发送短信
        MessageRecord messageRecord = new MessageRecord();
        messageRecord.setServiceType(TemplateType.UPDATE_PWD.name());//这个一定要按业务填写，否则有问题
        messageRecord.setContent(TemplateType.UPDATE_PWD.name());
        messageRecord.setTitle("发送忘记密码短信验证码");
        messageRecord.setMessageType(1);
        messageRecord.setReceiver(mobilePhone);
        messageRecord.setMessageSn(RandomUtil.simpleUUID());

        result(messageService.sendCode(messageRecord));
    }


    /**
     * 查询用户信息
     */
    public void getUserInfo() {
        Long userId = getUserId();
        if (userId == null) {
            result(RC.REQUEST_FAIL, "用户未登录，请先登录.");
            return;
        }
        UserPrincipal user = usersService.queryUserById(userId);
        if (user == null) {
            result(RC.REQUEST_FAIL, "查询用户信息失败");
            return;
        }
        result(Ret.resultFile(user, BaseImgUrl.fastUrl + BaseImgUrl.IMG_BASE));
    }


    /**
     * 修改用户信息  公共修改非法
     */
    public void updateUserInfo() {
        Long userId = getUserId();
        if (userId == null) {
            result(RC.REQUEST_FAIL, "用户未登录，请先登录.");
            return;
        }
        String columnName = getParam("columnName");   //字段名称
        String columnValue = getParam("columnValue");   //字段值

        if (StringUtils.isBlank(columnName)) {
            result(RC.BUSINESS_FAIL, "参数columnName不能为空.");
            return;
        }
        //校验字段信息
        StringBuffer columnStr = new StringBuffer();
        String retMsg = getColumnStr(columnName, columnStr, columnValue);
        if ("".equals(columnStr.toString())) {
            result(RC.BUSINESS_FAIL, "非法参数名.");
            return;
        }
        if (StringUtils.isBlank(columnValue)) {
            result(RC.BUSINESS_FAIL, columnStr + "不能为空.");
            return;
        }
        if (StringUtils.isNotEmpty(retMsg)) {
            result(RC.BUSINESS_FAIL, retMsg);
            return;
        }
        result(usersService.updateUserInfo(columnName, columnValue, columnStr.toString(), userId));
    }

    /**
     * 判断user 字段名称是否正确（需要和数据库user表里面的字段名称一致）
     * 校验参数的值是否满足条件
     *
     * @param columnName
     * @param columnStr
     * @return
     */
    private String getColumnStr(String columnName, StringBuffer columnStr, String columnValue) {
        String retMsg = "";
        switch (columnName) {
            case "pic":
                columnStr.append("用户头像");
                break;
            case "nickName":
                columnStr.append("昵称");
                break;
            case "realName":
                columnStr.append("真实姓名");
                break;
            case "sex":
                columnStr.append("性别");
                break;
            case "mobilePhone":
                columnStr.append("手机");
                if (!RegexUtils.checkMobile(columnValue)) {
                    retMsg = "请输入正确的手机号码.";
                }
                break;
            case "idcard":
                columnStr.append("证件号码");
                break;
            case "idcardImg":
                columnStr.append("证件照片");
                break;
            case "bank":
                columnStr.append("开户银行");
                break;
            case "bankch":
                columnStr.append("支行信息");
                break;
            case "bankNum":
                columnStr.append("银行卡号");
                if (!RegexUtils.checkBankCard(columnValue)) {
                    retMsg = "请输入正确的银行卡号.";
                }
                break;
            case "bankImg":
                columnStr.append("银行卡照片");
                break;
            case "bankcardOwner":
                columnStr.append("持卡人姓名");
                break;
            case "mobile":
                columnStr.append("绑定手机号");
                if (!RegexUtils.checkMobile(columnValue)) {
                    retMsg = "请输入正确的手机号码.";
                }
                break;
            case "country":
                columnStr.append("国籍");
                break;
            case "addr":
                columnStr.append("详细地址");
                break;
            case "email":
                columnStr.append("邮箱");
                if (!RegexUtils.checkEmail(columnValue)) {
                    retMsg = "请输入正确的邮箱账号.";
                }
                break;
            case "birthday":
                columnStr.append("生日");
                if (!RegexUtils.checkBirthday(columnValue)) {
                    retMsg = "请输入正确的日期.";
                }
                break;
            case "pro":
                columnStr.append("省");
                break;
            case "city":
                columnStr.append("市");
                break;
            case "area":
                columnStr.append("区");
                break;
            case "idcardIsA":
                columnStr.append("身份证是否认证");
                if (!RegexUtils.checkDigit(columnValue)) {
                    retMsg = "请输入正确认证值，正确的值为1或者2.";
                }
                if (!"1".equals(columnValue) || !"2".equals(columnValue)) {
                    retMsg = "正确的值为1或者2.";
                }
                break;
            case "bankIsA":
                columnStr.append("银行卡是否认证");
                if (!RegexUtils.checkDigit(columnValue)) {
                    retMsg = "请输入正确认证值.";
                }
                if (!"1".equals(columnValue) || !"2".equals(columnValue)) {
                    retMsg = "正确的值为1或者2.";
                }
                break;
            default:
                columnStr.append("");
        }
        return retMsg;
    }

}
