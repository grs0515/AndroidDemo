package com.grs.demo.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassDemo03 {

    /**
     * 对反射方法调用操作
     * @param args
     */
    public static void main(String[] args) {
        // 要获取print（int,int）方法
        // 1,要获取一个方法的就是获取类的信息，获取类的信息首先要获取类的类类型
        A a = new A();
        Class<? extends A> c = a.getClass();
        // 2,获取方法－必须有名称和参数列表决定-->getMethod()获取public的方法

        try {
            getInvoke1(a, c);
            System.out.println("=======分割=======");
            // TODO 获得一个对象
            getInvoke2(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 获得方法
        ClassUtil.printPublicMethodMessage(a);
    }

    private static void getInvoke2(Class<?> c) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object a1 = c.newInstance();
        // 对应private的只能使用getDeclaredMethod（），否则报错
        Method method = c.getDeclaredMethod("print", new Class[]{int.class});
        //没用权限获得 NoSuchMethodException:
        //Method method = c.getMethod("print", new Class[] { int.class });
        method.setAccessible(true);
        method.invoke(a1, 100);// private Hello
    }

    private static void getInvoke1(A a, Class<? extends A> c) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        // 3,获取具体使用类型的方法
        // Method m = c.getMethod("print", new
        // Class[]{int.class,int.class});
        Method m = c.getMethod("print", int.class, int.class);
        // 方法的反射操作：使用m对象来进行方法调用和a.print(10,22)效果一样;
        // 方法如果没用返回值返回 null ,有返回值返回具体的返回值；
        Object o = m.invoke(a, new Object[]{10, 22});// 32
        System.out.println("==============");
        Method m2 = c.getMethod("print", String.class, String.class);
        m2.invoke(a, "Hello", "World");// HELLO,world
        System.out.println("==============");
        // 参数没有可以不用写
        // Method m3 = c.getMethod("print", null);
        Method m3 = c.getMethod("print");
        // m3.invoke(a, new Object[] {});
        m3.invoke(a);// public Hello
    }

}

class A {

    // private 没用权限获得
    private void print(int a) {
        System.out.println("private Hello");
    }

    public void print() {
        System.out.println("public Hello");
    }

    public void print(int a, int b) {
        System.out.println(a + b);
    }

    public void print(String a, String b) {
        System.out.println(a.toUpperCase() + "," + b.toLowerCase());
    }
}