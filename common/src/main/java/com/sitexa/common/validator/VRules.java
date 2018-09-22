package com.sitexa.common.validator;

import java.lang.annotation.*;

/**
 * ------------------------------
 * 校验规则
 * ------------------------------
 */
@Documented
@Target(ElementType.TYPE)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface VRules {
	VRule[] rules();
}