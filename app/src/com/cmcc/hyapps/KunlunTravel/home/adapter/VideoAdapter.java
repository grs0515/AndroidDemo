package com.cmcc.hyapps.KunlunTravel.home.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmcc.hyapps.KunlunTravel.R;
import com.cmcc.hyapps.KunlunTravel.base.ImageManager;
import com.cmcc.hyapps.KunlunTravel.home.bean.HomeBean;

import java.util.List;

/**
 * @Class: ListAdapter
 * @Description: 数据适配器
 * @author: lling(www.liuling123.com)
 * @Date: 2015/10/29
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ItemViewHolder> {

    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener;

    private List<HomeBean.VideoEntity> mData;

    public VideoAdapter(Context context, List<HomeBean.VideoEntity> data) {
        this.mData = data;
        mInflater = LayoutInflater.from(context);
    }
    public void setDatasChanged(List<HomeBean.VideoEntity> data){
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final ItemViewHolder itemViewHolder, final int i) {
        HomeBean.VideoEntity item = mData.get(i);
        if (item.getTitle()!=null){
            itemViewHolder.mTextView.setText(item.getTitle());
        }
        if (item.getDescinfo()!=null){
            itemViewHolder.mDesc.setVisibility(View.VISIBLE);
            itemViewHolder.mDesc.setText(item.getDescinfo());
        }
        itemViewHolder.mPlayCount.setText(item.getPlaycount()+"");
        ImageManager.displayImage(item.getImg_url(),
                itemViewHolder.mImageView);
        if(mOnItemClickListener != null) {
            /**
             * 这里加了判断，itemViewHolder.itemView.hasOnClickListeners()
             * 目的是减少对象的创建，如果已经为view设置了click监听事件,就不用重复设置了
             * 不然每次调用onBindViewHolder方法，都会创建两个监听事件对象，增加了内存的开销
             */
            if(!itemViewHolder.itemView.hasOnClickListeners()) {
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
                R.layout.home_video_item, viewGroup, false));
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

        private TextView mTextView;
        private TextView mDesc;
        private TextView mPlayCount;
        private ImageView mImageView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.banner_text);
            mDesc = (TextView) itemView.findViewById(R.id.banner_desc);
            mPlayCount = (TextView) itemView.findViewById(R.id.play_count);
            mImageView = (ImageView) itemView.findViewById(R.id.banner_img);
        }
    }

}
