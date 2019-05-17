package com.grs.demo.andfix;

import android.os.Bundle;

import com.grs.demo.R;
import com.grs.demo.base.BaseActivity;
import org.xutils.common.util.LogUtil;

public class AndFixActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_and_fix);
		LogUtil.e(A.a("good"));
		LogUtil.e("" + new A().b("s1", "s2"));
		LogUtil.e("" + new A().getI());
	}
}
