package com.grs.demo.reflect;

public class ClassDemo02 {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // 调用String类类型的所有方法
        String string = "Hello";
        ClassUtil.printPublicMethodMessage(string);

        ClassUtil.printFieldMessage(new Integer(1));

        ClassUtil.printConstuctMessage(string);
        // 调用boolean类型的所有方法
        Boolean b = true;//同boolean b = true;
        ClassUtil.printPublicMethodMessage(b);

        ClassUtil.printPublicMethodMessage(new Foo());
        ClassUtil.printFieldMessage(new Foo());
        ClassUtil.printConstuctMessage(new Foo());

    }
}