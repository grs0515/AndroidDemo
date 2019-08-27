package com.grs.lib.annotations.find;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author:gaoruishan
 * @date:202019-05-09/16:42
 * @email:grs0515@163.com
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FindEvent {
    int value();
}
