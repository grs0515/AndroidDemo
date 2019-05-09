package com.grs.demo.annotation;

// 定义一个接口
public interface Person {

    String name();

    int age();

    // 过时注解
    @Deprecated
    void sing();
}