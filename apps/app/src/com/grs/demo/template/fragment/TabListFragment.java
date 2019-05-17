package com.grs.demo.template.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.grs.demo.R;
import com.grs.demo.template.base.BaseFragment;

/**
 * @作者:gaoruishan
 * @时间:2016/12/5
 * @邮箱:grs0515@163.com
 */

public class TabListFragment extends BaseFragment {

	private SwipeRefreshLayout mRefreshLayout;
	private RecyclerView mRecyclerView;

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_tab_list;
	}


	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		bindViews();

	}

	private void bindViews() {
		mRefreshLayout = find(R.id.mRefreshLayout);
		mRecyclerView = find(R.id.mRecyclerView);
	}

}
