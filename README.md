# AndroidDemo
  这是一个android开发的基本架构模版。
  
# 依赖库
  1,Vitamio
  
    是Android平台视音频播放组件，支持播放几乎格式的视频以及主流网络视频流（http/rtsp/mms等
    
    a).	强大。支持超多格式视频和网络视频播放。（不强大免费也没用，所以排在第一位）
    
    b).	个人免费。（但企业使用需要购买授权）（Google Play上已有多款基于Vitamio的收费播放器，大家可以在里面搜索Vitamio关键字）
    
    c).	无缝集成。仅将Vitamio的Library工程引入即可使用，无需另外下载安装解码包（Vitamio的上一个版本是需要单独下载安装解码包）。
    
    d).	使用简单。调用非常简单，方便使用。
    
    e).	自由定制。播放界面的代码已完全开放，方便自定义播放界面、进度条等。
    
    d).	持续更新。2012-7-9已发布新一版的内测版本，预计8月初能发布下一个版本。
    
    e).	服务跟进。Vitamio官方QQ群（171570336），提供开发者交流和讨论。论坛还在开发中。
    
    学习：http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0102/2253.html
    
  2,

# MVP 模式
  在MVP模式里通常包含4个要素：
  
     (1)View:负责绘制UI元素、与用户进行交互(在Android中体现为Activity);

     (2)View interface:需要View实现的接口，View通过View interface与Presenter进行交互，降低耦合，方便进行单元测试;

     (3)Model:负责存储、检索、操纵数据(有时也实现一个Model interface用来降低耦合);

     (4)Presenter:作为View与Model交互的中间纽带，处理与用户交互的负责逻辑。
     
  区别:MVC和MVP
  
    View：对应于布局文件
    Model：业务逻辑和实体模型
    Controllor：对应于Activity
    
    View 对应于Activity，负责View的绘制以及与用户交互
    Model 依然是业务逻辑和实体模型
    Presenter 负责完成View于Model间的交互

     
# RxJava 使用
     
  一个在 Java VM 上使用可观测的序列来组成异步的、基于事件的程序的库.
 
  观察者模式:例子是点击监听器 OnClickListener.
    
     View 是被观察者， OnClickListener 是观察者，二者通过 setOnClickListener() 方法达成订阅关系
     Button -> 被观察者、OnClickListener -> 观察者、setOnClickListener() -> 订阅，onClick() -> 事件
 
  RxJava 有四个基本概念：
  
    1,Observable (被观察者)、
    2,Observer (观察者)、＝＝ observer / subscribe 订户
    3,subscribe (订阅)、
    4,事件
  
  流程:
  
    Observable 和Observer 通过 subscribe() 方法实现订阅关系，从而 Observable 可以在需要的时候发出事件来通知 Observer。
  
  RxJava事件回调方法:
  
    1,普通事件 onNext() （相当于 onClick() / onEvent());还定义了两个特殊的事件：
    2,onCompleted(): 事件队列完结。RxJava 不仅把每个事件单独处理，还会把它们看做一个队列。RxJava 规定，当不会再有新的onNext() 发出时，需要触发 onCompleted() 方法作为标志。
    3,onError(): 事件队列异常。在事件处理过程中出异常时，onError() 会被触发，同时队列自动终止，不允许再有事件发出。
    在一个正确运行的事件序列中, onCompleted() 和 onError() 有且只有一个，并且是事件序列中的最后一个。需要注意的是，onCompleted() 和 onError() 二者也是互斥的，即在队列中调用了其中一个，就不应该再调用另一个。
  
  目的:『后台处理，前台回调』的异步机制.
  
  Scheduler ——> 调度器
  
    1,Schedulers.immediate(): 直接在当前线程运行，相当于不指定线程。这是默认的 Scheduler。
    2,Schedulers.newThread(): 总是启用新线程，并在新线程执行操作。
    3,Schedulers.io(): I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler。行为模式和 newThread() 差不多，区别在于 io() 的内部实现是是用一个无数量上限的线程池，可以重用空闲的线程，因此多数情况下 io() 比 newThread() 更有效率。不要把计算工作放在 io() 中，可以避免创建不必要的线程。
    4,Schedulers.computation(): 计算所使用的 Scheduler。这个计算指的是 CPU 密集型计算，即不会被 I/O 等操作限制性能的操作，例如图形的计算。这个 Scheduler 使用的固定的线程池，大小为 CPU 核数。不要把 I/O 操作放在 computation() 中，否则 I/O 操作的等待时间会浪费 CPU。
    5,AndroidSchedulers.mainThread()，它指定的操作将在 Android 主线程运行。
    6,subscribeOn(): 指定 subscribe() 所发生的线程，即 Observable.OnSubscribe 被激活时所处的线程。或者叫做事件产生的线程。
    7,observeOn(): 指定 Subscriber 所运行在的线程。或者叫做事件消费的线程。observeOn() 指定的是它之后的操作所在的线程
  
  变换:将事件序列中的对象或整个序列进行加工处理，转换成不同的事件或事件序列.