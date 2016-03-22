package com.cmcc.hyapps.KunlunTravel.home.view;

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

    RecyclerView getRecyclerView();

    Context getContext();

    void startToActivty(int id,String title);
}
