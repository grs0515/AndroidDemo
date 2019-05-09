package com.grs.demo.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 打印类的信息，包括类的成员函数，成员变量，构造函数
 */
public class ClassUtil {

    /**
     * 获取类的成员函数（自己声明所有public的）
     */
    public static void printPublicMethodMessage(Object obj) {

        // 1，要获取类的信息 首先要获取类的类类型
        // 注：getClass()使用native 声明 JNR处理本地方法：用java声明 调用底层C实现
        Class<? extends Object> c = obj.getClass();// 传递的是哪个子类的对象 c 就是该子类的类类型

        // 2,获取类的名称
        System.out.println("类的名称是:" + c.getName());
        /**
         * Method类，方法对象，一个成员方法就是一个Method对象
         * getMethods()方法获取的是所有的Public的函数，包括父类继承来的
         * getDeclaredMethods()获取的是所有该类自己声明的方法，不问访问权限
         */
        Method[] ms = c.getDeclaredMethods();// c.getDeclaredMethods()
        for (Method method : ms) {
            Class<?> type = method.getReturnType();
            System.out.print(type.getName() + " ");
            // 得到方法的名
            System.out.print(method.getName() + "(");
            // 获取参数类型-->得到的是参数列表的类类型
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (Class<?> class1 : parameterTypes) {
                System.out.print(class1.getName() + ",");
            }
            System.out.println(")");
        }
    }

    /**
     * 获取成员变量的信息（所有自己声明的）
     * @param obj
     */
    public static void printFieldMessage(Object obj) {

        // 1，要获取类的信息 首先要获取类的类类型
        // 注：getClass()使用native 声明 JNR处理本地方法：用java声明 调用底层C实现
        Class<? extends Object> c = obj.getClass();// 传递的是哪个子类的对象 c 就是该子类的类类型

        // 2,获取类的名称
        System.out.println("类的名称是:" + c.getName());
        /**
         * 成员变量也是对象 java.lang.reflect.field Field类封装了关于成员变量的操作
         * getFields()方法获取的是所有的public的成员变量的信息
         * getDeclaredFields()获取的是该类自己声明的成员变量信息
         */
        // Field[] fs = c.getFields();
        Field[] fs = c.getDeclaredFields();
        for (Field field : fs) {
            // 得到成员变量的类类型
            Class<?> type = field.getType();
            String typeName = type.getName();
            // 得到成员变量的名称
            String fieldName = field.getName();
            System.out.println(typeName + " " + fieldName);
        }
    }

    /**
     * 获取类的构造函数的信息
     */
    public static void printConstuctMessage(Object obj) {
        Class c = obj.getClass();
        /**
         * 构造函数也是对象 java.lang.Constructor中封装类构造函数的信息
         * getContructors()获得所有的public的构造函数 getDeclaredContructors的到所有到构造函数
         */
        // Constructor[] cs = c.getConstructors();
        Constructor[] cs = c.getDeclaredConstructors();
        for (Constructor constructor : cs) {
            System.out.print(constructor.getName() + "(");
            // 获取构造函数到参数列表－－> 得到到是参数列表到类类型
            Class[] parameterTypes = constructor.getParameterTypes();
            for (Class class1 : parameterTypes) {
                System.out.print(class1.getName() + " ");
            }
            System.out.println(") ");
        }
    }
}


