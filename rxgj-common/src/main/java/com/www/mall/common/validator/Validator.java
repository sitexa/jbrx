package com.www.mall.common.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**<pre>
 * ------------------------------
 * 用于controller层需要验证数据较多时的验证
 * 默认VGroup=VGroup.excutor
 * 默认VRenderType=VRenderType.json
 * ------------------------------
 * </pre>
 * @author wdm（l311576@sina.com）  @date 2018年5月30日
 * @version 1.0
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