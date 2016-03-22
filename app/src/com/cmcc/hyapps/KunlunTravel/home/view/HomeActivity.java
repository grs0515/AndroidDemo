package com.cmcc.hyapps.KunlunTravel.home.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.cmcc.hyapps.KunlunTravel.R;
import com.cmcc.hyapps.KunlunTravel.VideoActivity;
import com.cmcc.hyapps.KunlunTravel.base.BaseActivity;
import com.cmcc.hyapps.KunlunTravel.home.presenter.HomePresenter;
import com.cmcc.hyapps.KunlunTravel.support.FullyGridLayoutManager;
import com.cmcc.hyapps.KunlunTravel.support.SpacesItemDecoration;
import com.cmcc.hyapps.KunlunTravel.utils.common.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_home)
public class HomeActivity extends BaseActivity implements IHomeView {
    @ViewInject(R.id.convenientBanner)
    private ConvenientBanner mConvenientBanner;
    @ViewInject(R.id.reload_view)
    private View reload_view;
    @ViewInject(R.id.loading_progress)
    private View loading_progress;
    @ViewInject(R.id.home_video)
    private RecyclerView home_video;


    @Event(value = {R.id.reload_view, R.id.class_im1, R.id.class_im2})
    private void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.reload_view:
                mHomePresenter.initHomeDatas();
                break;
            case R.id.class_im1:
                ToastUtils.show(HomeActivity.this, "文化遗产");
                break;
            case R.id.class_im2:
                ToastUtils.show(HomeActivity.this, "记忆");
                break;
        }

    }

    private HomePresenter mHomePresenter = new HomePresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomePresenter.initHomeDatas();
    }

    @Override
    public void initDatasSuccess() {
        mHomePresenter.initBanner();
        mHomePresenter.initVideo();
        loading_progress.setVisibility(View.GONE);
    }

    @Override
    public void initDatasFailed() {
        reload_view.setVisibility(View.VISIBLE);
    }

    @Override
    public ConvenientBanner getConvenientBanner() {
        return mConvenientBanner;
    }

    @Override
    public RecyclerView getRecyclerView() {
        home_video.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3);
        // 设置布局管理器
        home_video.setLayoutManager(manager);
        // 设置item动画
        home_video.setItemAnimator(new DefaultItemAnimator());
        home_video.addItemDecoration(new SpacesItemDecoration(20));

        return home_video;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void startToActivty(int id, String title) {
        startActivity(VideoActivity.class,id,title);
    }
}
