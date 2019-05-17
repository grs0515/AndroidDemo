package com.grs.demo.indicator;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grs.demo.R;
import com.grs.demo.base.BaseActivity;
import com.grs.demo.widget.IndicatorView;
import com.grs.demo.widget.PagerSlidingTabStrip;

import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_test)
public class IndicatorActivity extends BaseActivity {
    @ViewInject(R.id.pst)
    PagerSlidingTabStrip pst;
    @ViewInject(R.id.vp)
    ViewPager viewPager;
    @ViewInject(R.id.iv)
    IndicatorView iv;
    List<String> list = new ArrayList<>();
    List<View> listViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list.add("待确定");
        list.add("已采纳");
        setPST();
//        setCustomPST();
        setIndicatorView(iv, list);
    }


    private void setCustomPST() {
        LayoutInflater mInflater = LayoutInflater.from(this);
        View view = mInflater.inflate(R.layout.test_view, null);
        listViews.add(view);
        listViews.add(view);
        viewPager.setAdapter(new CustomTabProviderAdapter(this, list, listViews));
        pst.setViewPager(viewPager);
        pst.setOnTabReselectedListener(new PagerSlidingTabStrip.OnTabReselectedListener() {
            @Override
            public void onTabReselected(int position) {
                LogUtil.e("position=" + position);
            }
        });
    }

    private void setPST() {
        LayoutInflater mInflater = LayoutInflater.from(this);
        View view = mInflater.inflate(R.layout.test_view, null);
        listViews.add(view);
        listViews.add(view);
        viewPager.setAdapter(new ViewPagerAdapter(list, listViews));
        pst.setViewPager(viewPager);
        pst.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                LogUtil.e("position=" + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    private void setIndicatorView(IndicatorView indicator, List<String> count) {
        float unSelectSize = 14;
        float selectSize = unSelectSize * 1.1f;
        int selectColor = getResources().getColor(R.color.green_p);
        int unSelectColor = getResources().getColor(R.color.tab_top_text_1);

        indicator.setAdapter(new IndicatorView.IndicatorViewAdapter(this, count));
        indicator.setScrollBar(new IndicatorView.ColorBar(getApplicationContext(),selectColor, 8));
        indicator.setOnTransitionListener(
                new IndicatorView.OnIndicatorTextListener().setColor(selectColor, unSelectColor).setSize(selectSize, unSelectSize));
        indicator.setCurrentItem(0, true);
        iv.setOnItemSelectListener(new IndicatorView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(View selectItemView, int select, int preSelect) {
                LogUtil.e("select=" + select);
            }
        });
    }

    //    ========================设置适配器========================
    public class ViewPagerAdapter extends PagerAdapter {

        private List<String> titles;
        private List<View> listViews;
        protected Context context;

        public ViewPagerAdapter(Context context) {
            this.context = context;
        }

        public ViewPagerAdapter(List<String> titles, List<View> listViews) {
            this.titles = titles;
            this.listViews = listViews;
        }

        public ViewPagerAdapter(List<String> titles) {
            this.titles = titles;
        }

        @Override
        public int getCount() {
            return titles.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            if (listViews != null) {
                ((ViewPager) container).removeView(listViews.get(position));
            }
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if (listViews != null) {
                ((ViewPager) container).addView(listViews.get(position));
                return listViews.get(position);
            } else {
                return titles.get(position);
            }
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

    //    ========================设置自定义的适配器========================
    public class CustomTabProviderAdapter extends ViewPagerAdapter implements PagerSlidingTabStrip.CustomTabProvider {

        public CustomTabProviderAdapter(Context context, List<String> titles, List<View> listViews) {
            super(context);
            super.titles = titles;
            super.listViews = listViews;
        }

        @Override
        public View getCustomTabView(ViewGroup parent, int position) {
            View view = LayoutInflater.from(context).inflate(R.layout.pst_view_tab2, null);
            return view;
        }

        @Override
        public void tabSelected(View tab) {
            LogUtil.e("tabSelected");
        }

        @Override
        public void tabUnselected(View tab) {
            LogUtil.e("tabUnselected");
        }
    }
}
