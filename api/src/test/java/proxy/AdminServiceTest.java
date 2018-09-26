package proxy;

import com.gavin.model.Page;
import com.gavin.model.Response;
import com.sitexa.common.shiro.principal.AdminPrincipal;
import com.sitexa.facade.interfaces.AdminService;
import com.xiaoleilu.hutool.util.RandomUtil;
import conf.BaseTest;
import io.jboot.core.rpc.annotation.JbootrpcService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

public class AdminServiceTest extends BaseTest {

   /* @JbootrpcService
    AdminService adminService;

    @Test
    public void getAdminByUsername() {
        AdminPrincipal admin = adminService.getAdminsByUserName("18673107430");
        result(admin);
    }

    @Test
    public void getAdminList() {
        Page<AdminPrincipal> pages = adminService.getAdminsList(0, 10, "admin");

        result(pages);
    }

    @Test
    public void insertAdmin() {
        AdminPrincipal admin = new AdminPrincipal();
        admin.setUserName("admin10");
        admin.setPlateformId(1L);
        admin.setAvatar("avatar");

        String salt = RandomUtil.randomUUID();
        String password = new Md5Hash("123456", salt, 3).toString();
        admin.setSalt(salt);
        admin.setPassword(password);

        Response response = adminService.insertAdmin(admin);
        result(response);
    }

    @Test
    public void updateAdmin() {
        AdminPrincipal admin = adminService.getAdminsByUserName("admin");

        admin.setUserName("xnpeng");

        Response response = adminService.updateAdmin(admin);
        result(response);
    }

    @Test
    public void updatePassword() {
        AdminPrincipal admin = adminService.getAdminsByUserName("admin");

        admin.setPassword("pop007");
        Response response = adminService.updateAdminPassword(admin);
        result(response);
    }

    @Test
    public void delAdmin() {
        AdminPrincipal admin = adminService.getAdminsByUserName("18673107430");
        Response response = adminService.delAdmin(admin);
        result(response);
    }

    @Test
    public void isUsedAdmin(){
        AdminPrincipal admin = adminService.getAdminsByUserName("admin");

        Response response = adminService.isUsedAdmin(admin);

        result(response);
    }*/
}
