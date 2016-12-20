package com.grs.demo.template.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * @作者:gaoruishan
 * @时间:2016/12/5
 * @邮箱:grs0515@163.com
 */
public abstract class BaseFragment extends Fragment {
	protected View rootView;
	protected Activity mActivity;


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		int layoutId = getLayoutId();
		View inflate = inflater.inflate(layoutId, null);
		rootView = inflate;
		return rootView;
	}


	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mActivity = (Activity) activity;
	}

	protected abstract int getLayoutId();

	public <T extends View> T find(int viewId) {
		View view = rootView.findViewById(viewId);
		return (T) view;
	}


	public void showToast(String msg) {
		if (mActivity != null) {
			Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
		}

	}


}
