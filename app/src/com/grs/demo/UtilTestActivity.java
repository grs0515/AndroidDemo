package com.grs.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.grs.demo.annotation.FindEvent;
import com.grs.demo.annotation.FindFragment;
import com.grs.demo.annotation.FindUtil;
import com.grs.demo.annotation.FindView;
import com.grs.demo.fragment.TestFragment;
import com.grs.demo.utils.common.RestartAPPTool;

/**
 * @author:gaoruishan
 * @date:2018/12/13/11:22
 * @email:grs0515@163.com
 */
public class UtilTestActivity extends AppCompatActivity  {

    private static final String TAG = UtilTestActivity.class.getSimpleName();
    @FindView(R.id.tv)
    TextView tv;
    @FindFragment(R.id.container_fragment)
    TestFragment fragment;
    @FindView(R.id.btn)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_util_test);
        FindUtil.inject(this);
        button.setText("测试11");
        tv.setText("测试22");
//
        // 开启fragment
//		FindViewUtil.addFragment(this,R.id.container_fragment, TestFragment.class);
    }

    @FindEvent(R.id.btn_rest)
    public void doRestart() {
        RestartAPPTool.restartAPP(this);
    }

    @FindEvent(R.id.btn)
    public void btnEvent() {
        Button button = fragment.getView().findViewById(R.id.btn);
        Log.e(TAG, "(UtilTestActivity.java:52) " + button.getText().toString());
    }
}
