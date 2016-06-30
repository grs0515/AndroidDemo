package com.cmcc.hyapps.KunlunTravel.mvp.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.bigkoo.convenientbanner.ConvenientBanner;

/**
 * Activity需要View实现的接口，与Presenter进行交互
 * Created by gaoruishan on 16/3/21.
 */
public interface IHomeView {
    void initDatasSuccess();

    void initDatasFailed();

    ConvenientBanner getConvenientBanner();

    RecyclerView getVideoRecyclerView();
    RecyclerView getBestRecyclerView();

    Context getContext();

    void startToVideoActivty(int id,String path);

    void startToDetailActivity(int id,String title);
}
