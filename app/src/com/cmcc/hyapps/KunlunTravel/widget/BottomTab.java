/**
 * 
 */

package com.cmcc.hyapps.KunlunTravel.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Tab menus at bottom in index.
 * 
 * @author Kuloud
 */
public class BottomTab extends LinearLayout {
    private int mCurrentTabIndex;//默认 0
    private OnTabSelectedLisenter mOnTabSelected;
    private int mBeforeTabIndex;

    public int getBeforeTabIndex() {
        return mBeforeTabIndex;
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public BottomTab(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * @param context
     * @param attrs
     */
    public BottomTab(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * @param context
     */
    public BottomTab(Context context) {
        super(context);
    }

    //显示效果的实现方法
    public void selectTab(int index) {
        mBeforeTabIndex = mCurrentTabIndex;
        if (index == mCurrentTabIndex) {
            return;
        } else {
            getChildAt(mCurrentTabIndex).setSelected(false);
            mCurrentTabIndex = index;
            getChildAt(mCurrentTabIndex).setSelected(true);
        }
    }

    //加载完成xml后，就会执行
    @Override
    protected void onFinishInflate() {
        //当xml布局文件完全填充后执行，添加点击事件
        super.onFinishInflate();
        for (int i = 0; i < getChildCount(); i++) {
            final int index = i;
            //设置没改子view的点击监听
            getChildAt(index).setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    //修改相应位置的显示效果
                    selectTab(index);
                    if (mOnTabSelected != null) {
                        //接口回调
                        mOnTabSelected.onTabSeledted(index);
                    }
                }
            });

        }
        //默认第一个选中
        getChildAt(0).setSelected(true);
    }

    //接口，可选择的对象，回调
    public interface OnTabSelectedLisenter {
        public void onTabSeledted(int index);
    }

    /**
     * 得到当前自定义控件对象选择的索引
     * @return the mCurrentTabIndex
     */
    public int getCurrentTabIndex() {
        return mCurrentTabIndex;
    }

    /**
     * 设置当前自定义控件对象选择的索引
     * @param currentTabIndex the mCurrentTabIndex to set
     */
    public void setCurrentTabIndex(int currentTabIndex) {
        this.mCurrentTabIndex = currentTabIndex;
    }

    /**
     * 得到当前自定义控件中的OnTabSelectedLisenter
     * @return the mOnTabSelected
     */
    public OnTabSelectedLisenter getOnTabSelectedLisenter() {
        return mOnTabSelected;
    }

    /**
     * 设置当前自定义控件中的监听事件
     * @param OnTabSelected the mOnTabSelected to set
     */
    public void setOnTabSelectedLisenter(OnTabSelectedLisenter OnTabSelected) {
        this.mOnTabSelected = OnTabSelected;
        this.mOnTabSelected.onTabSeledted(mCurrentTabIndex);
    }
}
