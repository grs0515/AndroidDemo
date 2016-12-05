package com.grs.demo.designmode.factory;

/**
 * 模型
 * Created by gaoruishan on 16/4/4.
 */
public class User {
   private String name;
   private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
