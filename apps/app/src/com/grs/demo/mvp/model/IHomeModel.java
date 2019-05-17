package com.grs.demo.mvp.model;

import com.grs.demo.base.Response;
import com.grs.demo.mvp.bean.CultureBestBean;
import com.grs.demo.mvp.bean.HomeBannerBean;

/**
 *
 * Created by gaoruishan on 16/3/21.
 */
public interface IHomeModel {
    //获取banner和video
    void getHomeBannerDates(Response.onCallBackListener<HomeBannerBean> callBack);
    void getCultureBest(Response.onCallBackListener<CultureBestBean> callBack);
}
