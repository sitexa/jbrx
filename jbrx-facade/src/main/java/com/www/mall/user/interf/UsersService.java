package com.www.mall.user.interf;

import com.gavin.model.Response;
import com.www.mall.common.shiro.principal.User;

public interface UsersService {
    public final static String service=UsersService.class.getSimpleName();

    public Response queryUsersByUsersName(String mobilePhone);

    public Response saveUsers(User user);

    public Response queryUserNameExist(String userName);

    public User queryUsersById(long id);

    public Response updatePassword(long userId,String newPassword, String newSalt);


    /**
     * 更新银行信息
     * @param user
     * @return
     */
    public Response updateBankInfo(User user);

    /**
     * 更新证件信息
     * @param user
     * @return
     */
    public Response updateUserIdcardInfo(User user);

    /**
     * 修改用户表 公共方法
     * @param columnName
     * @param columnValue
     * @param userId
     * @return
     */
    public Response updateUserInfo(String columnName,String columnValue,String columnStr,Long userId);

    /**
     * 更新地址
     * @param user
     * @return
     */
    public Response updateUserAddress(User user);

    /**
     * 删除银行卡信息
     * @param userId
     * @return
     */
    public Response deleteBankInfo(Long userId);



}
