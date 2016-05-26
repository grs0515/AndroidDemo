package com.cmcc.hyapps.KunlunTravel.designMode.observer;

import java.util.Observable;

/**
 * Created by gaoruishan on 16/4/4.
 */
public class NewsObservable extends Observable{

    public void addNews(News news){
        setChanged();//改变状态
        notifyObservers(news);//刷新
        System.out.println("addNews 成功");
    }
}
