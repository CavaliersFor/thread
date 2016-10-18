package com.lunjar.ebp.admin.biz.authority;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * administer权限控制
 * 
 * @author fish
 * 
 */
@Target({ ElementType.METHOD, ElementType.TYPE, ElementType.PACKAGE })
@Retention(RetentionPolicy.RUNTIME)
public @interface AdminAuthority {
	/**对应functionResource.xml中的id*/
	String[] value() default {};
}