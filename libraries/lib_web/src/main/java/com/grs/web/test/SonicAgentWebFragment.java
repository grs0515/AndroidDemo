package com.grs.web.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.grs.web.base.BaseAgentWebFragment;
import com.grs.web.sonic.SonicImpl;
import com.grs.web.sonic.SonicJavaScriptInterface;
import com.just.agentweb.MiddlewareWebClientBase;

import static com.grs.web.sonic.SonicJavaScriptInterface.PARAM_CLICK_TIME;

/**
 * Created by cenxiaozhong on 2017/12/18.
 *
 * If you wanna use VasSonic to fast open first page , please
 * follow as sample to update your code;
 */

public class SonicAgentWebFragment extends BaseAgentWebFragment {
    private SonicImpl mSonicImpl;

    public static SonicAgentWebFragment create(Bundle bundle){

        SonicAgentWebFragment mSonicAgentWebFragment =new SonicAgentWebFragment();
        if(bundle!=null){
            mSonicAgentWebFragment.setArguments(bundle);
        }
        return mSonicAgentWebFragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        String string = this.getArguments().getString(URL_KEY);
        // 1. 首先创建SonicImpl
        mSonicImpl = new SonicImpl(getUrl(), this.getContext());
        // 2. 调用 onCreateSession
        mSonicImpl.onCreateSession();
        //3. 创建AgentWeb ，注意创建AgentWeb的时候应该使用加入SonicWebViewClient中间件
        super.onViewCreated(view, savedInstanceState);
        // 创建 AgentWeb 注意的 go("") 传入的 mUrl 应该null 或者""
        //4. 注入 JavaScriptInterface
        mAgentWeb.getJsInterfaceHolder().addJavaObject("sonic",
                new SonicJavaScriptInterface(
                        mSonicImpl.getSonicSessionClient(),
                        new Intent().putExtra(PARAM_CLICK_TIME,getArguments().getLong(PARAM_CLICK_TIME))
                                .putExtra("loadUrlTime", System.currentTimeMillis())));
        //5. 最后绑定AgentWeb
        mSonicImpl.bindAgentWeb(mAgentWeb);

    }

    //在步骤3的时候应该传入给AgentWeb
    @Override
    public MiddlewareWebClientBase getMiddlewareWebClient() {
        return mSonicImpl.createSonicClientMiddleWare();
    }

    //getUrl 应该为null
    @Override
    public String getUrl() {
//        return  "file:///android_asset/js_interaction/hello.html";
        return "http://www.jd.com/";
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //销毁SonicSession
        if(mSonicImpl !=null){
            mSonicImpl.destrory();
        }
    }
}
