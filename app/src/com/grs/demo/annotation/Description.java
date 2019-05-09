package com.grs.demo.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//声明 注解的作用域
@Target({ ElementType.METHOD, ElementType.TYPE })
// 声明 注解的生命周期
@Retention(RetentionPolicy.RUNTIME)
// 声明 注解的标识 即运行子类继承
@Inherited
// 声明 生成javadoc时会包含注解
@Documented
// 使用@interface 关键字定义注解
public @interface Description {

    // 只有一个成员，必须使用value（）
    // String value();

    // 成员以无参数无异常方式声明
    String desc();

    String author();

    // 可以用default 为成员指定一个默认值
    int age() default 18;

}