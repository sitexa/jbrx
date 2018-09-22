package com.sitexa.msg.vo;

/**
 * ------------------------------
 * 短信状态  state非1的都是失败
 * ------------------------------
 * @date 2017年11月18日
 * @version 1.0
 */
public class SmsEnums {



	/**
	 * https://docs.jiguang.cn/jsms/server/rest_api_jsms/ 状态码返回
	 */
	public enum State{
		
		//成功-----------------------------------
		dev_success(1, "当前为测试环境,默认成功"),
		send_success(1, "信息发送成功"),
		test_success(1, "信息验证成功"),
		
		//失败-----------------------------------
		connection_err(2, "连接异常"),
		send_fail(3, "信息发送失败"),
		test_fail(4, "信息验证失败"),
		other_err(5,"未知错误"),
		
		//三方错误码  以后可以通过数据库或配置文件获取-----------------------------------
		send_manyRequest(50009, "当前用户请求频繁"),
		missing_mobile(50004,"手机号码为空"),
		send_phoneInvalid(50006,"手机号码无效"),
		test_smsError(50010, "验证码无效"),
		test_smsInvalid(50011,"验证码过期"),
		test_smsVerified(50012, "验证码以验证过"),
		invalid_temp_id(50013,"模版ID无效"),
		no_money(50014,"可发短信余量不足"),
		missing_code(50015,"验证码为空"),
		api_not_found(50016,"API 不存在"),
		media_not_supported(50017,"媒体类型不支持"),
		request_method_not_support(50018,"请求方法不支持"),
		wrong_msg_id(50026,"msg_id 无效"),
		server_error(50019,"服务端异常"),
		repeat_send(50034,"重复发送"),
		illegal_IP(50035,"非法 IP 请求"),
		has_black_word(50037,"短信内容存在敏感词汇"),
		template_is_empty(50042,"模板内容为空"),
		template_parameter_invalid(50044,"模板参数无效")
		;
		
		
		private String resultDesc;
		private int resultState;
		
		private State(int resultState, String resultDesc) {
			this.resultDesc = resultDesc;
			this.resultState = resultState;
		}
		
		
		public static State getResultDescByResultState(int resultCode){
			State[] smsStates= State.values();
			for (int i = 0; i < smsStates.length; i++) {
				if(smsStates[i].getResultState()==resultCode){
					return smsStates[i];
				}
			}
			return State.send_fail;
		}
		
		
		public int getResultState() {
			return resultState;
		}
		
		public String getResultDesc() {
			return resultDesc;
		}
	}


}
