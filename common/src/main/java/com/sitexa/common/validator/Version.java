package com.sitexa.common.validator;

import java.lang.annotation.*;

/**
 * ------------------------------
 * 用于controller层验证版本号
 * ------------------------------
 */
@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Version {
	String message();
	int version();
}