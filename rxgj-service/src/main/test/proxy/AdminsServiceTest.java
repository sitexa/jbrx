package proxy;

import com.www.mall.common.shiro.principal.Admins;
import com.www.mall.service.user.AdminsService;
import conf.BaseTest;
import org.junit.Test;

public class AdminsServiceTest extends BaseTest {
    AdminsService adminsService;

    public AdminsServiceTest() {
        adminsService = new com.www.mall.service.user.AdminsService();
    }

    @Test
    public void addAdmins() {
        Admins admins = new Admins();

        admins.setUserName("admin5");
        admins.setPlateformId(1L);
        admins.setAvatar("");
        String password = "123456";
        admins.setPassword(password);

        result(adminsService.insertAdmin(admins));
    }

    @Test
    public void getAdminsByUsername() {
        String username = "admin";
        result(adminsService.getAdminsByUserName(username));
    }

    @Test
    public void getAdminsList() {
        result(adminsService.getAdminsList(0, 10, "admin"));
    }

    @Test
    public void isUserAdmin() {
        Admins admins = new Admins();
        admins.setId(2L);
        admins.setUserName("admin");
        result(adminsService.isUsedAdmin(admins));
    }
}
