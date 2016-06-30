package com.cmcc.hyapps.KunlunTravel.rxjava;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cmcc.hyapps.KunlunTravel.R;
import com.cmcc.hyapps.KunlunTravel.base.BaseActivity;
import com.cmcc.hyapps.KunlunTravel.base.RxBus;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.cmcc.hyapps.KunlunTravel.utils.common.ToolFor9Ge.getBitmapFromPath;

/**
 * RxJava 练习
 * 查看log日志
 */
public class RxJavaActivity extends BaseActivity {

    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        layout=(LinearLayout)findViewById(R.id.ll);
        //简单创建使用
        test1();
        //快捷创建使用
        test2();
        //Scheduler 线程控制器
        test3();
        //Scheduler 设置图片
        test4();
        //转换
        test5();
        //使用Single
        test6();
        //RxBus
        test7();

    }

    private void test7() {
        //2,接受事件
        RxBus.getDefault().toObserverable(UserEvent.class).subscribe(new Action1<UserEvent>() {
            @Override
            public void call(UserEvent userEvent) {
                long id = userEvent.getId();
                String name = userEvent.getName();
                LogUtil.e("id=" + id + ",name=" + name);
            }
        });
    }

    private void test6() {
        Single.create(
                new Single.OnSubscribe<String>() {
                    @Override
                    public void call(SingleSubscriber<? super String> singleSubscriber) {
                        //延时
                        String value = longRunningOperation();
                        singleSubscriber.onSuccess(value);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        //update UI
                        //btn.setEnabled(true);
                        //1,订阅事件
                        RxBus.getDefault().post(new UserEvent(12, "测试"));
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        //fail
                    }
                });
//        Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                String value = longRunningOperation();
//                subscriber.onNext(value);
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        // //update UI
//                    }
//                });
    }

    private void test5() {
        final ImageView imageView1 = (ImageView) findViewById(R.id.icon1);
        //1.单个转换
        Observable.just("sdcard/temp.png")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<String, Bitmap>() {
                    @Override
                    public Bitmap call(String s) {
                        return getBitmapFromPath(s, 100, 100);
                    }
                })
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        imageView1.setImageBitmap(bitmap);
                    }
                });

        //2.多个匹配转换
        String[] urls = {"sdcard/temp.png","sdcard/test.jpg"};
        Observable.from(urls)
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return s.endsWith(".png");
                    }
                })
                .map(new Func1<String, ImageModel>() {
                    @Override
                    public ImageModel call(String s) {
                        Bitmap bitmap = getBitmapFromPath(s, 200, 200);
                        ImageView image = new ImageView(RxJavaActivity.this);
                        return new ImageModel(s,image,bitmap);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ImageModel>() {
                    @Override
                    public void call(ImageModel model) {
                        ImageView image = model.image;
                        Bitmap bitmap = model.bitmap;
                        image.setImageBitmap(bitmap);
                        layout.addView(image);
                    }
                });

        Student[] students = {new Student("学员1", new Course("语文")), new Student("学员2", new Course("数学"))};
        //1,获取Student 名字
        Observable.from(students)
                .map(new Func1<Student, String>() {
                    @Override
                    public String call(Student student) {
                        return student.name;
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        LogUtil.e("Name:" + s);
                    }
                });
        //2,获取获取Student 的所有Coures的名
        Observable.from(students)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        LogUtil.e(student.name);
                        return Observable.from(student.courses);
                    }
                })
                .subscribe(new Action1<Course>() {
                    @Override
                    public void call(Course course) {
                        LogUtil.e("CourseName:" + course.name);
                    }
                });
    }

    private void test4() {
        final int drawableRes = R.drawable.wenhuabest1;
        final ImageView imageView = (ImageView) findViewById(R.id.icon);
        Observable.create(
                //有subscribeOn(Schedulers.io())指定在子线程
                new Observable.OnSubscribe<Drawable>() {
                    @Override
                    public void call(Subscriber<? super Drawable> subscriber) {
                        Drawable drawable = getResources().getDrawable(drawableRes);
                        subscriber.onNext(drawable);
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Drawable>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(activity, "Error!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Drawable drawable) {
                        imageView.setImageDrawable(drawable);
                    }
                });
    }

    private void test3() {
        Observable.just(1, 2, 3)
                .subscribeOn(Schedulers.io())//// 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        LogUtil.e("number:" + integer);
                    }
                });
    }

    private void test2() {
        //from(需打印字符串数组).subscribe(new 一个订户)
        //Subscriber == Observer 观察者
        String[] names = {"张三", "李四", "王五"};
        Observable.from(names).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                LogUtil.e("onNext=" + s);
            }
        });
        //提供了多个 ActionX 形式的接口
        Observable.just("张三1", "李四1", "王五1").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                LogUtil.e("call=" + s);
            }
        });
    }

    private void test1() {
        //1，创建观察者 new
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onNext(String s) {
                //响应
                LogUtil.e("Item: " + s);
            }

            @Override
            public void onCompleted() {
                LogUtil.e("Completed!");
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e("Error!");
            }
        };
        //2，创建被观察者 create
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                //触发
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                subscriber.onCompleted();//订阅事件完成
            }
        });
        //3，订阅（是被观察者－通知观察者）
        observable.subscribe(observer);
    }

    class Student {
        String name;
        List<Course> courses;

        public Student(String name, Course course) {
            this.name = name;
            List<Course> courseList = new ArrayList<>();
            courseList.add(course);
            courseList.add(new Course("英语"));
            this.courses = courseList;
        }
    }

    class Course {
        String name;

        public Course(String name) {
            this.name = name;
        }
    }

    public String longRunningOperation() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // error
        }
        return "Complete!";
    }

    public class UserEvent {
        long id;
        String name;

        public UserEvent(long id, String name) {
            this.id = id;
            this.name = name;
        }

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
    class ImageModel{
        ImageView image;
        Bitmap bitmap;
        String url;

        public ImageModel(String url, ImageView image, Bitmap bitmap) {
            this.url = url;
            this.image = image;
            this.bitmap = bitmap;
        }

        public ImageModel(Bitmap bitmap, String url) {
            this.bitmap = bitmap;
            this.url = url;
        }

        public Bitmap getBitmap() {
            return bitmap;
        }

        public void setBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public ImageView getImage() {
            return image;
        }

        public void setImage(ImageView image) {
            this.image = image;
        }
    }
}
