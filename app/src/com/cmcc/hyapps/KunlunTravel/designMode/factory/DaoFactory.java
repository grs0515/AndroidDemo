package com.cmcc.hyapps.KunlunTravel.designMode.factory;

/**
 * 单例工厂
 * Created by gaoruishan on 16/4/4.
 */
public class DaoFactory {


    private static DaoImpUser mInstance;
    //不能实例话
    private DaoFactory() {
        throw new IllegalArgumentException("can't get a instance");
    }

    public static IDao getInstance(){
        if (mInstance==null){//同步，防止多个线程同时
            synchronized (DaoFactory.class){
                if (mInstance==null){
                    mInstance = new DaoImpUser();
                }
            }
        }
        return mInstance;
    }
}
