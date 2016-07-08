
package com.grs.demo.base;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 *  1,recycleView中 仅list view 适配器
 *  2,继承此抽象类，需定义一个ViewHolder内部类
 * @param <T>
 */
public abstract class AppendableAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    protected List<T> mDataItems = new ArrayList<T>();
    //设置数据
    public void setDataItems(List<T> items) {
        mDataItems.clear();
        if (items != null) {
            mDataItems.addAll(items);
        }
        notifyDataSetChanged();
    }
    //续接数据
    public void appendDataItems(List<T> items) {
        if (items == null || items.isEmpty()) {
            return;
        }
        if (mDataItems.isEmpty()) {
            mDataItems.addAll(items);
            notifyDataSetChanged();
        } else {
            int positionStart = mDataItems.size();
            mDataItems.addAll(items);
            notifyItemRangeInserted(positionStart, items.size());
        }
    }
    //插入数据
    public void instertDataItemsAhead(List<T> dataItems) {
        if (dataItems == null || dataItems.isEmpty()) {
            return;
        }
        final int AHEAD_OFFSET =  0;
        mDataItems.addAll(AHEAD_OFFSET, dataItems);
        notifyItemRangeInserted(AHEAD_OFFSET, dataItems.size());
    }
    @Override
    public int getItemCount() {
        return mDataItems == null ? 0 : mDataItems.size();
    }
    //获得数据
    public List<T> getDataItems() {
        return mDataItems;
    }
}
