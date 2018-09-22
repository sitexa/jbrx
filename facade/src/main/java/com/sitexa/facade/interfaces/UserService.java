package com.sitexa.facade.interfaces;

import com.gavin.model.Response;
import com.sitexa.common.shiro.principal.UserPrincipal;

public interface UserService {
    public final static String service= UserService.class.getSimpleName();

    public Response queryUsersByUsersName(String mobilePhone);

    public Response saveUsers(UserPrincipal user);

    public Response queryUserNameExist(String userName);

    public UserPrincipal queryUsersById(long id);

    public Response updatePassword(long userId, String newPassword, String newSalt);


    /**
     * 修改用户表 公共方法
     * @param columnName
     * @param columnValue
     * @param userId
     * @return
     */
    public Response updateUserInfo(String columnName, String columnValue, String columnStr, Long userId);

}
