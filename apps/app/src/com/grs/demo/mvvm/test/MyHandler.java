package com.grs.demo.mvvm.test;

import android.view.View;
import android.widget.Toast;

/**
 * 3,如果你不想把点击事件写在 MainActivity 中，你把它单独写在一个类里面
 * @author:gaoruishan
 * @date:2018/12/6/14:41
 * @email:grs0515@163.com
 */
public class MyHandler {

	public void onClickBtn(View view) {
		Toast.makeText(view.getContext(), "点击事件", Toast.LENGTH_LONG).show();
	}
}
