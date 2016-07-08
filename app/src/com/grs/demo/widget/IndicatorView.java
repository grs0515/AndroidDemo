package com.grs.demo.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.grs.demo.R;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 一个简单的指示器
 * Created by gaoruishan on 16/7/8.
 */
public class IndicatorView extends LinearLayout {

    private IndicatorAdapter mAdapter;
    private float mPositionOffset;
    private OnItemSelectedListener onItemSelectedListener;
    private OnTransitionListener onTransitionListener;
    private int mPosition;
    private int mPositionOffsetPixels;
    private int mSelectedTabIndex = -1;
    private int mPreSelectedTabIndex = -1;
    public static final int SPLITMETHOD_EQUALS = 0;
    public static final int SPLITMETHOD_WEIGHT = 1;
    public static final int SPLITMETHOD_WRAP = 2;
    private ScrollBar scrollBar;
    private int splitMethod = SPLITMETHOD_EQUALS;
    private int state = ViewPager.SCROLL_STATE_IDLE;
    private int[] prePositions = {-1, -1};
    private List<ViewGroup> views = new LinkedList<ViewGroup>();
    private InRun inRun;


    public IndicatorView(Context context) {
        this(context, null);
        init();
    }

    public IndicatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }


    public IndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inRun = new InRun();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        inRun.stop();
    }

    @Override
    protected void measureChildren(int widthMeasureSpec, int heightMeasureSpec) {
        super.measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    //======================内部代码======================
    //内部线程
    private class InRun implements Runnable {
        private int updateTime = 20;

        private Scroller scroller;
        private final Interpolator sInterpolator = new Interpolator() {
            public float getInterpolation(float t) {
                t -= 1.0f;
                return t * t * t * t * t + 1.0f;
            }
        };

        public InRun() {
            super();
            scroller = new Scroller(getContext(), sInterpolator);
        }

        public void startScroll(int startX, int endX, int dration) {
            scroller.startScroll(startX, 0, endX - startX, 0, dration);
            ViewCompat.postInvalidateOnAnimation(IndicatorView.this);
            post(this);
        }

        public boolean isFinished() {
            return scroller.isFinished();
        }

        public boolean computeScrollOffset() {
            return scroller.computeScrollOffset();
        }

        public int getCurrentX() {
            return scroller.getCurrX();
        }

        public void stop() {
            if (scroller.isFinished()) {
                scroller.abortAnimation();
            }
            removeCallbacks(this);
        }

        @Override
        public void run() {
            ViewCompat.postInvalidateOnAnimation(IndicatorView.this);
            if (!scroller.isFinished()) {
                postDelayed(this, updateTime);
            }
        }
    }


    private OnClickListener onClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            int i = (Integer) v.getTag();
            ViewGroup parent = (ViewGroup) v;
            setCurrentItem(i);
            if (onItemSelectedListener != null) {
                onItemSelectedListener.onItemSelected(parent.getChildAt(0), i, mPreSelectedTabIndex);
            }
        }
    };
    /**
     * 观察者实现类
     */
    private DataSetObserver dataSetObserver = new DataSetObserver() {
        @Override
        public void onChange() {
            if (!inRun.isFinished()) {
                inRun.stop();
            }
            int count = getChildCount();
            int newCount = mAdapter.getCount();
            views.clear();
            for (int i = 0; i < count && i < newCount; i++) {
                views.add((ViewGroup) getChildAt(i));
            }
            removeAllViews();
            int size = views.size();
            for (int i = 0; i < newCount; i++) {
                LinearLayout result = new LinearLayout(getContext());
                View view;
                if (i < size) {
                    View temp = views.get(i).getChildAt(0);
                    views.get(i).removeView(temp);
                    view = mAdapter.getView(i, temp, result);
                } else {
                    view = mAdapter.getView(i, null, result);
                }
                if (onTransitionListener != null) {
                    onTransitionListener.onTransition(view, i, i == mSelectedTabIndex ? 1 : 0);
                }
                result.addView(view);
                result.setOnClickListener(onClickListener);
                result.setTag(i);
                addView(result, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
            }
            mPreSelectedTabIndex = -1;
            setCurrentItem(mSelectedTabIndex, false);
            measureTabs();
        }
    };

    private void measureTabs() {
        int count = getChildCount();
        switch (splitMethod) {
            case SPLITMETHOD_EQUALS:
                for (int i = 0; i < count; i++) {
                    View view = getChildAt(i);
                    LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                    layoutParams.width = 0;
                    layoutParams.weight = 1;
                    view.setLayoutParams(layoutParams);
                }
                break;
            case SPLITMETHOD_WRAP:
                for (int i = 0; i < count; i++) {
                    View view = getChildAt(i);
                    LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                    layoutParams.width = LayoutParams.WRAP_CONTENT;
                    layoutParams.weight = 0;
                    view.setLayoutParams(layoutParams);
                }
                break;
            case SPLITMETHOD_WEIGHT:
                for (int i = 0; i < count; i++) {
                    View view = getChildAt(i);
                    LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                    layoutParams.width = LayoutParams.WRAP_CONTENT;
                    layoutParams.weight = 1;
                    view.setLayoutParams(layoutParams);
                }
                break;
        }
    }

    private void updateTabSelectState(int selectItem) {
        View preview = getItemView(mPreSelectedTabIndex);
        if (preview != null) {
            preview.setSelected(false);
        }
        View selectView = getItemView(selectItem);
        if (selectView != null) {
            selectView.setSelected(true);
        }
    }

    private void notifyPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (position < 0 || position > getCount() - 1) {
            return;
        }
        if (scrollBar != null) {
            scrollBar.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }
        if (onTransitionListener != null) {
            for (int i : prePositions) {
                if (i != position && i != position + 1) {
                    View view = getItemView(i);
                    if (view != null) {
                        onTransitionListener.onTransition(view, i, 0);
                    }
                }
            }
            prePositions[0] = position;
            prePositions[1] = position + 1;

            View view = getItemView(mPreSelectedTabIndex);
            if (view != null) {
                onTransitionListener.onTransition(view, mPreSelectedTabIndex, 0);
            }

            view = getItemView(position);
            if (view != null) {
                onTransitionListener.onTransition(view, position, 1 - positionOffset);
            }

            view = getItemView(position + 1);
            if (view != null) {
                onTransitionListener.onTransition(view, position + 1, positionOffset);
            }
        }
    }

    protected void dispatchDraw(Canvas canvas) {
        if (scrollBar != null && scrollBar.getGravity() == ScrollBar.Gravity.CENTENT_BACKGROUND) {
            drawSlideBar(canvas);
        }
        super.dispatchDraw(canvas);
        if (scrollBar != null && scrollBar.getGravity() != ScrollBar.Gravity.CENTENT_BACKGROUND) {
            drawSlideBar(canvas);
        }
    }

    private void drawSlideBar(Canvas canvas) {
        if (mAdapter == null || scrollBar == null) {
            inRun.stop();
            return;
        }
        final int count = mAdapter.getCount();
        if (count == 0) {
            inRun.stop();
            return;
        }
        if (getCurrentItem() >= count) {
            setCurrentItem(count - 1);
            inRun.stop();
            return;
        }
        float offsetX = 0;
        int offsetY = 0;
        switch (this.scrollBar.getGravity()) {
            case CENTENT_BACKGROUND:
            case CENTENT:
                offsetY = (getHeight() - scrollBar.getHeight(getHeight())) / 2;
                break;
            case TOP:
            case TOP_FLOAT:
                offsetY = 0;
                break;
            case BOTTOM:
            case BOTTOM_FLOAT:
            default:
                offsetY = getHeight() - scrollBar.getHeight(getHeight());
                break;
        }
        int tabWidth;
        View currentView = null;
        Log.d("pppp", "state:" + state);
        if (!inRun.isFinished() && inRun.computeScrollOffset()) {
            offsetX = inRun.getCurrentX();
            int position = 0;
            for (int i = 0; i < count; i++) {
                currentView = getChildAt(i);
                if (currentView.getLeft() <= offsetX && offsetX < currentView.getRight()) {
                    position = i;
                    break;
                }
            }
            int width = currentView.getWidth();
            int positionOffsetPixels = (int) (offsetX - currentView.getLeft());
            float positionOffset = (offsetX - currentView.getLeft()) / width;
            notifyPageScrolled(position, positionOffset, positionOffsetPixels);
            tabWidth = measureScrollBar(position, positionOffset, true);

            Log.d("pppp", "1:" + " mPosition:" + position + " offsetX:" + offsetX);
        } else if (state != ViewPager.SCROLL_STATE_IDLE) {
            currentView = getChildAt(mPosition);
            int width = currentView.getWidth();
            offsetX = currentView.getLeft() + width * mPositionOffset;
            notifyPageScrolled(mPosition, mPositionOffset, mPositionOffsetPixels);
            tabWidth = measureScrollBar(mPosition, mPositionOffset, true);

            Log.d("pppp", "2:" + " mPosition:" + mPosition + " offsetX:" + offsetX);
        } else {
            tabWidth = measureScrollBar(mSelectedTabIndex, 0, true);
            currentView = getChildAt(mSelectedTabIndex);
            if (currentView == null) {
                return;
            }
            offsetX = currentView.getLeft();

            Log.d("pppp", "3:" + " mSelectedTabIndex:" + mSelectedTabIndex + " offsetX:" + offsetX);
        }
        if (inRun.isFinished()) {
            inRun.stop();
        }
        int width = scrollBar.getSlideView().getWidth();
        offsetX += (tabWidth - width) / 2;
        int saveCount = canvas.save();
        canvas.translate(offsetX, offsetY);
        canvas.clipRect(0, 0, width, scrollBar.getSlideView().getHeight()); // needed
        scrollBar.getSlideView().draw(canvas);
        canvas.restoreToCount(saveCount);

        inRun.stop();
    }

    private int measureScrollBar(int position, float selectPercent, boolean needChange) {
        if (scrollBar == null)
            return 0;
        View view = scrollBar.getSlideView();
        if (view.isLayoutRequested() || needChange) {
            View selectV = getChildAt(position);
            View unSelectV = getChildAt(position + 1);
            if (selectV != null) {
                int tabWidth = (int) (selectV.getWidth() * (1 - selectPercent) + (unSelectV == null ? 0 : unSelectV.getWidth() * selectPercent));
                int width = scrollBar.getWidth(tabWidth);
                int height = scrollBar.getHeight(getHeight());
                view.measure(width, height);
                view.layout(0, 0, width, height);
                return tabWidth;
            }
        }
        return scrollBar.getSlideView().getWidth();
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        measureScrollBar(mSelectedTabIndex, 1, true);
    }
//============================对外提供方法===============================


    public void setOnItemSelectListener(OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }


    public void setScrollBar(ScrollBar scrollBar) {
        int paddingBottom = getPaddingBottom();
        int paddingTop = getPaddingTop();
        if (this.scrollBar != null) {
            switch (this.scrollBar.getGravity()) {
                case BOTTOM_FLOAT:
                    paddingBottom = paddingBottom - scrollBar.getHeight(getHeight());
                    break;

                case TOP_FLOAT:
                    paddingTop = paddingTop - scrollBar.getHeight(getHeight());
                    break;
                default:
                    break;
            }
        }
        this.scrollBar = scrollBar;
        switch (this.scrollBar.getGravity()) {
            case BOTTOM_FLOAT:
                paddingBottom = paddingBottom + scrollBar.getHeight(getHeight());
                break;

            case TOP_FLOAT:
                paddingTop = paddingTop + scrollBar.getHeight(getHeight());
                break;
            default:
                break;
        }
        setPadding(getPaddingLeft(), paddingTop, getPaddingRight(), paddingBottom);
        // measureScrollBar(true);
    }

    /**
     * 设置适配器
     */
    public void setAdapter(IndicatorAdapter adapter) {
        if (this.mAdapter != null) {
            this.mAdapter.unRegistDataSetObserver(dataSetObserver);
        }
        this.mAdapter = adapter;
        adapter.registDataSetObserver(dataSetObserver);
        adapter.notifyDataSetChanged();
    }

    public void setCurrentItem(int item) {
        setCurrentItem(item, true);
    }

    public int getCount() {
        if (mAdapter == null) {
            return 0;
        }
        return mAdapter.getCount();
    }

    public int getCurrentItem() {
        return mSelectedTabIndex;
    }

    /**
     * 设置当前条目
     *
     * @param item
     * @param anim
     */
    public void setCurrentItem(int item, boolean anim) {
        int count = getCount();
        if (count == 0) {
            return;
        }
        if (item < 0) {
            item = 0;
        } else if (item > count - 1) {
            item = count - 1;
        }
        if (mSelectedTabIndex != item) {
            mPreSelectedTabIndex = mSelectedTabIndex;
            mSelectedTabIndex = item;

            if (!inRun.isFinished()) {
                inRun.stop();
            }
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                updateTabSelectState(item);
                if (anim && getWidth() != 0 && mPreSelectedTabIndex >= 0 && mPreSelectedTabIndex < getChildCount()) {
                    int sx = getChildAt(mPreSelectedTabIndex).getLeft();
                    int ex = getChildAt(item).getLeft();
                    final float pageDelta = (float) Math.abs(ex - sx) / (getChildAt(item).getWidth());
                    int duration = (int) ((pageDelta + 1) * 100);
                    duration = Math.min(duration, 600);
                    inRun.startScroll(sx, ex, duration);
                } else {
                    notifyPageScrolled(item, 0, 0);
                }
            } else {
                if (onTransitionListener == null) {
                    updateTabSelectState(item);
                }
            }
        }
    }

    public View getItemView(int position) {
        if (position < 0 || position > mAdapter.getCount() - 1) {
            return null;
        }
        final ViewGroup group = (ViewGroup) getChildAt(position);
        return group.getChildAt(0);
    }

    public void setOnPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        this.mPosition = position;
        this.mPositionOffset = positionOffset;
        this.mPositionOffsetPixels = positionOffsetPixels;
        if (scrollBar != null) {
            ViewCompat.postInvalidateOnAnimation(this);
        } else {
            notifyPageScrolled(position, positionOffset, positionOffsetPixels);
        }
    }

    public void setOnPageScrollStateChanged(int state) {
        this.state = state;
    }

    /**
     * 设置过渡效果
     *
     * @param onTransitionListener
     */
    public void setOnTransitionListener(OnTransitionListener onTransitionListener) {
        this.onTransitionListener = onTransitionListener;
        updateTabSelectState(mSelectedTabIndex);
        if (mAdapter != null) {
            for (int i = 0; i < mAdapter.getCount(); i++) {
                View tab = getItemView(i);
                if (tab != null) {
                    onTransitionListener.onTransition(tab, i, mSelectedTabIndex == i ? 1 : 0);
                }
            }
        }
    }
//================================接口==============================

    /**
     * 设置滑动块接口
     */
    public interface ScrollBar {
        public static enum Gravity {
            TOP,
            TOP_FLOAT,
            BOTTOM,
            BOTTOM_FLOAT,
            CENTENT,
            CENTENT_BACKGROUND
        }

        public int getHeight(int tabHeight);

        public int getWidth(int tabWidth);

        public View getSlideView();

        public Gravity getGravity();

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);
    }

    /**
     * 数据源观察者
     */
    static interface DataSetObserver {
        public void onChange();
    }

    /**
     * tab项选中监听
     */
    public static interface OnItemSelectedListener {
        /**
         * 注意 preItem 可能为 -1。表示之前没有选中过,每次adapter.notifyDataSetChanged也会将preItem
         * 设置为-1；
         *
         * @param selectItemView 当前选中的view
         * @param select         当前选中项的索引
         * @param preSelect      之前选中项的索引
         */
        public void onItemSelected(View selectItemView, int select, int preSelect);
    }

    /**
     * tab滑动变化的转换监听，tab在切换过程中会调用此监听。<br>
     * 通过它可以自定义实现在滑动过程中，tab项的字体变化，颜色变化等等效果<br>
     * 目前提供的子类
     */
    public static interface OnTransitionListener {
        public void onTransition(View view, int position, float selectPercent);
    }

//================================父类适配器==================================

    /**
     * 适配器
     */
    public static abstract class IndicatorAdapter {

        private Set<DataSetObserver> observers = new LinkedHashSet<DataSetObserver>(2);

        public abstract int getCount();

        public abstract View getView(int position, View convertView, ViewGroup parent);

        public void notifyDataSetChanged() {
            for (DataSetObserver dataSetObserver : observers) {
                dataSetObserver.onChange();
            }
        }

        public void registDataSetObserver(DataSetObserver observer) {
            observers.add(observer);
        }

        public void unRegistDataSetObserver(DataSetObserver observer) {
            observers.remove(observer);
        }

    }

//========================通过颜色来设置滑动块===========================

    /**
     * 通过颜色来设置滑动块
     */
    public static class ColorBar implements ScrollBar {
        protected ScrollBar.Gravity gravity;
        protected View view;
        protected int color;
        protected int height;
        protected int width;

        public ColorBar(Context context, int color, int height) {
            this(context, color, height, Gravity.BOTTOM);
        }

        public ColorBar(Context context, int color, int height, Gravity gravity) {
            view = new View(context);
            this.color = color;
            view.setBackgroundColor(color);
            this.height = height;
            this.gravity = gravity;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
            view.setBackgroundColor(color);
        }

        public void setHeight(int height) {
            this.height = height;
        }

        @Override
        public int getHeight(int tabHeight) {
            if (height == 0) {
                return tabHeight;
            }
            return height;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        @Override
        public int getWidth(int tabWidth) {
            if (width == 0) {
                return tabWidth;
            }
            return width;
        }

        @Override
        public View getSlideView() {
            return view;
        }

        @Override
        public Gravity getGravity() {
            return gravity;
        }

        public void setGravity(Gravity gravity) {
            this.gravity = gravity;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

    }
//=======================用于获取两个颜色的过渡色=============================

    /**
     * 用于获取两个颜色的过渡色
     */
    public static class ColorGradient {
        /**
         * 开始的颜色
         */
        private int color1;
        /**
         * 结束的颜色
         */
        private int color2;
        /**
         * 开始的颜色到结束的颜色的过渡色分为几份
         */
        private int count;
        /**
         * 开始的颜色的a，r，g，b值
         */
        private int[] color1Values;
        /**
         * 结束的颜色的a，r，g，b值
         */
        private int[] color2Values;

        public ColorGradient(int color1, int color2, int count) {
            super();
            this.color1 = color1;
            this.color2 = color2;
            this.count = count;
            color1Values = toColorValue(color1);
            color2Values = toColorValue(color2);
        }

        /**
         * 获取第几个过渡色，总共分为count个过渡色，i表示去其中的第i个过渡色
         *
         * @param i
         * @return
         */
        public int getColor(int i) {
            int[] result = new int[4];
            for (int j = 0; j < color2Values.length; j++) {
                result[j] = (int) (color1Values[j] + (color2Values[j] - color1Values[j]) * 1.0 / count * i);
            }
            return Color.argb(result[0], result[1], result[2], result[3]);
        }

        private int[] toColorValue(int color) {
            int[] values = new int[4];
            values[0] = Color.alpha(color);
            values[1] = Color.red(color);
            values[2] = Color.green(color);
            values[3] = Color.blue(color);
            return values;
        }
    }
//    =======================设置指示器的text格式=============================

    public static class OnIndicatorTextListener implements IndicatorView.OnTransitionListener {
        private float selectSize = -1;
        private float unSelectSize = -1;
        private IndicatorView.ColorGradient gradient;
        private float dFontFize = -1;

        private boolean isPxSize = false;

        public OnIndicatorTextListener() {
            super();
        }

        public OnIndicatorTextListener(float selectSize, float unSelectSize, int selectColor, int unSelectColor) {
            super();
            setColor(selectColor, unSelectColor);
            setSize(selectSize, unSelectSize);
        }

        public final OnIndicatorTextListener setSize(float selectSize, float unSelectSize) {
            isPxSize = false;
            this.selectSize = selectSize;
            this.unSelectSize = unSelectSize;
            this.dFontFize = selectSize - unSelectSize;
            return this;
        }

        public final OnIndicatorTextListener setValueFromRes(Context context, int selectColorId, int unSelectColorId, int selectSizeId,
                                                             int unSelectSizeId) {
            setColorId(context, selectColorId, unSelectColorId);
            setSizeId(context, selectSizeId, unSelectSizeId);
            return this;
        }

        public final OnIndicatorTextListener setColorId(Context context, int selectColorId, int unSelectColorId) {
            Resources res = context.getResources();
            setColor(res.getColor(selectColorId), res.getColor(unSelectColorId));
            return this;
        }

        public final OnIndicatorTextListener setSizeId(Context context, int selectSizeId, int unSelectSizeId) {
            Resources res = context.getResources();
            setSize(res.getDimensionPixelSize(selectSizeId), res.getDimensionPixelSize(unSelectSizeId));
            isPxSize = true;
            return this;
        }

        public final OnIndicatorTextListener setColor(int selectColor, int unSelectColor) {
            gradient = new ColorGradient(unSelectColor, selectColor, 100);
            return this;
        }

        /**
         * 如果tabItemView 不是目标的TextView，那么你可以重写该方法返回实际要变化的TextView
         *
         * @param tabItemView Indicator的每一项的view
         * @param position    view在Indicator的位置索引
         * @return
         */
        public TextView getTextView(View tabItemView, int position) {
            return (TextView) tabItemView;
        }

        @Override
        public void onTransition(View view, int position, float selectPercent) {
            TextView selectTextView = getTextView(view, position);
            if (gradient != null) {
                selectTextView.setTextColor(gradient.getColor((int) (selectPercent * 100)));
            }
            if (unSelectSize > 0 && selectSize > 0) {
                if (isPxSize) {
                    selectTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, unSelectSize + dFontFize * selectPercent);
                } else {
                    selectTextView.setTextSize(unSelectSize + dFontFize * selectPercent);
                }

            }
        }
    }
//    ========================设置适配器========================


    public static class IndicatorViewAdapter extends IndicatorView.IndicatorAdapter {

        private final int count;
        private final List<String> titles;
        private final Context context;

        public IndicatorViewAdapter(Context context, List<String> titles) {
            super();
            this.count = titles.size();
            this.titles = titles;
            this.context = context;
        }

        @Override
        public int getCount() {
            return count;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.tab_top, parent, false);
            }
            TextView textView = (TextView) convertView;
            //用了固定宽度可以避免TextView文字大小变化，tab宽度变化导致tab抖动现象
            textView.setWidth(dipToPix(context, 50));
            textView.setText(titles.get(position));
            return convertView;
        }
    }

    public static int dipToPix(Context context, int dip) {
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics());
        return size;
    }
}
