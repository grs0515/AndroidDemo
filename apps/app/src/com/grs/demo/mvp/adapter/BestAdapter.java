package com.grs.demo.mvp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.grs.demo.R;
import com.grs.demo.base.ImageManager;
import com.grs.demo.mvp.bean.CultureBestBean;

import java.util.List;

/**
 * @Class: ListAdapter
 * @Description: 数据适配器
 * @author: lling(www.liuling123.com)
 * @Date: 2015/10/29
 */
public class BestAdapter extends RecyclerView.Adapter<BestAdapter.ItemViewHolder> {

    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener;

    private List<CultureBestBean.ResultsEntity> mData;

    public BestAdapter(Context context, List<CultureBestBean.ResultsEntity> data) {
        this.mData = data;
        mInflater = LayoutInflater.from(context);
    }

    public void setDatasChanged(List<CultureBestBean.ResultsEntity> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mData != null ){
            return mData.size();
        }
        return 0;
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final ItemViewHolder itemViewHolder, final int i) {
        CultureBestBean.ResultsEntity item = mData.get(i);
        ImageManager.displayImage(item.getLogo_url(),
                itemViewHolder.mImageView);
        if (mOnItemClickListener != null) {
            /**
             * 这里加了判断，itemViewHolder.itemView.hasOnClickListeners()
             * 目的是减少对象的创建，如果已经为view设置了click监听事件,就不用重复设置了
             * 不然每次调用onBindViewHolder方法，都会创建两个监听事件对象，增加了内存的开销
             */
            if (!itemViewHolder.itemView.hasOnClickListeners()) {
                itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = itemViewHolder.getPosition();
                        mOnItemClickListener.onItemClick(v, pos);
                    }
                });
                itemViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int pos = itemViewHolder.getPosition();
                        mOnItemClickListener.onItemLongClick(v, pos);
                        return true;
                    }
                });
            }
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        /**
         * 使用RecyclerView，ViewHolder是可以复用的。这根使用ListView的VIewHolder复用是一样的
         * ViewHolder创建的个数好像是可见item的个数+3
         */
        ItemViewHolder holder = new ItemViewHolder(mInflater.inflate(
                R.layout.home_best_item, viewGroup, false));
        return holder;
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    /**
     * 处理item的点击事件和长按事件
     */
    public interface OnItemClickListener {
        public void onItemClick(View view, int position);

        public void onItemLongClick(View view, int position);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.banner_img);
        }
    }

}
