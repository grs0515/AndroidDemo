package com.grs.demo.base;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Spanned;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 继承此抽象类，需重写三个方法、设置ItemViewHolder(可添加成员变量)、设置监听事件
 * Created by gaoruishan on 16/1/21.
 */
public abstract class BaseRecycleViewAdapter<Header, T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_HEADER = 1;
    public static final int TYPE_CREATE_ITEM = 2;
    public static final int TYPE_BIND_ITEM = 3;
    protected boolean mHeaderEnable;
    protected Context mContext;
    protected LayoutInflater mInflater;
    protected int headerLayoutId;
    protected int itemLayoutId;
    private SparseArray<View> mViews;
    private SparseArray<View> mItemViews;
    protected Header mHeader;
    protected List<T> mDataItems;

    public BaseRecycleViewAdapter(Context context, int headerLayoutId, int itemLayoutId) {
        this(true);
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.headerLayoutId = headerLayoutId;
        this.itemLayoutId = itemLayoutId;
        this.mViews = new SparseArray<View>();
        this.mItemViews = new SparseArray<View>();
    }

    //是否开启头
    public boolean getHeaderEnable() {
        return mHeaderEnable;
    }

    public void setHeaderEnable(boolean enable) {
        mHeaderEnable = enable;
    }

    //默认是带有头的
    public BaseRecycleViewAdapter() {
        this(true);
    }

    public BaseRecycleViewAdapter(boolean headerEnable) {
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
     */
    public List<T> getDataItems() {
        List<T> dataItems = mHeaderEnable ? mDataItems.subList(1, mDataItems.size()) : mDataItems;
        return dataItems;
    }

    /**
     * 设置list 的Items的数据
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
//            notifyDataSetChanged();
        } else {
            notifyDataSetChanged();
        }
    }

    /**
     * 设置没有头的list 数据
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
     */
    public void instertDataItemsAhead(List<T> dataItems) {
        if (dataItems == null || dataItems.isEmpty()) {
            return;
        }
        final int AHEAD_OFFSET = mHeaderEnable ? 1 : 0;
        mDataItems.addAll(AHEAD_OFFSET, dataItems);
        notifyItemRangeInserted(AHEAD_OFFSET, dataItems.size());
    }

    @Nullable
    protected T getResultsEntity(int position) {
        if (mDataItems.size() <= position) {
            LogUtil.e("[onBinderItemViewHolder] position out of bound");
            return null;
        }
        T entity = mDataItems.get(position);

        if (entity == null) {
            LogUtil.e("[onBinderItemViewHolder] comment: " + entity);
            return null;
        }
        return entity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {// 带有Header Type==1
            return onCreateHeaderViewHolder(parent);
        } else {
            return onCreateItemViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0 && mHeaderEnable) {
            if (mHeader != null) {
                convertHeader((HeaderViewHolder) holder);
            }
            return;
        }
        onBinderItemViewHolder(holder, position);
    }

    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View headerView = mInflater.inflate(headerLayoutId, parent, false);
        return new HeaderViewHolder(headerView);
    }

    protected abstract RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType);

    public abstract void convertHeader(HeaderViewHolder holder);

    protected abstract void onBinderItemViewHolder(RecyclerView.ViewHolder holder, int position);

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


    /**
     * 设置监听
     */
    private OnItemClickAdapterListener onItemClickListener;
    private OnItemLongClickAdapterListener onItemLongClickListener;

    public void setOnItemClickAdapterListener(OnItemClickAdapterListener listener) {
        this.onItemClickListener = listener;
    }

    public void setOnItemLongClickAdapterListener(OnItemLongClickAdapterListener listener) {
        this.onItemLongClickListener = listener;
    }

    public void attachClickEvent(final View view, final int position) {
        if (onItemClickListener != null)
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(view, position);
                }
            });
    }

    public void attachLongClickEvent(final View view, final int position) {
        if (onItemLongClickListener != null)
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onItemLongClickListener.onItemLongClick(view, position);
                    return false;
                }
            });
    }

    public interface OnItemClickAdapterListener {
        void onItemClick(View view, int positon);
    }

    public interface OnItemLongClickAdapterListener {
        void onItemLongClick(View view, int postion);
    }

    /**
     * ViewHolder
     */
    //ItemViewHolder
    public abstract class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public TextView mContent;
        public TextView mNum;
        public ImageView mImage;
        public ImageView mIcon;
        public EditText mEdt;
        public Button mBtn;
        public RatingBar mRtB;

        public ItemViewHolder(View itemView) {
            super(itemView);
            findViewHolderById();
        }

        protected abstract void findViewHolderById();
    }

    //HeaderViewHolder
    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        private View headerView;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            this.headerView = itemView;
        }

        /**
         * 通过viewId获取控件
         */
        public <T extends View> T getView(int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = headerView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }

        /**
         * 设置TextView的值
         */
        public HeaderViewHolder setText(@IdRes int viewId, @NonNull Spanned text) {
            TextView tv = getView(viewId);
            tv.setText(text);
            return this;
        }
        public HeaderViewHolder setRating(int viewId, float rating) {
            RatingBar view = getView(viewId);
            view.setRating(rating);
            return this;
        }
        public HeaderViewHolder setText(@IdRes int viewId, @NonNull String text) {
            TextView tv = getView(viewId);
            tv.setText(text+"");
            return this;
        }

        public HeaderViewHolder setImageResource(@IdRes int viewId, @NonNull int resId) {
            ImageView view = getView(viewId);
            view.setImageResource(resId);
            return this;
        }

        //Image直接添加URL
        public HeaderViewHolder setImageUrl(@IdRes int viewId, @NonNull String url) {
            ImageView view = getView(viewId);
            ImageManager.displayImage(url+"", view);
            return this;
        }

        public HeaderViewHolder setBackgroundColor(@IdRes int viewId, @NonNull int color) {
            View view = getView(viewId);
            view.setBackgroundColor(color);
            return this;
        }


        public HeaderViewHolder setTextColor(@IdRes int viewId, @NonNull int textColor) {
            TextView view = getView(viewId);
            view.setTextColor(textColor);
            return this;
        }

        /**
         * 关于事件的
         */
        public HeaderViewHolder setOnClickListener(@IdRes int viewId,
                                                   View.OnClickListener listener) {
            View view = getView(viewId);
            view.setOnClickListener(listener);
            return this;
        }

        public HeaderViewHolder setOnTouchListener(@IdRes int viewId,
                                                   View.OnTouchListener listener) {
            View view = getView(viewId);
            view.setOnTouchListener(listener);
            return this;
        }

        public HeaderViewHolder setOnLongClickListener( @IdRes int viewId,
                                                       View.OnLongClickListener listener) {
            View view = getView(viewId);
            view.setOnLongClickListener(listener);
            return this;
        }

    }

}
