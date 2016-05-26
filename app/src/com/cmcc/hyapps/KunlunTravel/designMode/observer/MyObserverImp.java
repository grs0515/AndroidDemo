package com.cmcc.hyapps.KunlunTravel.designMode.observer;

/**
 * Created by gaoruishan on 16/4/4.
 */
public class MyObserverImp implements MyObserver {
    @Override
    public void update(Object data) {
        if (data!=null)
            if (data instanceof News){
                News news = (News) data;
                System.out.println("@News:"+news.getTitle()+","+news.getBody());
            }
    }
}
