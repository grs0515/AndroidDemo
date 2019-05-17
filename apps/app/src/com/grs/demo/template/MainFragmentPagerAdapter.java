package com.grs.demo.template;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.grs.demo.template.fragment.TabListFragment;

/**
 * @作者:gaoruishan
 * @时间:2016/12/5
 * @邮箱:grs0515@163.com
 */
public class MainFragmentPagerAdapter extends FragmentPagerAdapter {


	private int mTabCount;

	public MainFragmentPagerAdapter(FragmentManager fragmentManager, int mTabCount) {
		super(fragmentManager);
		this.mTabCount = mTabCount;
	}

	@Override
	public Fragment getItem(int position) {
		return new TabListFragment();
	}

	@Override
	public int getCount() {
		return mTabCount;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return "Tab:" + position;
	}
}
