package com.grs.demo.arouter.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.grs.demo.R;
import com.grs.demo.arouter.Router;
import com.grs.demo.arouter.bean.RouterTestObject;
import com.grs.demo.base.BaseActivity;

@Route(path = Router.TEST_PARAM_URL)
public class ATestParamActivity extends BaseActivity {

    @Autowired()
    long key1;

    @Autowired()
    String key2;

    @Autowired()
    RouterTestObject key3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atest_param);
        TextView tv = findViewById(R.id.tv);
        tv.setText(key1 + ", " + key2 + ", " + key3.getName());
        Log.e(TAG, "(ATestParamActivity.java:27) " + key1 + ", " + key2 + ", " + key3.getName());
    }
}
