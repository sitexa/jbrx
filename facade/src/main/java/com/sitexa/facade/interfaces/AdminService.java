package com.sitexa.facade.interfaces;

import com.gavin.model.Page;
import com.gavin.model.Response;
import com.sitexa.common.shiro.principal.AdminPrincipal;

public interface AdminService {
    public final static String service=AdminService.class.getSimpleName();

    public AdminPrincipal getAdminsByUserName(String adminsName);

    public Page<AdminPrincipal> getAdminsList(int pageNumber, int pageSize, String userName);

    public Response insertAdmin(AdminPrincipal admins);

    public Response updateAdmin(AdminPrincipal admins);

    public Response isUsedAdmin(AdminPrincipal admins);

    public Response delAdmin(AdminPrincipal admins);

    public Response updateAdminPassword(AdminPrincipal admins);
}
