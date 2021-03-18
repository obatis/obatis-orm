package com.obatis.orm.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Documented
@Target(ElementType.FIELD)
public @interface Column {

	/**
	 * 字段名称
	 * @return
	 */
	String name();
}
