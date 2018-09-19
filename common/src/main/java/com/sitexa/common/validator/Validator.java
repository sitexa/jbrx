package com.sitexa.common.validator;

import java.lang.annotation.*;

/**<pre>
 * ------------------------------
 * 用于controller层需要验证数据较多时的验证
 * 默认VGroup=VGroup.excutor
 * 默认VRenderType=VRenderType.json
 * ------------------------------
 * </pre>
 */
@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Validator {
	VGroup group() default VGroup.excutor;
	Class<?> bean();
	String beanName() default "";
	String uri() default "";
	VRenderType render()  default VRenderType.json;
}