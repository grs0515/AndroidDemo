package com.grs.demo.base;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.grs.demo.utils.common.ToastUtils;
import com.grs.demo.utils.netstate.NetChangeObserver;
import com.grs.demo.utils.netstate.NetWorkUtil;
import com.grs.demo.utils.netstate.NetworkStateReceiver;
import com.iflytek.cloud.SpeechUtility;

import org.xutils.common.util.LogUtil;
import org.xutils.x;



/**
 * Application 单例模式 生命周期最长,可进行初始化,数据传递,共享等
 * Created by gaoruishan on 15/12/2.
 */
public class BaseApp extends MultiDexApplication {
    public static String mAuth_id;
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
        // 注意： appid 必须和下载的SDK保持一致，否则会出现10407错误
        SpeechUtility.createUtility(this, "appid=" + "582e754e");

        // 以下语句用于设置日志开关（默认开启），设置成false时关闭语音云SDK日志打印
        // Setting.setShowLog(false);

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
        if (mInstance==null){//加同步锁更安全
            synchronized (BaseApp.class){
                if (mInstance==null){
                    mInstance = new BaseApp();
                }
            }
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

