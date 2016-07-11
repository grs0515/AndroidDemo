package com.grs.demo.mvp.view;

import android.support.v7.widget.RecyclerView;

import com.bigkoo.convenientbanner.ConvenientBanner;

/**
 * Activity需要View实现的接口，与Presenter进行交互
 * Created by gaoruishan on 16/3/21.
 */
public interface IHomeView extends BaseView{

    ConvenientBanner getConvenientBanner();

    RecyclerView getVideoRecyclerView();
    RecyclerView getBestRecyclerView();


    void startToVideoActivty(int id,String path);

    void startToDetailActivity(int id,String title);
}
