package com.grs.demo.designmode.observer;

/**
 * Created by gaoruishan on 16/4/4.
 */
public interface MyObservable {
    void addObserver(MyObserver observer);
    void removerObserver(MyObserver observer);
    void notifyObserver(Object data);
}
