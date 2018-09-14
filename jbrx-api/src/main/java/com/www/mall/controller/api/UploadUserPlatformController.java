package com.www.mall.controller.api;

import com.gavin.model.Page;
import com.www.mall.common.base.BaseController;
import com.www.mall.common.bean.RC;
import com.www.mall.common.constants.RxConstants;
import com.www.mall.user.dto.UploadUserPlatform;
import com.www.mall.user.interf.UploadUserPlatformService;
import com.www.mall.user.interf.UserService;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

import static com.www.mall.common.bean.Ret.result;

@RequestMapping( value =    "/app/uup")
public class UploadUserPlatformController extends BaseController {

    @JbootrpcService
    private UploadUserPlatformService uploadUserPlatformService;
    @JbootrpcService
    private UserService usersService;

    /**
     * 上传平台用户 新增/修改
     */
    public void followUploadUserPlatform(){
        result(uploadUserPlatformService.followUploadUserPlatform(getParamToLong("userId"),getParamToLong("platformId")));
    }

    /**
     * 上传平台用户 新增/修改
     * 之所以不用传参数，是因为在做 利万平台注册的时候，将平台ID保存到session中了。
     */
    public void followUploadUserPlatformNotParam(){
        // 获取当前登录用户ID
        Long userId = getUserId();
        if (userId == null) {
            result(RC.REQUEST_FAIL, "当前用户不存在，请先登录再进行修改.");
            return;
        }
        if(this.getSession().getAttribute(RxConstants.LW_LOGIN_TOKEN)==null){
            result(RC.BUSINESS_FAIL, "请先进行利万系统登录认证.");
            return;
        }
        if(this.getSession().getAttribute(RxConstants.LW_REGISTER_PLATID)==null){
            result(RC.BUSINESS_FAIL, "请先进行利万系统登录认证.");
            return;
        }
        Long platId=Long.parseLong(this.getSession().getAttribute(RxConstants.LW_REGISTER_PLATID).toString());
        result(uploadUserPlatformService.followUploadUserPlatform(userId,platId));
    }

    /**
     * 获取上传平台用户列表 可根据用户ID 和 平台名称 查询
     */
    public void getUploadUserPlatformList(){
        Page<UploadUserPlatform> page=uploadUserPlatformService.getUploadUserPlatformList(pageNumber(),pageSize(),getParamLong("userId"),getParam("platformName"));
        result(page);
    }


}
