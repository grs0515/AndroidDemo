package com.cmcc.hyapps.KunlunTravel.home.model;

import com.cmcc.hyapps.KunlunTravel.base.Response;
import com.cmcc.hyapps.KunlunTravel.home.bean.CultureBestBean;
import com.cmcc.hyapps.KunlunTravel.home.bean.HomeBannerBean;

/**
 *
 * Created by gaoruishan on 16/3/21.
 */
public interface IHomeModel {
    //获取banner和video
    void getHomeBannerDates(Response.onCallBackListener<HomeBannerBean> callBack);
    void getCultureBest(Response.onCallBackListener<CultureBestBean> callBack);
}
