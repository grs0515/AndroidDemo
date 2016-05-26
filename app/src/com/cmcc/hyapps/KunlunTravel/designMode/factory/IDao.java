package com.cmcc.hyapps.KunlunTravel.designMode.factory;

/**
 * 定义一个泛型接口,添加操作方法
 * Created by gaoruishan on 16/4/4.
 */
public interface IDao<T> {

    void save(T user);

    void delete(int id);
}

