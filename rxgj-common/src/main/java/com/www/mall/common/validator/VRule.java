package com.www.mall.common.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ------------------------------
 * 配置一条校验规则
 * ------------------------------
 * @author wdm（l311576@sina.com）  @date 2018年7月31日
 * @version 1.0
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
