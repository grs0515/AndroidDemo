package com.grs.demo.test;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.WindowManager;

import com.grs.demo.R;
import com.grs.demo.base.BaseApp;
import com.grs.demo.widget.imageview.FloatImageView;

/**
 * @作者:gaoruishan
 * @时间:2017/2/17/14:20
 * @邮箱:grs0515@163.com
 */

public class FloatPresenter {

	private final Activity activity;
	private WindowManager wm = null;
	private WindowManager.LayoutParams wmParams = null;

	private FloatImageView myFV = null;

	public FloatPresenter(Activity activity) {
		this.activity = activity;
	}

	//开始时创建
	public void create() {
		myFV = new FloatImageView(activity.getApplicationContext());
		myFV.setImageResource(R.drawable.iv_default);  //这里简单的用自带的Icom来做演示
		//获取WindowManager
		wm = (WindowManager) activity.getApplicationContext().getSystemService("window");
		//设置LayoutParams(全局变量）相关参数
		wmParams = ((BaseApp) activity.getApplication()).getMywmParams();

		/**
		 *以下都是WindowManager.LayoutParams的相关属性
		 * 具体用途可参考SDK文档
		 */
		wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;   //设置window type
		wmParams.format = PixelFormat.RGBA_8888;   //设置图片格式，效果为背景透明

		//设置Window flag
		wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
				| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
		/*
		 * 下面的flags属性的效果形同“锁定”。
         * 悬浮窗不可触摸，不接受任何事件,同时不影响后面的事件响应。
         wmParams.flags=LayoutParams.FLAG_NOT_TOUCH_MODAL
                               | LayoutParams.FLAG_NOT_FOCUSABLE
                               | LayoutParams.FLAG_NOT_TOUCHABLE;
        */


		wmParams.gravity = Gravity.LEFT | Gravity.TOP;   //调整悬浮窗口至左上角，便于调整坐标
		//以屏幕左上角为原点，设置x、y初始值
		wmParams.x = 0;
		wmParams.y = 0;

		//设置悬浮窗口长宽数据
		wmParams.width = 140;
		wmParams.height = 140;

		//显示myFloatView图像
		wm.addView(myFV, wmParams);

	}
	//在程序退出(Activity销毁）时销毁悬浮窗口
	public void destory() {
		if (wm != null && myFV != null) {
			wm.removeView(myFV);
		}
	}
}
