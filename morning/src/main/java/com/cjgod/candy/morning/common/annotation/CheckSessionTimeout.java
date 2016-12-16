package com.cjgod.candy.morning.common.annotation;

/**
 * Created by lichunjiang on 2016/12/16.
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckSessionTimeout {

}