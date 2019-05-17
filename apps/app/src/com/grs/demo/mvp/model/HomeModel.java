package com.grs.demo.mvp.model;

import com.grs.demo.base.RequestManager;
import com.grs.demo.base.Response;
import com.grs.demo.base.ServiceAPI;
import com.grs.demo.mvp.bean.CultureBestBean;
import com.grs.demo.mvp.bean.HomeBannerBean;

import org.xutils.common.util.LogUtil;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;

/**
 * Created by gaoruishan on 16/3/21.
 */
public class HomeModel implements IHomeModel {
    @Override
    public void getHomeBannerDates(final Response.onCallBackListener<HomeBannerBean> callBack) {
        RequestParams params = new RequestParams(ServiceAPI.Index.getIndex());
        RequestManager.getInstance().sendGsonRequest(HttpMethod.GET, params, HomeBannerBean.class, new Response.onSuccessListener<HomeBannerBean>() {
            @Override
            public void onSuccess(HomeBannerBean response) {
                LogUtil.e(response.toString());
                callBack.onSuccess(response);

            }
        }, new Response.onErrorListener() {
            @Override
            public void onError(String error) {
                LogUtil.e(error.toString());
                callBack.onError(error);
            }
        });
    }

    @Override
    public void getCultureBest(final Response.onCallBackListener<CultureBestBean> callBack) {
        RequestParams params = new RequestParams(ServiceAPI.Index.getCultureBest());
        RequestManager.getInstance().sendGsonRequest(HttpMethod.GET, params, CultureBestBean.class, new Response.onSuccessListener<CultureBestBean>() {
            @Override
            public void onSuccess(CultureBestBean response) {
                LogUtil.e(response.toString());
                callBack.onSuccess(response);

            }
        }, new Response.onErrorListener() {
            @Override
            public void onError(String error) {
                LogUtil.e(error);
                callBack.onError(error);
            }
        });
    }
}
