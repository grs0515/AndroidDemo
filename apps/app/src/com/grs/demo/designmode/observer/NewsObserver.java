package com.grs.demo.designmode.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by gaoruishan on 16/4/4.
 */
public class NewsObserver implements Observer {
    @Override
    public void update(Observable observable, Object data) {
        if (data instanceof News){
            News news = (News) data;
            System.out.println("News:"+news.getTitle()+","+news.getBody());
        }
    }
}
