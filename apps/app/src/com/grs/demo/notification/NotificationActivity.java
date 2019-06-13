package com.grs.demo.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RemoteViews;

import com.grs.demo.R;
import com.grs.demo.UtilTestActivity;
import com.grs.demo.base.BaseActivity;
import com.ycbjie.notificationlib.NotificationUtils;

import org.xutils.view.annotation.ContentView;

/**
 * @author:gaoruishan
 * @date:202019-06-05/10:04
 * @email:grs0515@163.com
 */
@ContentView(R.layout.activity_notification_test)
public class NotificationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void clear(View view) {
        NotificationUtils notificationUtils = new NotificationUtils(this);
        notificationUtils.clearNotification();
    }

    public void simple(View view) {
        NotificationUtils notificationUtils = new NotificationUtils(this);
        notificationUtils.sendNotification(1,"这个是标题","这个是内容",R.mipmap.ic_launcher);

    }
    @UiThread
    public void complex(View view) {
        long[] vibrate = new long[]{0, 500, 1000, 1500};
//处理点击Notification的逻辑
//创建intent
        Intent resultIntent = new Intent(this, UtilTestActivity.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);           //添加为栈顶Activity
        resultIntent.putExtra("what",3);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this,3,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);
//发送pendingIntent

        NotificationUtils notificationUtils = new NotificationUtils(this);
        notificationUtils
                //让通知左右滑的时候是否可以取消通知
                .setOngoing(true)
                //设置内容点击处理intent
                .setContentIntent(resultPendingIntent)
                //设置状态栏的标题
                .setTicker("来通知消息啦")
                //设置自定义view通知栏布局
                .setContent(getRemoteViews())
                //设置sound
                .setSound(android.provider.Settings.System.DEFAULT_NOTIFICATION_URI)
                //设置优先级
                .setPriority(Notification.PRIORITY_DEFAULT)
                //自定义震动效果
                .setVibrate(vibrate)
                //必须设置的属性，发送通知
                .sendNotification(3,"这个是标题3", "这个是内容3", R.mipmap.ic_launcher);
    }
    private RemoteViews getRemoteViews() {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification_mobile_play);
        // 设置 点击通知栏的上一首按钮时要执行的意图
        remoteViews.setOnClickPendingIntent(R.id.btn_pre, getActivityPendingIntent(11));
        // 设置 点击通知栏的下一首按钮时要执行的意图
        remoteViews.setOnClickPendingIntent(R.id.btn_next, getActivityPendingIntent(12));
        // 设置 点击通知栏的播放暂停按钮时要执行的意图
        remoteViews.setOnClickPendingIntent(R.id.btn_start, getActivityPendingIntent(13));
        // 设置 点击通知栏的根容器时要执行的意图
        remoteViews.setOnClickPendingIntent(R.id.ll_root, getActivityPendingIntent(14));
        remoteViews.setTextViewText(R.id.tv_title, "标题");     // 设置通知栏上标题
        remoteViews.setTextViewText(R.id.tv_artist, "艺术家");   // 设置通知栏上艺术家
        return remoteViews;
    }
    /** 获取一个Activity类型的PendingIntent对象 */
    @CheckResult
    private PendingIntent getActivityPendingIntent(int what) {
        Intent intent = new Intent(this, UtilTestActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);           //添加为栈顶Activity
        intent.putExtra("what", what);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, what, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }
}
