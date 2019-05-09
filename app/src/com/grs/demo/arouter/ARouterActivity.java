package com.grs.demo.arouter;

import android.os.Bundle;
import android.view.View;

import com.grs.demo.R;
import com.grs.demo.base.BaseActivity;


public class ARouterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arouter);
    }

    public void toATest(View view) {
        Router.toTest();
    }

    public void toATestParam(View view) {
        Router.toTestParam();
    }

    public void toUri(View view) {
        Router.toUri();
    }

    public void toIntercept(View view) {
        Router.toInterceptActivity(this);
    }
}
