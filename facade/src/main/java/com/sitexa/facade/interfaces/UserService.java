package com.sitexa.facade.interfaces;

import com.gavin.model.Page;
import com.gavin.model.Response;
import com.gavin.schema.service.User;
import com.sitexa.common.shiro.principal.UserPrincipal;

public interface UserService {
    public final static String service = UserService.class.getSimpleName();

    public Response queryUserByUsersName(String mobilePhone);

    public Response saveUser(UserPrincipal user);

    public Response queryUserNameExist(String userName);

    public UserPrincipal queryUserById(long id);

    public Response updatePassword(long userId, String newPassword, String newSalt);

    public Response updateUserInfo(String columnName, String columnValue, String columnStr, Long userId);

    public Page<User> getUserList(int pageNumber, int pageSize, String mobilePhone);

    public Response isUsedUser(User user);

    public Response delUser(User user);
}
