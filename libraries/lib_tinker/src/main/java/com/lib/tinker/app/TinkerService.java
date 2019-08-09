package com.lib.tinker.app;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.lib.tinker.http.PatchInfo;
import com.lib.tinker.http.RequestManager;
import com.lib.tinker.http.Response;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;

import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;

import java.io.File;

/**
 * @function 应用程序Tinker更新服务：
 * 1.从服务器下载patch文件
 * 2.使用TinkerManager完成patch文件加载
 * 3.patch文件会在下次进程启动时生效
 */
public class TinkerService extends Service {
    private static final String FILE_END = ".apk"; //文件后缀名
    private static final int DOWNLOAD_PATCH = 0x01; //下载patch文件信息
    private static final int UPDATE_PATCH = 0x02; //检查是否有patch更新
    private static final String TAG = "TinkerService";
    //更新URL
    public static String PATCH_URL = "";
    private String mPatchFileDir; //patch要保存的文件夹
    private String mFilePtch; //patch文件保存路径
    private PatchInfo mBasePatchInfo; //服务器patch信息
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case UPDATE_PATCH:
                    checkPatchInfo();
                    break;
                case DOWNLOAD_PATCH:
                    downloadPatch();
                    break;
            }
        }
    };

    /**
     * 对外提供启动servcie方法
     */
    public static void runTinkerService(Context context,String url) {
        PATCH_URL = url;
        try {
            Intent intent = new Intent(context, TinkerService.class);
            context.startService(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 结束进程
     * @param context
     */
    public static void killProcess(Context context) {
        ShareTinkerInternals.killAllOtherProcess(context.getApplicationContext());
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //检查是否有patch更新
        mHandler.sendEmptyMessage(UPDATE_PATCH);
        return START_NOT_STICKY; //被系统回收不再重启
    }

    //用来与被启动者通信的接口
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //初始化变量
    private void init() {
        mPatchFileDir = Environment.getExternalStorageDirectory().getPath() + "/tpatch/";
        File patchFileDir = new File(mPatchFileDir);
        try {
            if (patchFileDir == null || !patchFileDir.exists()) {
                patchFileDir.mkdir(); //文件夹不存在则创建
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "(TinkerService.java:107) init ");
            stopSelf(); //无法正常创建文件，则终止服务
        }
    }


    private void checkPatchInfo() {
        if(TextUtils.isEmpty(PATCH_URL)){
            Log.e(TAG,"(TinkerService.java:113) 请配置PATCH_URL");
        	return;
        }
        RequestParams params = new RequestParams(PATCH_URL);
        RequestManager.getInstance().sendGsonRequest(HttpMethod.GET, params, PatchInfo.class, new Response.onSuccessListener<PatchInfo>() {
            @Override
            public void onSuccess(PatchInfo response) {
                Log.e(TAG, "(TinkerService.java:107) checkPatchInfo onSuccess");
                if (response.getData() != null && !TextUtils.isEmpty(response.getData().getDownloadUrl())) {
                    mBasePatchInfo = response;
                    mHandler.sendEmptyMessage(DOWNLOAD_PATCH);
                }
            }
        }, new Response.onErrorListener() {
            @Override
            public void onError(String error) {
                Log.e(TAG, "(TinkerService.java:107) checkPatchInfo onFailure");
                stopSelf();
            }
        });
    }

    private void downloadPatch() {
        mFilePtch = mPatchFileDir.concat(String.valueOf(System.currentTimeMillis()))
                .concat(FILE_END);
        RequestManager.getInstance().sendFileRequest(mBasePatchInfo.getData().getDownloadUrl(), mFilePtch, new RequestManager.FileCallback() {
            @Override
            public void onSuccess(File result) {
                TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), mFilePtch);
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                stopSelf();
            }
        });
    }
}
