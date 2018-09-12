package com.www.mall.common.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ------------------------------
 * 用于controller层验证版本号
 * ------------------------------
 * @author wdm（l311576@sina.com）  @date 2018年5月30日
 * @version 1.0
 */
@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Version {
	String message();
	int version();
}