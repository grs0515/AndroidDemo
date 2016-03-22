package com.cmcc.hyapps.KunlunTravel.home.model;

import com.cmcc.hyapps.KunlunTravel.base.RequestManager;
import com.cmcc.hyapps.KunlunTravel.base.Response;
import com.cmcc.hyapps.KunlunTravel.base.ServiceAPI;
import com.cmcc.hyapps.KunlunTravel.home.bean.HomeBean;

import org.xutils.common.util.LogUtil;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;

/**
 * Created by gaoruishan on 16/3/21.
 */
public class HomeModel implements IHomeModel {
    @Override
    public void getHomeDates(final Response.onCallBackListener<HomeBean> callBack) {
        RequestParams params = new RequestParams(ServiceAPI.Index.getIndex());
        RequestManager.getInstance().sendGsonRequest(HttpMethod.GET, params, HomeBean.class, new Response.onSuccessListener<HomeBean>() {
            @Override
            public void onSuccess(HomeBean response) {
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
}
