package com.grs.demo.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

import com.grs.demo.R;
import com.grs.demo.base.BaseActivity;

public class TestDragActivity extends BaseActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_drag);
		Button button = (Button)findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		//创建悬浮窗口
		presenter.create();

		getWindow().getDecorView();
		PopupWindow popupWindow = new PopupWindow();
//		popupWindow.showAtLocation(getWindow().getDecorView().getRootView());
//		popupWindow.showAsDropDown();
	}

	FloatPresenter presenter = new FloatPresenter(this);


	@Override
	public void onDestroy() {
		super.onDestroy();
		presenter.destory();
	}
}
