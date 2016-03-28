package com.cmcc.hyapps.KunlunTravel.base;

/**
 * 接口管理类－各种响应接口回调
 * @param <T>
 */
public class Response<T> {

    public interface onSuccessListener<T> {
        void onSuccess(T response);
    }

    public interface onErrorListener {
        void onError(String error);
    }

    public interface onCancelledListener {
        void onCancelled(String error);
    }

    public interface onFinishedListener {
        void onFinished(String error);
    }

    public interface onCallBackListener<T>{
        void onSuccess(T response);
        void onError(String error);
    }
}