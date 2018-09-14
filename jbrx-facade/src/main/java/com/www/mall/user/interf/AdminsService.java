package com.www.mall.user.interf;

import com.gavin.model.Page;
import com.gavin.model.Response;
import com.www.mall.common.shiro.principal.AdminPrincipal;

public interface AdminsService {
    public final static String service=AdminsService.class.getSimpleName();

    public AdminPrincipal getAdminsByUserName(String adminsName);

    public Page<AdminPrincipal> getAdminsList(int pageNumber, int pageSize, String userName);

    public Response insertAdmin(AdminPrincipal admins);

    public Response updateAdmin(AdminPrincipal admins);

    public Response isUsedAdmin(AdminPrincipal admins);

    public Response delAdmin(AdminPrincipal admins);

    public Response updateAdminPassword(AdminPrincipal admins);
}
