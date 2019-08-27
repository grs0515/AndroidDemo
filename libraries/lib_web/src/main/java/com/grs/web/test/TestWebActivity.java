package com.grs.web.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.grs.web.R;
import com.grs.web.base.BaseAgentWebFragment;

/**
 * Created by cenxiaozhong on 2017/7/22.
 */

public class TestWebActivity extends AppCompatActivity {

    public static final String TYPE_KEY = "type_key";
    public static final int TYPE_1 = 1;
    public static final int TYPE_2 = 2;
    public static final int TYPE_3 = 3;
    private FrameLayout mFrameLayout;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_common_web);

        mFrameLayout = (FrameLayout) this.findViewById(R.id.container_framelayout);
        int key = getIntent().getIntExtra(TYPE_KEY, -1);
        mFragmentManager = this.getSupportFragmentManager();
        openFragment(key);

    }

    private void openFragment(int key) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        Bundle mBundle = null;
        Fragment mFragment = null;
        switch (key) {
            case TYPE_1:
                ft.add(R.id.container_framelayout, mFragment = JsAgentWebFragment.getInstance(new Bundle()), JsAgentWebFragment.class.getName())
                        .show(mFragment);
                break;
            case TYPE_2:
                ft.add(R.id.container_framelayout, mFragment = SonicAgentWebFragment.create(new Bundle()), SonicAgentWebFragment.class.getName())
                        .show(mFragment);
                break;
            case TYPE_3:
                Bundle bundle = new Bundle();
                bundle.putString(BaseAgentWebFragment.URL_KEY,"file:///android_asset/upload_file/jsuploadfile.html");
                ft.add(R.id.container_framelayout, mFragment = BaseAgentWebFragment.getInstance(bundle), SonicAgentWebFragment.class.getName())
                        .show(mFragment);
                break;
            default:
                break;
        }

        ft.commit();
    }
}
