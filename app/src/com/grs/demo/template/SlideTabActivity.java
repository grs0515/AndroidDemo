package com.grs.demo.template;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.grs.demo.R;

/**
 * @作者:gaoruishan
 * @时间:2016/12/5
 * @邮箱:grs0515@163.com
 */
public class SlideTabActivity extends AppCompatActivity {


	private TabLayout mTabLayout;
	private ViewPager mViewPager;
	private int mTabCount = 4;
	private MainFragmentPagerAdapter fragmentPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slide_tab);
		mTabLayout = (TabLayout) findViewById(R.id.id_tablayout);
		mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
		fragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager(), mTabCount);
		mViewPager.setAdapter(fragmentPagerAdapter);
		mTabLayout.setupWithViewPager(mViewPager);
	}
}
