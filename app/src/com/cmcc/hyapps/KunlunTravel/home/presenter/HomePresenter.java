package com.cmcc.hyapps.KunlunTravel.home.presenter;

import android.content.Context;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.cmcc.hyapps.KunlunTravel.R;
import com.cmcc.hyapps.KunlunTravel.base.Response;
import com.cmcc.hyapps.KunlunTravel.home.adapter.VideoAdapter;
import com.cmcc.hyapps.KunlunTravel.home.bean.HomeBean;
import com.cmcc.hyapps.KunlunTravel.home.holder.BannerHolder;
import com.cmcc.hyapps.KunlunTravel.home.model.HomeModel;
import com.cmcc.hyapps.KunlunTravel.home.view.IHomeView;

import java.util.List;

/**
 * 作为View与Model交互的中间纽带，处理与用户交互的负责逻辑
 * Created by gaoruishan on 16/3/21.
 */
public class HomePresenter {

    private final Context mContext;
    private HomeModel mHomeModel;
    private IHomeView mIHomeView;
    private List<HomeBean.BannerEntity> mBanner;
    private List<HomeBean.VideoEntity> mVideo;

    public HomePresenter(IHomeView IHomeView) {
        this.mIHomeView = IHomeView;
        this.mHomeModel = new HomeModel();
        mContext = mIHomeView.getContext();
    }

    public void initHomeDatas() {
        mHomeModel.getHomeDates(new Response.onCallBackListener<HomeBean>() {
            @Override
            public void onSuccess(HomeBean response) {
                mBanner = response.getBanner();
                mVideo = response.getVideo();
                mIHomeView.initDatasSuccess();
            }

            @Override
            public void onError(String error) {
                mIHomeView.initDatasFailed();
            }
        });
    }

    public void initBanner() {
        mIHomeView.getConvenientBanner().setPages(new CBViewHolderCreator<BannerHolder>() {
            @Override
            public BannerHolder createHolder() {
                return new BannerHolder(mContext);
            }

        }, mBanner)
                //设置两个点图片作为翻页指示器，不设置则没有指示器
                .setPageIndicator(new int[]{R.drawable.icon_point, R.drawable.icon_point_pre})
                        //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
    }

    public void initVideo(){
        VideoAdapter mVedioAdapter = new VideoAdapter(mContext, mVideo);
        mVedioAdapter.setOnItemClickListener(new VideoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String path = mVideo.get(position).getHref();
                int id = mVideo.get(position).getId();
                if (path != null) {
                    mIHomeView.startToActivty(id, path);
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
            }
        });
        mIHomeView.getRecyclerView().setAdapter(mVedioAdapter);

    }

}
