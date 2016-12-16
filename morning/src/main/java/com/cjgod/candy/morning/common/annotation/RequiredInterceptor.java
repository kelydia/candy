package com.cjgod.candy.morning.common.annotation;

/**
 * Created by lichunjiang on 2016/12/16.
 */
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiredInterceptor {
    boolean required() default true;

    String desc();
}

