package com.grs.demo.drag;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.widget.ImageView;

/**
 * @作者:gaoruishan
 * @时间:2017/2/15/09:39
 * @邮箱:grs0515@163.com
 */

public class AnimUtil {

	private ImageView mImageView;   //播方动画的相应布局
	private int[] mImageRes;
	private int durations;
	private static boolean isStop = false;

	//停止
	public static void stop() {
		isStop = true;
	}

	//开始
	public AnimUtil(ImageView pImageView, int[] pImageRes, int duration) {
		mImageView = pImageView;
		durations = duration;
		mImageRes = pImageRes;
		mImageView.setImageResource(mImageRes[0]);
		isStop = false;//初始化
		play(0);
	}

	//执行动画
	private void play(final int pImageNo) {
		if (isStop) return;
		mImageView.postDelayed(new Runnable() {     //采用延迟启动子线程的方式
			public void run() {
				mImageView.setImageResource(mImageRes[pImageNo]);
				Log.e("play", "run: " + pImageNo);
				if (pImageNo == mImageRes.length - 1) {
					play(0);//重新开始
					return;
				} else {
					play(pImageNo + 1);
				}
			}
		}, durations);
	}

	//获得资源id数组
	public static int[] generateDrawableArray(Activity context, String name, int size) {
		int[] drawaleArr = new int[size];
		Resources res = context.getResources();
		for (int i = 0; i < size; i++) {
			//name:图片的名，defType：资源类型（drawable，string。。。），defPackage:工程的包名
			//xxx01 格式
			String _name = name;
			// TODO 根据需求修改
			if (i <= 9) {
				_name = _name + "0" + i;
			} else {
				_name = _name + i;
			}
			Log.e("_name", "generateDrawableArray: " + _name + "   ,context.getPackageName()=" + context.getPackageName());
			int id = res.getIdentifier(_name, "drawable", context.getPackageName());
			//获得drawable
//			Drawable drawable=res.getDrawable(id);
			drawaleArr[i] = id;
		}
		return drawaleArr;
	}

	//获得资源id
	public static int generateDrawable(Context context, String name, String type) {
		Resources res = context.getResources();
		//name:图片的名，defType：资源类型（drawable，string。。。），defPackage:工程的包名
		int id = res.getIdentifier(name, type, context.getPackageName());
		return id;
	}
}
