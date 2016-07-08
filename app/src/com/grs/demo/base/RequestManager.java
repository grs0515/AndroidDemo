package com.grs.demo.base;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

/**
 * http请求类－单例设计模式
 * Created by gaoruishan on 16/1/4.
 */
public final class RequestManager {
    public static final int TIMEOUT = 5000;
    private static RequestManager mInstance = new RequestManager();
    private final static Map<String, Callback.Cancelable> map = new HashMap<String, Callback.Cancelable>();

    private RequestManager() {
         /* cannot be instantiated */
//        throw new UnsupportedOperationException("cannot be instantiated");
    }


    public static RequestManager getInstance() {
        return mInstance;
    }


    public <T> Callback.Cancelable sendGsonRequest(HttpMethod method, RequestParams requestParams, Class<T> cls, Response.onSuccessListener<T> listener,
                                                   Response.onErrorListener errorListener) {
        return this.sendGsonRequest(method, null, requestParams, cls, listener, errorListener);
    }

    /**
     * 向服务器发送网络请求
     *
     * @param method        请求类型
     * @param url           服务器URL
     * @param requestParams 请求参数
     * @param cls           请求实体
     * @param listener      成功监听
     * @param errorListener 错误监听
     * @param <T>           返回当前实体
     * @return
     */
    public <T> Callback.Cancelable sendGsonRequest(HttpMethod method, String url, RequestParams requestParams, Class<T> cls, final Response.onSuccessListener<T> listener,
                                                   final Response.onErrorListener errorListener) {
        Callback.Cancelable cancelable = null;
        RequestParams params;
        if (requestParams != null) {//判断是否有请求参数
            params = requestParams;
        } else {
            params = new RequestParams(url);
        }
        // 设置固定参数 params.addParameter("cityname", "海淀");
        //params.setHeader("");
        params.setConnectTimeout(TIMEOUT);//设置超时时间
        if (url == null) {//打印URL
            LogUtil.e(params.getUri());
        } else {
            LogUtil.e(url);
        }
        if (method == HttpMethod.GET) {
            params.setMethod(HttpMethod.GET);
            cancelable = get(params, cls, listener, errorListener);
        } else if (method == HttpMethod.POST) {
            params.setMethod(HttpMethod.POST);
            cancelable = post(params, cls, listener, errorListener);
        }
        return cancelable;
    }

    /**
     * 异步get
     *
     * @param params
     */
    private <T> Callback.Cancelable get(RequestParams params, final Class<T> cls, final Response.onSuccessListener<T> listener,
                                        final Response.onErrorListener errorListener) {
        Callback.Cancelable cancelable = x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e(result);
                Gson gson = new Gson();
                T t = gson.fromJson(result, cls);
                listener.onSuccess(t);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                errorListener.onError(ex.toString() + ",isOnCallback is " + isOnCallback);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        return cancelable;
    }

    /**
     * 异步post
     *
     * @param params
     */
    private <T> Callback.Cancelable post(RequestParams params, final Class<T> cls, final Response.onSuccessListener<T> listener,
                                         final Response.onErrorListener errorListener) {
        Callback.Cancelable cancelable = x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                T t = gson.fromJson(result, cls);
                listener.onSuccess(t);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                errorListener.onError(ex.toString() + ",isOnCallback is " + isOnCallback);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        return cancelable;
    }

}
