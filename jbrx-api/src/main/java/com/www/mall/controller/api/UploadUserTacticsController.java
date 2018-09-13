package com.www.mall.controller.api;

import com.gavin.model.Page;
import com.www.mall.common.base.BaseController;
import com.www.mall.common.bean.RC;
import com.www.mall.common.bean.Ret;
import com.www.mall.common.shiro.principal.User;
import com.www.mall.common.utils.WebServiceUtils;
import com.www.mall.common.utils.http.HttpRequest;
import com.www.mall.user.dto.UploadUserPlatform;
import com.www.mall.user.dto.UploadUserTactics;
import com.www.mall.user.interf.UploadUserTacticsService;
import com.www.mall.user.interf.UsersService;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@RequestMapping(value = "/app/uut")
public class UploadUserTacticsController extends BaseController {
    @JbootrpcService
    private UploadUserTacticsService uploadUserTacticsService;
    @JbootrpcService
    private UsersService usersService;
    /**
     * 上传策略用户 新增/修改
     */
    public void followUploadUserPlatform(){
        result(uploadUserTacticsService.followUploadUserTactics(getParamToLong("userId"),getParamToLong("tacticsId")));
    }

    /**
     * 获取上传策略用户列表 可根据用户ID 和 策略名称 查询
     */
    public void getUploadUserTacticsList(){
        Page<UploadUserTactics> page=uploadUserTacticsService.getUploadUserTacticsList(pageNumber(),pageSize(),getParamLong("userId"),getParam("tacticsName"));
        result(page);
    }

    /**
     * 用户注册到平台
     */
    public void registerUserToTactics(){
        //获取接口地址
        String httpUrl="https://www.sojson.com/open/api/weather/json.shtml";
        //获取用户信息
        User user=usersService.queryUsersById(getUserId());
        if(user==null){
            result(RC.REQUEST_FAIL, "查询用户信息失败");
            return;
        }
        //平台ID
        Long platformId=getParamToLong("platformId");
        //设置参数
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cardPic",user.getPic());//头像
        params.put("bank",user); //银行
        params.put("bankcardOwner",user); //持卡人
        params.put("branch",user); //支行
        params.put("bankNum",user); //用户卡号
        params.put("userName",user); //用户名
        params.put("userPhone",user); //手机
        params.put("userEmail",user); //邮箱
        params.put("userPassword",user); //密码
        params.put("cardNum",user); //身份证
        params.put("city","%E5%8C%97%E4%BA%AC");
        //调用接口
        Ret br=WebServiceUtils.sendHttp(httpUrl,params,HttpRequest.METHOD_GET);
        //返回结果
        result(br);
    }
}
