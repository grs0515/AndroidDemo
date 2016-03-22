package com.cmcc.hyapps.KunlunTravel.base;

import android.content.Context;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

/**
 * http请求类 单例设计模式
 * Created by gaoruishan on 16/1/4.
 */
public final class RequestManager {
    private static RequestManager sInstance = new RequestManager();
    private Context mContext;
    private RequestParams params;
    private final static Map<String,Callback.Cancelable>  map = new HashMap<String,Callback.Cancelable>();
    private RequestManager() {
         /* cannot be instantiated */
//        throw new UnsupportedOperationException("cannot be instantiated");
    }


    public static RequestManager getInstance() {
        return sInstance;
    }

    public void init(Context context) {
        mContext = context;
        params = new RequestParams();
    }

    public <T> Callback.Cancelable sendGsonRequest(HttpMethod method, RequestParams requestParams, Class<T> cls, Response.onSuccessListener<T> listener,
                                                   Response.onErrorListener errorListener) {
        return this.sendGsonRequest(method, null, requestParams, cls, listener, errorListener);
    }

    public <T> Callback.Cancelable sendGsonRequest(HttpMethod method, String url, RequestParams requestParams, Class<T> cls, final Response.onSuccessListener<T> listener,
                                                   final Response.onErrorListener errorListener) {
        Callback.Cancelable cancelable = null;
        RequestParams params = null;
        if (requestParams != null) {
            params = requestParams;
        } else {
            params = new RequestParams(url);
        }
        // 设置固定参数 params.addParameter("cityname", "海淀");
        //params.setHeader("");
        params.setConnectTimeout(5000);//超时
        if (url == null) LogUtil.e("URL=" + params.getUri());
        else LogUtil.e("URL=" + url);
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
     * @param params
     */
    private <T> Callback.Cancelable get(RequestParams params, final Class<T> cls, final Response.onSuccessListener<T> listener,
                                        final Response.onErrorListener errorListener) {
        Callback.Cancelable cancelable = x.http().get(params, new Callback.CommonCallback<String>() {
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
    /**
     * 异步post
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
