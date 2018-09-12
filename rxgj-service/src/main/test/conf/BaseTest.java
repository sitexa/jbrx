package conf;

import com.gavin.business.DBTrans;
import com.www.mall.common.bean.Ret;

public class BaseTest {
	
	static{
		boolean dbstart=DBTrans.getInstance().config("jboot.properties").start();
		if(!dbstart){//数据库插件启动失败
			System.out.println("数据库启动失败");
		}
	}
	
	public static void result(Object obj){
		System.out.println(Ret.ok("ok", obj));
	}

}
