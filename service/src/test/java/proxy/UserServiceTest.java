package proxy;

import com.gavin.model.Response;
import com.sitexa.common.bean.UserVo;
import com.sitexa.common.shiro.principal.UserPrincipal;
import com.sitexa.facade.interfaces.UserService;
import com.xiaoleilu.hutool.util.RandomUtil;
import conf.BaseTest;
import io.jboot.core.rpc.annotation.JbootrpcService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

public class UserServiceTest extends BaseTest {
/*
    //UserService userService = new UserServiceImpl();

    @JbootrpcService
    UserService userService;

    @Test
    public void testInsert(){

        String salt = RandomUtil.randomUUID();
        String pwd = "123456";
        String password = new Md5Hash(pwd,salt,3).toString();

        UserPrincipal user = new UserPrincipal();
        user.setMobilePhone("12345678904");
        user.setPic("pic");
        user.setNickName("nicky");
        user.setRealName("realman");
        user.setSex(1);
        user.setSalt(salt);
        user.setPassword(password);

        Response response = userService.saveUser(user);

        result(response);
    }

    @Test
    public void testQueryUserByMobile(){
        Response response = userService.queryUserByUsersName("12345678901");
        result(response);

        System.out.println("response.result = " + response.result);
        System.out.println("response.data = " + response.data);
        System.out.println("response.getData() = " + response.getData());

        UserPrincipal user = response.bean(UserPrincipal.class);
        System.out.println("user = " + user.toString());

        UserVo userVo = response.bean(UserVo.class);
        System.out.println("userVo = " + userVo.toString());
    }

    @Test
    public void testqueryUserNameExist(){
        Response response = userService.queryUserNameExist("12345678901");
        result(response);

        System.out.println("response.getData() = " + response.getData());
    }

    @Test
    public void testqueryUsersById(){
        UserPrincipal user = userService.queryUserById(3);
        result(user);
    }*/
}
