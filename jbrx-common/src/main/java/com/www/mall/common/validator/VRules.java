package com.www.mall.common.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ------------------------------
 * 校验规则
 * ------------------------------
 * @author wdm（l311576@sina.com）  @date 2018年7月31日
 * @version 1.0
 */
@Documented
@Target(ElementType.TYPE)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface VRules {
	VRule[] rules();
}