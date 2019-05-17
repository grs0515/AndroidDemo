package com.grs.demo.arouter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.grs.demo.R;
import com.grs.demo.arouter.bean.RouterTestObject;

/**
 * 使用说明: https://www.jianshu.com/p/6021f3f61fa6
 * @author:gaoruishan
 * @date:2019/1/22/09:53
 * @email:grs0515@163.com
 */
public class Router {
    /**
     * 定义path
     */
    public static final String TEST_URL = "/test/activity";
    public static final String TEST_PARAM_URL = "/test/param";
    public static final String ACTIVITY_URL_FRAGMENT = "/test/fragment";
    public static final String TEST_URI = "/test/uri";
    public static final String TEST_INTERCEPT = "/test/intercept";
    private static final String TAG = "Router";
    /**
     * 使用Interceptor类注解的priority，数值越小，越先执行。
     */
    public static final int priority = 1;


    /**
     * 普通跳转
     */
    public static void toTest() {
        // 1. 应用内简单的跳转(通过URL跳转在'进阶用法'中)
        ARouter.getInstance().build(TEST_URL)
                //界面跳转动画
                .withTransition(R.anim.slide_in_from_top, R.anim.slide_out_to_bottom)
                .navigation();

    }

    /**
     * 带参数跳转
     */
    public static void toTestParam() {
        // 2. 跳转并携带参数
        RouterTestObject routerTestObject = new RouterTestObject();
        routerTestObject.setName("测试");
        ARouter.getInstance().build(TEST_PARAM_URL)
                .withLong("key1", 666L)
                .withString("key2", "888")
                .withSerializable("key3", routerTestObject)
                //会报错
//                .withObject("key3",routerTestObject )
                .navigation();
    }

    /**
     * uri跳转
     */
    public static void toUri() {
        Uri uri = Uri.parse(TEST_URI);
        ARouter.getInstance().build(uri).navigation();
    }

    /**
     * fragment 获取
     * @return
     */
    public static Fragment toFragment() {
        return (Fragment) ARouter.getInstance().build(ACTIVITY_URL_FRAGMENT).navigation();

    }

    /**
     * 跳转拦截
     * @param context
     */
    public static void toInterceptActivity(Context context) {
        ARouter.getInstance().build(TEST_INTERCEPT)
                .navigation(context, new NavigationCallback() {
                    @Override
                    public void onFound(Postcard postcard) {
                        //路由目标被发现时调用
                        String group = postcard.getGroup(); String path = postcard.getPath();
                        Log.e(TAG, "onFound : group == "+group + "; path == "+path);
                    }

                    @Override
                    public void onLost(Postcard postcard) {
                        //路由被丢失时调用
                        Log.e(TAG, "onLost : " );

                    }

                    @Override
                    public void onArrival(Postcard postcard) {
                        //路由到达之后调用
                        String group = postcard.getGroup();
                        String path = postcard.getPath();
                        Log.e(TAG, "onArrival : group == "+group + "; path == "+path);
                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {
                        //路由被拦截时调用
                        Log.e(TAG, "onInterrupt : 被拦截了" );
                    }
                });
    }
}
