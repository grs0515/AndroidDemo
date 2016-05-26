package com.cmcc.hyapps.KunlunTravel.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @类 说 明:mainactivity的底部导航的pager的适配器，装载着视fragment的含有的view
 * @version 1.0
 * @创建时间：2014-8-5 下午3:16:11
 * 
 */
public class BasePagerAdapter extends PagerAdapter {
	private List<Fragment> fragments;
	private FragmentManager manager;

	public BasePagerAdapter(List<Fragment> fragments, FragmentManager manager) {
		super();
		this.fragments = fragments;
		this.manager = manager;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(fragments.get(position).getView());
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		Fragment fragment = fragments.get(position);
		if (!fragment.isAdded()) {// 是否已添加过
			FragmentTransaction transaction = manager.beginTransaction();
			transaction.add(fragment, fragment.getClass().getSimpleName());
			transaction.commitAllowingStateLoss();
			manager.executePendingTransactions();
		}
		if (fragment.getView().getParent() == null) {// 父视图不为null，向容器里添加fragment的视图
			container.addView(fragment.getView());
		}
		return fragment.getView();
	}

}
