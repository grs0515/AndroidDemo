package com.grs.demo.reflect;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class ClassDemo04 {

    /**
     * 反射的操作都是再编译之后的操作，绕过了编译操作就绕过了泛型
     * @param args
     */
    public static void main(String[] args) {
        ArrayList list = new ArrayList();

        ArrayList<String> list2 = new ArrayList<String>();
        list2.add("Hello");
        // list2.add(20);错误的
        Class<? extends ArrayList> c1 = list.getClass();
        Class<? extends ArrayList> c2 = list2.getClass();
        System.out.println(c1 == c2);// true
        /**
         * 反射的操作都是再编译之后的操作 c1==c2 结果返回true 说明编译之后集合的泛型是去泛型化的
         * java中集合的泛型，是防止错误输入的，只再编译阶段有效；
         */
        try {
            //   public boolean add(E e) {}
            Method m = c2.getMethod("add", Object.class);
            // TODO **绕过了编译操作就绕过了泛型**
            m.invoke(list2, 100);
            System.out.println(list2.size());// 2
            System.out.println(list2);// [Hello, 100]
            // 注：此时不能再用foreach 遍历
// for (String string : list2) {
// System.out.println(string);//异常 ClassCastException
// }
            for (int i = 0; i < list2.size(); i++) {
                // 使用Object可以变量
                Object s = list2.get(i);
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}