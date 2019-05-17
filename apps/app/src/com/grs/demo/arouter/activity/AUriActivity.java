package com.grs.demo.arouter.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.grs.demo.R;
import com.grs.demo.arouter.Router;
import com.grs.demo.base.BaseActivity;

@Route(path = Router.TEST_URI)
public class AUriActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auri);
    }
}
