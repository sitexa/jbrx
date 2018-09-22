package com.sitexa.common.constants;

/**
 * <pre>
 * ------------------------------
    返回码取值范围-10 ~ 10：0为成功，其他都是异常请求结果
	重新登录：-10
	无权访问：-9
	参数错误：-2
	数据库异常：-1
	成功：0
	1至10为业务响应码,开发自定义
 * ------------------------------
 * </pre>
 */
public enum RC{


	/**接口版本错误**/
	VERSION_FAIL(-8,"接口版本错误"),

	BUSINESS_FAIL(-5,"业务异常"),
	/**数据库异常**/
	DATABASE_ERROR(-4,"数据库异常"),
	/**请求三方业务失败**/
	THIRD_BUSINESS_FAIL(-3,"请求三方业务失败"),
	/**参数错误**/
	PARAM_FAIL(-1,"参数错误"),
	
	/**成功**/
	SUCCESS(0,"成功"),

	/**业务结果1**/
	BUSINESS1(1,"业务结果1"),
	/**业务结果2**/
	BUSINESS2(2,"业务结果2"),
	/**业务结果3**/
	BUSINESS3(3,"业务结果3"),
	/**业务结果4**/
	BUSINESS4(4,"业务结果4"),
	/**业务结果5**/
	BUSINESS5(5,"业务结果5"),
	/**业务结果6**/
	BUSINESS6(6,"业务结果6"),
	/**业务结果7**/
	BUSINESS7(7,"业务结果7"),
	/**业务结果8**/
	BUSINESS8(8,"业务结果8"),
	/**业务结果9**/
	BUSINESS9(9,"业务结果9"),
	/**业务结果10**/
	BUSINESS10(10,"业务结果10"),
	
	/**重新登录**/
	RELOGIN(110,"重新登录"),
	/**权限错误**/
	NO_PERMISSION(111,"权限错误"),	
	/**请求失败**/
	REQUEST_FAIL(112,"请求失败"),	
	/**500**/
	SYS_ERROR_500(500,"系统内部异常500"),
	/**405**/
	SYS_ERROR_405(405,"系统内部异常405"),
	/**404**/
	SYS_ERROR_404(404,"系统内部异常404"),	
	/**403**/
	SYS_ERROR_403(403,"系统内部异常403"),
	/**401**/
	SYS_ERROR_401(401,"系统内部异常401");
	
	private int state;
	private String desc;
	private RC(int state,String desc){
		this.state=state;
		this.desc=desc;
	}
	public int getState(){
		return state;
	}
	public String getDesc(){
		return desc;
	}
	
	public static RC getStateByCode(int state){
		RC[] states= RC.values();
		for (int i = 0; i < states.length; i++) {
			if(states[i].getState()==state){
				return states[i];
			}
		}
		throw new RuntimeException("Ret  code["+state+"] not suport!");
	}
}