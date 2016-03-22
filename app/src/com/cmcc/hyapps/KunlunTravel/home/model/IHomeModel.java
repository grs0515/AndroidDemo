package com.cmcc.hyapps.KunlunTravel.home.model;

import com.cmcc.hyapps.KunlunTravel.base.Response;
import com.cmcc.hyapps.KunlunTravel.home.bean.HomeBean;

/**
 *
 * Created by gaoruishan on 16/3/21.
 */
public interface IHomeModel {
    void getHomeDates(Response.onCallBackListener<HomeBean> callBack);
}
