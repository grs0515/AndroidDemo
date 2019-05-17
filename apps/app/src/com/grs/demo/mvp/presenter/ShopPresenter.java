package com.grs.demo.mvp.presenter;

import android.content.Context;

import com.grs.demo.base.AppConst;
import com.grs.demo.base.RequestManager;
import com.grs.demo.base.Response;
import com.grs.demo.base.ServiceAPI;
import com.grs.demo.mvp.bean.ShopBanner;
import com.grs.demo.mvp.bean.ShopRecommend;
import com.grs.demo.mvp.view.IShopView;

import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

/**
 * 连接Model和View,处理主要的逻辑代码
 * Created by gaoruishan on 16/7/8.
 */
public class ShopPresenter {

    private final IShopView mIShopView;
    private final Context mContext;
    public List<ShopBanner.ResultsEntity> shopBannerResults = new ArrayList<>();
    public List<ShopRecommend.ResultsEntity> shopRecommendResults = new ArrayList<>();
    public int mRecommendTotalCount;

    public ShopPresenter(IShopView mIShopView) {
        this.mIShopView = mIShopView;
        this.mContext = mIShopView.getContext();
    }


    public void initBannerData() {
        RequestParams params = new RequestParams(ServiceAPI.Shop.getBanner());
        RequestManager.getInstance().sendGsonRequest(HttpMethod.GET, params, ShopBanner.class, new Response.onSuccessListener<ShopBanner>() {
            @Override
            public void onSuccess(ShopBanner response) {
                if (response != null) {
                    shopBannerResults = response.getResults();
                    mIShopView.initDataSuccess(AppConst.SHOP_BANNER);
                }
            }
        }, new Response.onErrorListener() {
            @Override
            public void onError(String error) {
                mIShopView.initDataFailed(AppConst.SHOP_BANNER);
            }
        });
    }

    public void initRecommendData(int offset) {
        RequestParams params = new RequestParams(ServiceAPI.Shop.getRecommend(ServiceAPI.PARAM_RECOMMEND));
        params.addParameter(ServiceAPI.PARAM_LIMIT,AppConst.LIMIT);
        params.addParameter(ServiceAPI.PARAM_OFFSET,offset);
        RequestManager.getInstance().sendGsonRequest(HttpMethod.GET, params, ShopRecommend.class, new Response.onSuccessListener<ShopRecommend>() {
            @Override
            public void onSuccess(ShopRecommend response) {
                if (response != null) {
                    shopRecommendResults = response.getResults();
                    mRecommendTotalCount = response.getCount();
                    mIShopView.initDataSuccess(AppConst.SHOP_RECOMMEND);
                }
            }
        }, new Response.onErrorListener() {
            @Override
            public void onError(String error) {
                mIShopView.initDataFailed(AppConst.SHOP_RECOMMEND);
            }
        });
    }
}
