package com.cmcc.hyapps.KunlunTravel.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.cmcc.hyapps.KunlunTravel.support.ViewHolder;

import java.util.List;

/**
 * 定义一个基本的抽象类适配器-----用于适配listview， gridview的数据 =>一种布局item
 *
 * @param <T>
 */
public abstract class BasesAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    private int layoutId;

    // 创建对象时，将数据和布局文件添加进来
    public BasesAdapter(Context context, List<T> datas, int layoutId) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mDatas = datas;
        this.layoutId = layoutId;
    }


    //刷新数据
    public void setDatasChanged(List<T> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }    //刷新数据

    public void setAppendDatasChanged(List<T> datas) {
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(mContext, convertView, parent,
                layoutId, position);
        convert(holder, getItem(position));
        return holder.getConvertView();
    }

    /**
     * 转换方法，设置holder和对象模型
     *
     * @param holder
     * @param t
     */
    public abstract void convert(ViewHolder holder, T t);

}
