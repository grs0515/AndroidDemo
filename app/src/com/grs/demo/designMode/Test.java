package com.grs.demo.designmode;

import com.grs.demo.designmode.factory.DaoFactory;
import com.grs.demo.designmode.factory.User;
import com.grs.demo.designmode.factory.Video;
import com.grs.demo.designmode.observer.MyObservableImp;
import com.grs.demo.designmode.observer.MyObserverImp;
import com.grs.demo.designmode.observer.News;
import com.grs.demo.designmode.observer.NewsObservable;
import com.grs.demo.designmode.observer.NewsObserver;

/**
 * Created by gaoruishan on 16/4/4.
 */
public class Test {

    public static void main(String[] args) {

        testFactory();//测试工程模式

        testObserverCustom();//测试观察者模式
        testObserverSystem();

    }

    private static void testObserverSystem() {
        final NewsObservable observable = new NewsObservable();
        NewsObserver observer = new NewsObserver();
        observable.addObserver(observer);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                News news = new News("SystemTest标题","SystemTest内容");
                observable.notifyObservers(news);
            }
        }).start();
    }

    private static void testObserverCustom() {
        final MyObservableImp observable = new MyObservableImp();
        MyObserverImp observer = new MyObserverImp();
        observable.addObserver(observer);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                News news = new News("customTest标题","customTest内容");
                observable.notifyObserver(news);
            }
        }).start();

    }

    private static void testFactory() {
        User user = new User("张三", 22);
        DaoFactory.getInstance().save(user);

        Video video = new Video("视频", "http://www.baidu.com", 22);
        DaoFactory.getInstance().save(video);
    }

}
