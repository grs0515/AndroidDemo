package com.grs.demo.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.grs.demo.IndexActivity;
import com.grs.demo.R;
import com.grs.demo.base.BaseActivity;
import com.grs.demo.test.photo.PhotoActivity;

public class ClickActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_click);

	}

	public void doIndex(View view) {
		startActivity(new Intent(this, IndexActivity.class));
	}

	public void doPhoto(View view) {
		startActivity(new Intent(this, PhotoActivity.class));
	}

	public void doOther(View view) {
		startActivity(new Intent(this, IndexActivity.class));
	}
}
