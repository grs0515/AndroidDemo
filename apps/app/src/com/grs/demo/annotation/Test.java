package com.grs.demo.annotation;

//定义一个测试类，new一个对象调用过时方法
public class Test {

    public void sing() {
        // 多态－多继承形成
        Person p = new Man();
        // 提示过时
        p.sing();
    }
}