package com.grs.demo.base;

import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * 使用说明：
 *
 * 1,订阅事件 =>  RxBus.getDefault().post(new UserEvent (1, "yoyo"));
 *
 * 2,接收事件 =>  rxSubscription是一个Subscription的全局变量，这段代码可以在onCreate/onStart等生命周期内
 * rxSubscription = RxBus.getDefault().toObserverable(UserEvent.class)
 * .subscribe(new Action1<UserEvent>() {
 *
 * @Override public void call(UserEvent userEvent) {
 * long id = userEvent.getId();
 * String name = userEvent.getName();
 * ...
 * }
 * },
 * new Action1<Throwable>() {
 * @Override public void call(Throwable throwable) {
 * // TODO: 处理异常
 * }
 * });
 *
 *
 * Created by gaoruishan on 16/3/27.
 * 文／YoKeyword（简书作者）
 * 原文链接：http://www.jianshu.com/p/ca090f6e2fe2
 */
public class RxBus {
    private static RxBus defaultInstance;
    // 主题
    private final Subject bus;

    /**
     * 1、Subject同时充当了Observer和Observable的角色，Subject是非线程安全的，要避免该问题，需要将 Subject转换为一个 SerializedSubject ，上述RxBus类中把线程非安全的PublishSubject包装成线程安全的Subject。
     * 2、PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者。
     */
    public RxBus() {
        bus = new SerializedSubject<>(PublishSubject.create());
    }

    /**
     * 单例RxBus
     */
    public static RxBus getDefault() {
        if (defaultInstance == null) {
            synchronized (RxBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new RxBus();
                }
            }
        }
        return defaultInstance;
    }


    /**
     * 提供了一个新的事件
     * 3、在我们需要发送事件的地方，将事件post至Subject，此时Subject作为Observer接收到事件（onNext），然后会发射给所有订阅该Subject的订阅者。
     *
     * @param o
     */
    public void post(Object o) {
        bus.onNext(o);
    }

    /**
     * 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
     * 4、Filter操作符可以使你提供一个指定的测试数据项，只有通过测试的数据才会被“发射”。
     * 5、cast操作符可以将一个Observable转换成指定类型的Observable。
     */
    public <T extends Object> Observable<T> toObserverable(final Class<T> eventType) {
        return bus.filter(new Func1<Object, Boolean>() {
            @Override
            public Boolean call(Object o) {
                return eventType.isInstance(o);
            }
        })
                .cast(eventType);
    }
}
