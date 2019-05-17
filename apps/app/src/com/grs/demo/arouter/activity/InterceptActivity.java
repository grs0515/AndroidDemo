package com.grs.demo.arouter.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.grs.demo.R;
import com.grs.demo.arouter.Router;
import com.grs.demo.base.BaseActivity;

@Route(path = Router.TEST_INTERCEPT)
public class InterceptActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intercept);
       TextView textView =  findViewById(R.id.tv);
       textView.setText("拦截器和回调的Avtivity");
    }
}
