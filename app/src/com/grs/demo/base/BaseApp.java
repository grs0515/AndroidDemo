package com.grs.demo.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.multidex.MultiDexApplication;
import android.util.Log;
import android.view.WindowManager;

import com.alipay.euler.andfix.patch.PatchManager;
import com.grs.demo.utils.common.ToastUtils;
import com.grs.demo.utils.netstate.NetChangeObserver;
import com.grs.demo.utils.netstate.NetWorkUtil;
import com.grs.demo.utils.netstate.NetworkStateReceiver;
import com.iflytek.cloud.SpeechUtility;

import org.xutils.common.util.LogUtil;
import org.xutils.x;

import java.io.IOException;



/**
 * Application 单例模式 生命周期最长,可进行初始化,数据传递,共享等
 * Created by gaoruishan on 15/12/2.
 */
public class BaseApp extends MultiDexApplication {
	private static final String APATCH_PATH = "/out.apatch";
	private static final String TAG = getBaseApp().getClass().getSimpleName();
	public static String mAuth_id;
	public static Context mContext;
	public static BaseApp mInstance;
	private PatchManager mPatchManager;
	/**
	 * 创建全局变量
	 * 全局变量一般都比较倾向于创建一个单独的数据类文件，并使用static静态变量
	 *
	 * 这里使用了在Application中添加数据的方法实现全局变量
	 * 注意在AndroidManifest.xml中的Application节点添加android:name=".MyApplication"属性
	 *
	 */
	private WindowManager.LayoutParams wmParams=new WindowManager.LayoutParams();
	private Activity mCurrentActivity;

	public WindowManager.LayoutParams getMywmParams(){
		return wmParams;
	}
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
		//初始化热修复
		initAndFix();

		registerActivityLifecycleCallbacks(callback);

	}
	public Activity getCurrentActivity() {
		return mCurrentActivity;
	}

	ActivityLifecycleCallbacks callback = new ActivityLifecycleCallbacks() {
		@Override
		public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
			Log.e(TAG, "onActivityCreated: "+activity.getClass().getSimpleName());
		}

		@Override
		public void onActivityStarted(Activity activity) {
			Log.e(TAG, "onActivityStarted: ");
		}

		@Override
		public void onActivityResumed(Activity activity) {
			mCurrentActivity = activity;
			Log.e(TAG, "onActivityResumed: "+activity.getClass().getSimpleName());
		}

		@Override
		public void onActivityPaused(Activity activity) {
			Log.e(TAG, "onActivityPaused: ");
		}

		@Override
		public void onActivityStopped(Activity activity) {
			Log.e(TAG, "onActivityStopped: ");
		}

		@Override
		public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
			Log.e(TAG, "onActivitySaveInstanceState: ");
		}

		@Override
		public void onActivityDestroyed(Activity activity) {
			Log.e(TAG, "onActivityDestroyed: ");
		}
	};

	private void initAndFix() {
		// initialize
		mPatchManager = new PatchManager(this);
		mPatchManager.init("1.0");//current version
		LogUtil.e("inited.");

		// load patch
		mPatchManager.loadPatch();
		LogUtil.e("apatch loaded.");

		// add patch at runtime
		try {
			// .apatch file path
			String patchFileString = Environment.getExternalStorageDirectory()
					.getAbsolutePath() + APATCH_PATH;
			mPatchManager.addPatch(patchFileString);
			LogUtil.e("apatch:" + patchFileString + " added.");
		} catch (IOException e) {
			LogUtil.e("", e);
		}
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
	 * @return
	 */
	public static Context getContext() {
		return mContext;
	}

	public static BaseApp getBaseApp() {
		if (mInstance == null) {//加同步锁更安全
			synchronized (BaseApp.class) {
				if (mInstance == null) {
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

