package com.cmcc.hyapps.KunlunTravel.base;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umeng.analytics.MobclickAgent;

import org.xutils.x;

/**
 * Base fragment, all fragment should extends it.
 *
 * @author Kuloud
 */
public class AppFragment extends Fragment {
    protected static final boolean DEBUG = AppConfig.DEBUG;

    protected String mRequestTag = AppFragment.class.getName();
    private Fragment mFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //接受请求参数
        if (getArguments() != null && getArguments().containsKey(AppConst.ARGS_REQUEST_TAG)) {
            mRequestTag = getArguments().getString(AppConst.ARGS_REQUEST_TAG);
        }else if (DEBUG){
//            throw new RuntimeException("A volley request tag must be provided");
        }
        mFragment = this;

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    /**
     *   统计页面名称，可自定义
     */
    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(getFragmentName());
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(getFragmentName());
    }

    /**
     *  获取fragment名称
     */
    public String getFragmentName(){
        return mFragment.getClass().getSimpleName()+"";
    }

}
