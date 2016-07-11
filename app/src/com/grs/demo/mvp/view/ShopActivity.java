package com.grs.demo.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.grs.demo.R;
import com.grs.demo.base.AppConst;
import com.grs.demo.base.BaseActivity;
import com.grs.demo.mvp.adapter.RecommendAdapter;
import com.grs.demo.mvp.holder.ShopBannerHolder;
import com.grs.demo.mvp.presenter.ShopPresenter;
import com.grs.demo.widget.ActionBar;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * 商户
 * Created by gaoruishan on 16/7/8.
 */
@ContentView(R.layout.activity_shop)
public class ShopActivity extends BaseActivity implements IShopView, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @ViewInject(R.id.action_bar)
    private ActionBar actionBar;
    @ViewInject(R.id.empty_layout)
    private LinearLayout emptyLayout;
    @ViewInject(R.id.recycle_view)
    private RecyclerView mRecyclerView;
    @ViewInject(R.id.swipe_layout)
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private int mCurrentCounter = 0;
    private ShopPresenter mShopPresenter = new ShopPresenter(this);
    private RecommendAdapter mRecommendAdapter;
    private ConvenientBanner convenientBanner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mShopPresenter.initBannerData();
        mShopPresenter.initRecommendData(offset);
    }

    @Override
    public Context getContext() {
        return ShopActivity.this;
    }

    @Override
    public void initActionBar() {
        actionBar.getLeftView().setImageResource(R.drawable.iv_back);
        actionBar.getLeftView().setOnClickListener(this);
        actionBar.getTitleView().setText(R.string.shop_title);
    }

    @Override
    protected void initRecyclerView() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecommendAdapter = new RecommendAdapter(mShopPresenter.shopRecommendResults);
        mRecyclerView.setAdapter(mRecommendAdapter);
        mRecommendAdapter.openLoadAnimation();
        mRecommendAdapter.setOnLoadMoreListener(this);
        mRecommendAdapter.openLoadMore(limit, true);
        mRecommendAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(ShopActivity.this, Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void addHeaderView() {
        View headView = getLayoutInflater().inflate(R.layout.head_view, (ViewGroup) mRecyclerView.getParent(), false);
        convenientBanner = (ConvenientBanner) headView.findViewById(R.id.convenient_banner);
        mRecommendAdapter.addHeaderView(headView);
    }

    protected void addFooterView() {
        mRecommendAdapter.notifyDataChangedAfterLoadMore(false);
        View view = getLayoutInflater().inflate(R.layout.not_loading, (ViewGroup) mRecyclerView.getParent(), false);
        mRecommendAdapter.addFooterView(view);
    }

    @Override
    protected void addCustomLoadingView() {
        View customLoading = getLayoutInflater().inflate(R.layout.custom_loading, (ViewGroup) mRecyclerView.getParent(), false);
        mRecommendAdapter.setLoadingView(customLoading);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action_bar_left:
                finish();
                break;
            case R.id.empty_layout:
                mShopPresenter.initBannerData();
                mShopPresenter.initRecommendData(0);
                break;
        }
    }

    @Override
    public void initDataSuccess(int type) {
        emptyLayout.setVisibility(View.GONE);
        if (type == AppConst.SHOP_BANNER) {
            convenientBanner.setPages(new CBViewHolderCreator<ShopBannerHolder>() {
                @Override
                public ShopBannerHolder createHolder() {
                    return new ShopBannerHolder(ShopActivity.this);
                }
            }, mShopPresenter.shopBannerResults)
                    //设置两个点图片作为翻页指示器，不设置则没有指示器
                    .setPageIndicator(new int[]{R.drawable.icon_point, R.drawable.icon_point_pre})
                    //设置指示器的方向
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);

        } else if (type == AppConst.SHOP_RECOMMEND) {
            mRecommendAdapter.addData(mShopPresenter.shopRecommendResults);
        }
    }

    @Override
    public void initDataFailed(int type) {
        emptyLayout.setVisibility(View.VISIBLE);
        emptyLayout.setOnClickListener(this);
//        mRecyclerView.setHasFixedSize(false);
//        View emptyView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) mRecyclerView.getParent(), false);
//        mRecommendAdapter.setEmptyView(emptyView);
    }

    @Override
    public void onRefresh() {
        offset = 0;
        mRecommendAdapter.setNewData(mShopPresenter.shopRecommendResults);
        mRecommendAdapter.openLoadMore(true);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoadMoreRequested() {
        offset++;
        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                if (mCurrentCounter >= mShopPresenter.mRecommendTotalCount) {
                    addFooterView();
                } else {
                    mRecommendAdapter.notifyDataChangedAfterLoadMore(mShopPresenter.shopRecommendResults, true);
                    mCurrentCounter = mRecommendAdapter.getData().size();
                }
            }
        });
    }
}
