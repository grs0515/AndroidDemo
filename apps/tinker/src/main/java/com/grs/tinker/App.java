package com.grs.tinker;


import android.content.Context;
import android.util.Log;

import com.lib.tinker.app.TinkerService;

import org.xutils.x;


/**
 * @author:gaoruishan
 * @date:202019-08-01/09:16
 * @email:grs0515@163.com
 */
public class App extends SampleApplication {

    public static String PATCH_URL = "https://gaoruishan.cn:3000/app/tinker/update";
    private static App mInstance;

    public static Context get() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
        mInstance = this;
        //需求请求权限
        TinkerService.runTinkerService(this,PATCH_URL);
        Log.e("TAG","(App.java:22) ");
    }
}
