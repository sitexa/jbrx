package com.sitexa.common.validator;

import java.lang.annotation.*;

/**
 * ------------------------------
 * 配置一条校验规则
 * ------------------------------
 */
@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface VRule {
	VGroup[] groups() default {VGroup.excutor};
	VType[] types();
	String attrName();
	String message() default "参数错误";
}
