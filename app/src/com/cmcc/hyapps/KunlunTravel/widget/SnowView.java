package com.cmcc.hyapps.KunlunTravel.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.cmcc.hyapps.KunlunTravel.R;

import java.util.Random;

/**
 * 自定义下雪花View - 直接调用snow.startSnow(....);
 */
public class SnowView extends View {
    // 画笔
    private final Paint mPaint = new Paint();
    // 随即生成器
    private static final Random random = new Random();
    private Activity context;
    // 花的位置
    private Coordinate[] snows = new Coordinate[100];
    // 屏幕的高度和宽度
    private int view_height = 0;
    private int view_width = 0;
    private int snowNum = 30;
    private int speed = 8;
    private int view_drawable;
    private boolean togged;
    private int delaymillisecond=500;

    /**
     * 构造器
     */
    public SnowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SnowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置当前窗体的实际高度和宽度
     */
    public void setViewWH(int height, int width) {
        view_height = height - 100;
        view_width = width;
    }
    /**
     * 设置当前雪花飘动
     */
    public void setWaveAnim(boolean togged) {
        this.togged = togged;
    }

    /**
     * 开始初始化雪花
     */
    public void startSnow(Activity activity,@DrawableRes int view_drawable, int number, int speed,int delaymillisecond) {
        context = activity;
        this.view_drawable = view_drawable;
        if (speed>0)this.speed = speed;
        this.delaymillisecond=delaymillisecond;
        initSnow(number);
    }
    public void startSnow(Activity activity,@DrawableRes int view_drawable) {
        context = activity;
        this.view_drawable = view_drawable;
        initSnow(snowNum);
    }

    /**
     * 随机的生成雪花的位置 和速度
     */
    public void addRandomSnow(int number, int speed) {
        this.speed = speed;
        initSnow(number);
    }

    private void initSnow(int number) {
        if (view_width == 0 || view_height == 0) {
            // 获取当前屏幕的高和宽
            DisplayMetrics dm = new DisplayMetrics();
            context.getWindowManager().getDefaultDisplay().getMetrics(dm);
            setViewWH(dm.heightPixels, dm.widthPixels);
        }
        if (number != 0) snowNum = number;
        for (int i = 0; i < number; i++) {
            snows[i] = new Coordinate(random.nextInt(view_width), -random.nextInt(view_height));
        }
        mRedrawHandler.sleep(delaymillisecond);
    }

    /* 内嵌坐标对象 */
    private class Coordinate {
        public int x;
        public int y;

        public Coordinate(int newX, int newY) {
            x = newX;
            y = newY;
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int x = 0; x < snowNum; x += 1) {
            if (snows[x].y >= view_height) {
                snows[x].y = 0;
            }
            // 雪花下落的速度
            snows[x].y += speed;
            // 雪花飘动的效果
            if (togged) {
                setWaveAnim(x);
            }
            Resources mResources = getResources();
            if (view_drawable != 0) {
                canvas.drawBitmap(((BitmapDrawable) mResources.getDrawable(view_drawable)).getBitmap(), ((float) snows[x].x),
                        ((float) snows[x].y), mPaint);
            } else {
                canvas.drawBitmap(((BitmapDrawable) mResources.getDrawable(R.drawable.ic_launcher)).getBitmap(), ((float) snows[x].x),
                        ((float) snows[x].y), mPaint);
            }
        }

    }

    /**
     * 雪花飘动的效果
     *
     * @param x
     */
    private void setWaveAnim(int x) {
        if (random.nextBoolean()) {
            // 随机产生一个数字，让雪花有水平移动的效果
            int ran = random.nextInt(10);
            snows[x].x += 2 - ran;
            if (snows[x].x < 0) {
                snows[x].x = view_width;
            } else if (snows[x].x > view_width) {
                snows[x].x = 0;
            }
        }
    }

    /**
     * 负责做界面更新工作 ，实现下雪
     */
    private RefreshHandler mRedrawHandler = new RefreshHandler();

    class RefreshHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            //snow.addRandomSnow();
            invalidate();
            sleep(100);
        }

        public void sleep(long delayMillis) {
            this.removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMillis);
        }
    }
}
