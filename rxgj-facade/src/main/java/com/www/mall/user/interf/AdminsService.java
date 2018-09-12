package com.www.mall.user.interf;

import com.gavin.model.Page;
import com.gavin.model.Response;
import com.www.mall.common.shiro.principal.Admins;

public interface AdminsService {
    public final static String service=AdminsService.class.getSimpleName();

    public Admins getAdminsByUserName(String adminsName);

    public Page<Admins> getAdminsList(int pageNumber,int pageSize,String userName);

    public Response insertAdmin(Admins admins);

    public Response updateAdmin(Admins admins);

    public Response isUsedAdmin(Admins admins);

    public Response delAdmin(Admins admins);

    public Response updateAdminPassword(Admins admins);
}
