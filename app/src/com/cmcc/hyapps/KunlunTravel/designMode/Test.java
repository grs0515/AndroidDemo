package com.cmcc.hyapps.KunlunTravel.designMode;

import com.cmcc.hyapps.KunlunTravel.designMode.factory.DaoFactory;
import com.cmcc.hyapps.KunlunTravel.designMode.factory.User;
import com.cmcc.hyapps.KunlunTravel.designMode.factory.Video;
import com.cmcc.hyapps.KunlunTravel.designMode.observer.MyObservableImp;
import com.cmcc.hyapps.KunlunTravel.designMode.observer.MyObserverImp;
import com.cmcc.hyapps.KunlunTravel.designMode.observer.News;
import com.cmcc.hyapps.KunlunTravel.designMode.observer.NewsObservable;
import com.cmcc.hyapps.KunlunTravel.designMode.observer.NewsObserver;

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
