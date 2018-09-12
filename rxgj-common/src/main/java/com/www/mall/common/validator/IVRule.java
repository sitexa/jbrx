package com.www.mall.common.validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ------------------------------
 * 数据验证规则
 * ------------------------------
 * @author wdm（l311576@sina.com）  @date 2018年5月30日
 * @version 1.0
 */
public interface IVRule {
	/**
	 * 校验规则
	 */
	public static final Map<String,List<ValidatorRule>> BEAN_VALIDATOT_RULE=new HashMap<String,List<ValidatorRule>>();
	/**
	 * 是否初始化校验规则
	 */
	public static final Map<String,Boolean> IS_INIT_VALIDATOT_RULE=new HashMap<String,Boolean>();
}