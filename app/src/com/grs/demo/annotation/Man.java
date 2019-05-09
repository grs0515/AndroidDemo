package com.grs.demo.annotation;

//定义一个子类，实现接口 重写三个方法 使用注解提示编译器
@Description(desc = "this is class", author = "grs")
public class Man implements Person {

    @Override
    //@Description("the name is method")//只有一个方法
    @Description(desc = "this is method", author = "grs")//有默认值的可以不用写
    public String name() {
        return null;
    }

    @Override
    @Description(desc = "the age is method",author = "grs")
    public int age() {
        return 0;
    }

    @Override
    public void sing() {
    }

}