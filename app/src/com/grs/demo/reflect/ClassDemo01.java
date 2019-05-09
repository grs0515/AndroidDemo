package com.grs.demo.reflect;

public class ClassDemo01 {
    public static void main(String[] args) {
        // 1，Foo的实例对象如何表示
        Foo foo = new Foo();// foo 就表示出来了
        // Foo 这个类 也是一个实例对象，任何一个类都是Class的实例对象
        // 2，Foo的类类型如何表示
        // 三种表示方式：

        // 第一种 表示方式: 类名.class－－>任何一个类都有一个隐含的静态成员变量class
        Class<Foo> c1 = Foo.class;

        // 第二种 表示方式:对象名.getClass()-->已知该类的对象通过getClass方法
        Class<? extends Foo> c2 = foo.getClass();

        /**
         * 官网 c1,c2表示了Foo类的类类型（class type）; 万事万物皆对象 Foo这个类本身就是Class的实例对象；
         * 这个对象称为该类的类类型。
         */

        // 不管c1,c2代表了什么类的类类型，一个类只有可能是Class类的一个实例对象
        System.out.println(c1 == c2);// true

        // 第三种 表示方式:Class.forName("包名＋类名")
        Class<?> c3 = null;
        try {
            c3 = Class.forName("com.grs.demo.reflect.Foo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(c2 == c3);// true

        // 可以通过类的类类型创建该类的对象实例－－>通过c1,c2,c3 创建Foo的实例对象
        try {
            //TODO 需要有无参数的构造方法
            Foo foo2 = c1.newInstance();
            foo2.print();//Foo
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
