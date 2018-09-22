package com.sitexa.common.validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ------------------------------
 * 数据验证规则
 * ------------------------------
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