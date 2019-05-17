package com.grs.demo.web;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.grs.demo.R;
import com.grs.demo.base.BaseActivity;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.AgentWebConfig;
import com.just.agentweb.DefaultWebClient;

/**
 * 对webView封装
 * https://github.com/Justson/AgentWeb
 * @author:gaoruishan
 * @date:2019/1/29/14:25
 * @email:grs0515@163.com
 */
public class WebActivity extends BaseActivity {

    private static String TAG = WebActivity.class.getSimpleName();
    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //do you  work
        }
    };

    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            //do you work
        }
    };
    private AgentWeb mAgentWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        LinearLayout linearLayout = findViewById(R.id.ll_web);
//        String url = getIntent().getStringExtra("URL");
        // 去哪儿
//        String url = "file:///android_asset/com.grs.travel/www/index.html";
        // 小岛
        String url = "file:///android_asset/com.grs.oldsland/www/index.html";
        // 富文本
        if (!TextUtils.isEmpty(url) && !url.startsWith("http") && !url.startsWith("file")) {
            WebView webView = new WebView(this);
            linearLayout.addView(webView);
            //特殊格式
//            if (url.contains("{") && url.contains("}") && url.contains("content") && url.contains("imgUrl")) {
//                HtmlEntity htmlEntity = new Gson().fromJson(url, HtmlEntity.class);
//                url = "<p>" + htmlEntity.title.text + "</p>";
//                for (HtmlEntity.ContentBean b : htmlEntity.content) {
//                    url += "<img src='" + b.imgUrl + "' /> <br/>";
//                }
//            }
            setWebView(webView, url);
        } else {
            initWebView(linearLayout, url);
        }

    }

    /**
     * 加载HTML富文本
     * @param webview
     * @param content
     */
    public static void setWebView(WebView webview, String content) {
        content = stringFilterSpace(content);
        //支持js
        setWebSetting(webview);
        webview.loadData(content, "text/html; charset=UTF-8", null);
    }

    /**
     * 初始AgentWeb
     * @param linearLayout
     * @param url
     */
    private void initWebView(LinearLayout linearLayout, String url) {
        mAgentWeb = AgentWeb.with(this)//
                //传入AgentWeb的父控件。
                .setAgentWebParent(linearLayout, -1, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
                //设置进度条颜色与高度，-1为默认值，高度为2，单位为dp。
                .useDefaultIndicator(-1, 3)
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                //严格模式 Android 4.2.2 以下会放弃注入对象 ，使用AgentWebView没影响。
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                //参数1是错误显示的布局，参数2点击刷新控件ID -1表示点击整个布局都刷新， AgentWeb 3.0.0 加入。
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                //打开其他页面时，弹窗质询用户前往其他应用 AgentWeb 3.0.0 加入。
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.DISALLOW)
                //拦截找不到相关页面的Url AgentWeb 3.0.0 加入。
                .interceptUnkownUrl()
                //创建AgentWeb。
                .createAgentWeb()
                //设置 WebSettings。
                .ready()
                //WebView载入该url地址的页面并显示。
                .go(url);

        AgentWebConfig.debug();

        // AgentWeb 4.0 开始，删除该类以及删除相关的API
//        DefaultMsgConfig.DownloadMsgConfig mDownloadMsgConfig = mAgentWeb.getDefaultMsgConfig().getDownloadMsgConfig();
        //  mDownloadMsgConfig.setCancel("放弃");  // 修改下载提示信息，这里可以语言切换

        // AgentWeb 没有把WebView的功能全面覆盖 ，所以某些设置 AgentWeb 没有提供 ， 请从WebView方面入手设置。
        mAgentWeb.getWebCreator().getWebView().setOverScrollMode(WebView.OVER_SCROLL_NEVER);

//		mAgentWeb.getWebCreator().getWebView().setOnLongClickListener();


        mAgentWeb.getWebCreator().getWebView().getSettings().setJavaScriptEnabled(true);
//        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        //优先使用网络
        mAgentWeb.getWebCreator().getWebView().getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //将图片调整到适合webview的大小
        mAgentWeb.getWebCreator().getWebView().getSettings().setUseWideViewPort(true);
        //支持内容重新布局
        mAgentWeb.getWebCreator().getWebView().getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //支持自动加载图片
        mAgentWeb.getWebCreator().getWebView().getSettings().setLoadsImagesAutomatically(true);
        //当webview调用requestFocus时为webview设置节点
        mAgentWeb.getWebCreator().getWebView().getSettings().setNeedInitialFocus(true);
        //自适应屏幕
        mAgentWeb.getWebCreator().getWebView().getSettings().setUseWideViewPort(true);
        mAgentWeb.getWebCreator().getWebView().getSettings().setLoadWithOverviewMode(true);
        //开启DOM storage API功能（HTML5 提供的一种标准的接口，主要将键值对存储在本地，在页面加载完毕后可以通过 javascript 来操作这些数据。）
        mAgentWeb.getWebCreator().getWebView().getSettings().setDomStorageEnabled(true);
        //支持缩放
        mAgentWeb.getWebCreator().getWebView().getSettings().setBuiltInZoomControls(true);
        mAgentWeb.getWebCreator().getWebView().getSettings().setSupportZoom(true);

        //允许webview对文件的操作
        mAgentWeb.getWebCreator().getWebView().getSettings().setAllowFileAccess(true);
        mAgentWeb.getWebCreator().getWebView().getSettings().setAllowFileAccessFromFileURLs(true);
        mAgentWeb.getWebCreator().getWebView().getSettings().setAllowUniversalAccessFromFileURLs(true);
        mAgentWeb.getWebCreator().getWebView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    // 表示按返回键时的操作
                    if (keyCode == KeyEvent.KEYCODE_BACK && mAgentWeb.getWebCreator().getWebView().canGoBack()) {
                        // 后退
                        mAgentWeb.getWebCreator().getWebView().goBack();
                        // webview.goForward();//前进
                        return true;
                    } else if (keyCode == KeyEvent.KEYCODE_BACK) {
                        WebActivity.this.finish();
                    }
                }
                return false;
            }
        });
    }

    /**
     * 解决空白问题的代码
     * @param content
     * @return
     */
    private static String stringFilterSpace(String content) {
        if (!content.contains("html")) {
            String head = "<head>" +
                    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                    "<style>*{margin:0;padding:0;}img{max-width: 100%; width:auto; height:auto;}</style>" +
                    "</head>";
            return "<html>" + head + "<body>" + content + "</body></html>";
        }

        return content;
    }

    /**
     * 设置web属性
     * @param webview
     */
    public static void setWebSetting(WebView webview) {
        //支持js
        webview.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放
        webview.getSettings().setSupportZoom(true);
        // 显示放大缩小
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setDisplayZoomControls(false);
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        //取消滚动条白边效果
        webview.setWebChromeClient(new WebChromeClient());
        webview.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.e(TAG, "(WebActivity.java:207) " + url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                //super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }
        });

        webview.setWebViewClient(new WebViewClient());
        //设置默认为utf-8
        webview.getSettings().setDefaultTextEncodingName("UTF-8");
        webview.getSettings().setBlockNetworkImage(false);
        //扩大比例的缩放
        webview.getSettings().setUseWideViewPort(true);
        //自适应屏幕
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webview.getSettings().setLoadWithOverviewMode(true);

        // 解决对某些标签的不支持出现白屏
        webview.getSettings().setDomStorageEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //注意安卓5.0以上的权限
            webview.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }

    @Override
    protected void onResume() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onResume();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onPause();
        }
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onDestroy();
        }
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb != null && mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
