
package com.cmcc.hyapps.KunlunTravel.base;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;

import com.umeng.analytics.MobclickAgent;
import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.x;

import java.io.Serializable;

/**
 * 这是一个Activity的基类，所有的都要继承它
 * <p/>
 * 说明:FragmentActivity
 * android-support-v4.jar兼容包里面的，它提供了操作fragment的一些方法，其功能跟3.0及以后的版本的Activity的功能一样。
 * 说明:AppCompatActivity
 * 初期AppCompat只是让Actionbar兼容到API 7。在AppCompat 21版本中，加入主题色、Toolbar等功能。显然ActionBarActivity这个名字已经不在适用AppCompat。新版本中，推荐使用AppCompatActivity 代替ActionBarActivity。
 * <p/>
 * AutoLayoutActivity 继承AppCompatActivity 又继承FragmentActivity
 */
public class BaseActivity extends AutoLayoutActivity {
    protected Activity activity;
    protected String requestTag;
    protected static final int REQUECT_CODE_SDCARD = 100;
    protected static final int REQUECT_CODE = 110;
    protected static final int REFRESH = 1;
    protected final static int LOAD_INIT = 2;
    protected final static int LOAD_MORE = 2;
    protected int limit = 10;
    protected int offset = 1;
    public static final String PARAM_LIMIT = "limit";
    public static final String PARAM_OFFSET = "offset";
    public static final String PARAM_TYPE = "type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在基类中注入
        x.view().inject(this);
        //进出和退出动画
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        requestTag = getClass().getName();
        activity = this;
        AppManager.getAppManager().addActivity(this);
    }

    /**
     * 友盟  session的统计
     */
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(activity);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(activity);
    }

    @Override
    public void finish() {
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        super.finish();
    }


    @Override
    protected void onStop() {
        super.onStop();
        //页面不可见时，取消所有的requestTag，RequestManager是什么呢？
//        RequestManager.getInstance().cancelAll(requestTag);
    }

//    protected void executeRequest(Request<?> request) {
//        RequestManager.getInstance().addRequest(request, requestTag);
//    }

    /**
     * 提供跳转
     */
    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(activity, cls);
        startActivity(intent);
    }

    /**
     * 提供简单跳转传递String数据(startActivity(Class<?> cls, String args) getIntentStringExtra()配套使用)
     *
     * @param cls
     * @param args
     */
    public void startActivity(Class<?> cls, String args) {
        Intent intent = new Intent(activity, cls);
        intent.putExtra(AppConst.EXTRA_DATA_STRING, args);
        startActivity(intent);
    }

    public String getIntentStringExtra() {
        return getIntent().getStringExtra(AppConst.EXTRA_DATA_STRING);
    }
    public String getIntentIntExtra() {
        return getIntent().getStringExtra(AppConst.EXTRA_DATA_INT);
    }

    /**
     *  提供两个参数跳转传递String数据，Int数据
     * @param cls
     * @param args
     * @param args1
     */
    public void startActivity(Class<?> cls, int args, String args1) {
        Intent intent = new Intent(activity, cls);
        intent.putExtra(AppConst.EXTRA_DATA_INT, args);
        intent.putExtra(AppConst.EXTRA_DATA_STRING, args1);
        startActivity(intent);
    }

    /**
     * 提供多个参数跳转传递(使用Map保存数据)
     * @param to
     * @param map
     */
    public void startActivity(Class<?> to, ArrayMap<String, Object> map) {
        Intent intent = new Intent(activity, to);
        if (null != map && !map.isEmpty()) {
            for (int i = 0, len = map.size(); i < len; i++) {
                String key = map.keyAt(i);
                Object value = map.valueAt(i);
                if (value instanceof Integer) {
                    intent.putExtra(key, Integer.parseInt(value.toString()));
                } else if (value instanceof String) {
                    intent.putExtra(key, value.toString());
                } else if (value instanceof Serializable) {
                    intent.putExtra(key, (Serializable) value);
                } else if (value instanceof Boolean) {
                    intent.putExtra(key, Boolean.valueOf(value.toString()));
                }
            }
            map.clear();
            startActivity(intent);
        }
    }

    /**
     * app字体不随系统字体变化
     */
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }
}
