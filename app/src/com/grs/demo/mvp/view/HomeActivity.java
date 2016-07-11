package com.grs.demo.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.grs.demo.R;
import com.grs.demo.base.BaseActivity;
import com.grs.demo.mvp.presenter.HomePresenter;
import com.grs.demo.support.FullyGridLayoutManager;
import com.grs.demo.support.FullyLinearLayoutManager;
import com.grs.demo.support.SpacesItemDecoration;
import com.grs.demo.utils.common.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * http://111.44.243.118/api/shop_banners/?format=json
 * <p/>
 * http://111.44.243.118/api/shops/?ordering=recommend
 */
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
    @ViewInject(R.id.home_best)
    private RecyclerView home_best;
    private String tag = "";


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
    public void initDataSuccess(int type) {
        mHomePresenter.initBanner();
        mHomePresenter.initVideo();
        loading_progress.setVisibility(View.GONE);
        reload_view.setVisibility(View.GONE);
    }

    @Override
    public void initDataFailed(int type) {
        loading_progress.setVisibility(View.GONE);
        reload_view.setVisibility(View.VISIBLE);
    }

    @Override
    public ConvenientBanner getConvenientBanner() {
        return mConvenientBanner;
    }

    @Override
    public RecyclerView getVideoRecyclerView() {
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
    public RecyclerView getBestRecyclerView() {
        home_best.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
//        FullyLinearLayoutManager manager = new FullyLinearLayoutManager(this);
        FullyLinearLayoutManager manager = new FullyLinearLayoutManager(this);
        // 设置布局管理器
        home_best.setLayoutManager(manager);
        //recyleScenic.addItemDecoration(new SpacesItemDecoration2(20));
        // 设置item动画
        home_best.setItemAnimator(new DefaultItemAnimator());
        return home_best;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void startToVideoActivty(int id, String path) {
//        startActivity(VideoActivity.class, id, path);
    }

    @Override
    public void startToDetailActivity(int id, String title) {
//        startActivity();
        ToastUtils.show(this, title);
    }
}
