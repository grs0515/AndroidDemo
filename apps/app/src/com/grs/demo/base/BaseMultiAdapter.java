package com.grs.demo.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.grs.demo.support.ViewHolder;

import java.util.List;

/**
 * 定义一个基本的抽象类适配器-----用于适配listview， gridview的数据 ＝> 多种布局item类型
 * @param <T>
 */
public abstract class BaseMultiAdapter<T> extends BasesAdapter<T> {

    protected MultiItemTypeSupport<T> mMultiItemTypeSupport;

    /**
     * 创建对象时，填充数据和多类型回调接口(设置布局，布局数，视图类型)
     * @param context
     * @param datas
     * @param multiItemTypeSupport
     */
    public BaseMultiAdapter(Context context, List<T> datas,
                            MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(context, datas, -1);
        mMultiItemTypeSupport = multiItemTypeSupport;
    }

    @Override
    public int getViewTypeCount() {
        if (mMultiItemTypeSupport != null)
            return mMultiItemTypeSupport.getViewTypeCount();
        return super.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (mMultiItemTypeSupport != null)
            return mMultiItemTypeSupport.getItemViewType(position,
                    mDatas.get(position));
        return super.getItemViewType(position);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (mMultiItemTypeSupport == null)
            return super.getView(position, convertView, parent);

        int layoutId = mMultiItemTypeSupport.getLayoutId(position,
                getItem(position));
        ViewHolder viewHolder = ViewHolder.get(mContext, convertView, parent,
                layoutId, position);
        convert(viewHolder, getItem(position));
        return viewHolder.getConvertView();
    }

    public interface MultiItemTypeSupport<T> {
        int getLayoutId(int position, T t);

        int getViewTypeCount();

        int getItemViewType(int postion, T t);
    }
}
