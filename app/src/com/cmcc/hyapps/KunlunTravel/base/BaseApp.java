package com.cmcc.hyapps.KunlunTravel.base;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.cmcc.hyapps.KunlunTravel.utils.common.ToastUtils;
import com.cmcc.hyapps.KunlunTravel.utils.netstate.NetChangeObserver;
import com.cmcc.hyapps.KunlunTravel.utils.netstate.NetWorkUtil;
import com.cmcc.hyapps.KunlunTravel.utils.netstate.NetworkStateReceiver;

import org.xutils.common.util.LogUtil;
import org.xutils.x;



/**
 * Created by gaoruishan on 15/12/2.
 */
public class BaseApp extends MultiDexApplication {

    public static Context mContext;
    public static BaseApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
        initApp();
        //切换模式: DEBUG 测试用 或 RELAESE上线用
        switchDebug(AppConfig.DEBUG);
        //注册
        registerApp();
    }

    /**
     * 注册和开启
     */
    private void registerApp() {
        //开启服务
//        startService(new Intent(getApplicationContext(), AMapLocationService.class));
        // 注册网络监听
        NetworkStateReceiver.registerNetworkStateReceiver(this);
        NetworkStateReceiver.registerObserver(observer);
    }
    public MyNetChngeOberver observer = new MyNetChngeOberver();
    public class MyNetChngeOberver implements NetChangeObserver {

        @Override
        public void onConnect(NetWorkUtil.NetType type) {
            LogUtil.e("onConnect");
        }

        @Override
        public void onDisConnect() {
            LogUtil.e("onDisConnect");
        }
    }
    /**
     * 切换 debug
     *
     * @param debug
     */
    private void switchDebug(boolean debug) {
        x.Ext.setDebug(debug);
        ToastUtils.isShow = debug;
        ServiceAPI.switchServer(debug);
    }

    /**
     * 初始化
     */
    private void initApp() {
        mInstance = this;
        mContext = getApplicationContext();
        x.Ext.init(this);
    }

    /**
     * 获取上下文和类
     *
     * @return
     */
    public static Context getContext() {
        return mContext;
    }

    public static BaseApp getBaseApp() {
        if (mInstance==null){
            mInstance = new BaseApp();
        }
        return mInstance;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        //注销网络状态
        NetworkStateReceiver.removeRegisterObserver(observer);
        NetworkStateReceiver.unRegisterNetworkStateReceiver(this);
    }
}

