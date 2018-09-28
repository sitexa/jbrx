package proxy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sitexa.common.shiro.principal.AdminPrincipal;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Date;

public class TestJSON {

    private static AdminPrincipal admin(){
        AdminPrincipal admin = new AdminPrincipal();
        admin.setUserName("admin_name");
        admin.setPassword("admin_password");
        admin.setAvatar("avatar");
        admin.setId(1L);
        admin.setStatus(1);
        admin.setCreateTime(new Date().toString());

        return admin;
    }

    @Test
    public void bean2obj() throws IllegalAccessException {
        AdminPrincipal admin = admin();

        Object obj = JSON.toJSON(admin);
        System.out.println("obj = " + obj);

        Class clazz = obj.getClass();
        System.out.println("clazz = " + clazz);

        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            System.out.println("field.getName() = " + field.getName());
        }

        Field[] fields2 = clazz.getDeclaredFields();
        for (Field field2 : fields2) {
            System.out.println("field2.getName() = " + field2.getName());
        }
    }

    @Test
    public void bean2json(){
        AdminPrincipal admin = admin();

        JSONObject json =(JSONObject) JSON.toJSON(admin);
        String jstr = json.toJSONString();
        System.out.println("jstr = " + jstr);

        String d = json.getString("createTime");
        Date dd = new Date(d);
        System.out.println("dd = " + dd);
    }


}
