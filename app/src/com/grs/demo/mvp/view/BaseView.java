package com.grs.demo.mvp.view;

import android.content.Context;

/**
 * 基本的接口(连接Presenter和Activity)
 * Created by gaoruishan on 16/7/8.
 */
public interface BaseView {
    /**
     * 提供上下文
     *
     * @return
     */
    Context getContext();

    /**
     * 初始化数据成功
     */
    void initDataSuccess(int type);

    /**
     * 初始化数据失败
     */
    void initDataFailed(int type);
}
