/**
 *
 */

package com.grs.demo.base;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 1,抽象的 RecyclerView.Adapter 添加listView 的Header数据和T集合中模型
 * 2,需要设置头类型，和定义内部类ViewHolder
 *
 * @author kuloud
 */
public abstract class BaseHeaderAdapter<Header, T> extends Adapter<ViewHolder> {
    public static final int TYPE_HEADER = 1;
    public static final int TYPE_CREATE_ITEM = 2;
    public static final int TYPE_BIND_ITEM = 3;
    protected boolean mHeaderEnable;
    protected Header mHeader;
    protected List<T> mDataItems;

    public BaseHeaderAdapter(Header mHeader, List<T> mDataItems) {
        this.mHeader = mHeader;
        this.mDataItems = mDataItems;
    }

    //是否开启头
    public boolean getHeaderEnable() {
        return mHeaderEnable;
    }

    public void setHeaderEnable(boolean enable) {
        mHeaderEnable = enable;
    }

    //默认是带有头的
    public BaseHeaderAdapter() {
        this(true);
    }

    public BaseHeaderAdapter(boolean headerEnable) {
        mDataItems = new ArrayList<T>();
        mHeaderEnable = headerEnable;
        if (headerEnable) {
            mDataItems.add(0, null);
        }
    }

    //设置头
    public void setHeader(Header header) {
        if (!mHeaderEnable) {
            Log.e("kuloud", "can't set header in none header mode");
            return;
        }

        mHeader = header;
        notifyItemChanged(0);
    }

    public Header getHeader() {
        return mHeader;
    }

    /**
     * 获得除头view外的Items数据
     *
     * @return the mDataItems
     */
    public List<T> getDataItems() {
        List<T> dataItems = mHeaderEnable ? mDataItems.subList(1, mDataItems.size()) : mDataItems;
        return dataItems;
    }

    /**
     * 设置list 的Items的数据
     *
     * @param dataItems the data items to set
     */
    public void setDataItems(List<T> dataItems) {
        if (dataItems == null) {
            dataItems = new ArrayList<T>();
        }
        mDataItems = null;
        mDataItems = dataItems;
        if (mHeaderEnable) {
            mDataItems.add(0, null);//头 null
            notifyItemRangeChanged(1, dataItems.size());
        } else {
            notifyDataSetChanged();
        }
    }

    /**
     * 设置没有头的list 数据
     *
     * @param dataItems
     */
    public void setDataItemsNoHeader(List<T> dataItems) {
        if (dataItems == null) {
            dataItems = new ArrayList<T>();
        }
        mDataItems = dataItems;
        notifyDataSetChanged();
    }

    /**
     * 续接数据 －上拉加载
     *
     * @param dataItems the data items to append behind
     */
    public void appendDataItems(List<T> dataItems) {
        if (dataItems == null || dataItems.isEmpty()) {
            return;
        }
        int positionStart = mDataItems.size();
        mDataItems.addAll(dataItems);
        notifyItemRangeInserted(positionStart, dataItems.size());
    }

    /**
     * 插入数据到头部 － 下拉刷新
     *
     * @param dataItems
     */
    public void instertDataItemsAhead(List<T> dataItems) {
        if (dataItems == null || dataItems.isEmpty()) {
            return;
        }
        final int AHEAD_OFFSET = mHeaderEnable ? 1 : 0;
        mDataItems.addAll(AHEAD_OFFSET, dataItems);
        notifyItemRangeInserted(AHEAD_OFFSET, dataItems.size());
    }

    public abstract ViewHolder onCreateHeaderViewHolder(ViewGroup parent);

    public abstract void onBinderHeaderViewHolder(ViewHolder holder);

    public abstract ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType);

    public abstract void onBinderItemViewHolder(ViewHolder holder, int position);

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {// 带有Header Type==1
            return onCreateHeaderViewHolder(parent);
        } else {
            return onCreateItemViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position == 0 && mHeaderEnable) {
            if (mHeader != null) {
                onBinderHeaderViewHolder(holder);
            }
            return;
        }
        onBinderItemViewHolder(holder, position);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && mHeaderEnable) {
            return TYPE_HEADER;
        } else {
            return super.getItemViewType(position);
        }
    }

    @Override
    public int getItemCount() {
        if (mHeaderEnable) {
            return mDataItems.size();
        } else {
            //原来为mDataItems.size()-1   是的设置头部为false后，显示少了一个
            return mDataItems.size();
        }

    }

    private OnItemClickAdapterListener onItemClickListener;

    public void setOnItemClickAdapterListener(OnItemClickAdapterListener listener) {
        this.onItemClickListener = listener;
    }

    //设置监听
    public void setUpClickEvent(final View holder, final int position) {
        if (onItemClickListener != null) {
            holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(holder, position);
                }
            });
            holder.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onItemClickListener.onItemLongClick(holder, position);
                    return false;
                }
            });
        }
    }

    public interface OnItemClickAdapterListener {
        void onItemClick(View view, int positon);

        void onItemLongClick(View view, int postion);
    }

}
