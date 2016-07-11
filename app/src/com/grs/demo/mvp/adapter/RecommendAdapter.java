package com.grs.demo.mvp.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.grs.demo.R;
import com.grs.demo.base.ImageManager;
import com.grs.demo.mvp.bean.ShopRecommend;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class RecommendAdapter extends BaseQuickAdapter<ShopRecommend.ResultsEntity> {

    public RecommendAdapter(List<ShopRecommend.ResultsEntity> results) {
        super(R.layout.view_quick, results);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopRecommend.ResultsEntity item) {
        helper.setText(R.id.tweetName, item.getName())
                .setText(R.id.tweetText, item.getImage_url())
                .setText(R.id.tweetDate, item.getCreated())
                .setOnClickListener(R.id.tweetAvatar, new OnItemChildClickListener())
                .setOnClickListener(R.id.tweetName, new OnItemChildClickListener())
                .linkify(R.id.tweetText);
        ImageManager.displayImage(item.getImage_url(), (ImageView) helper.getView(R.id.tweetAvatar));
    }


}
