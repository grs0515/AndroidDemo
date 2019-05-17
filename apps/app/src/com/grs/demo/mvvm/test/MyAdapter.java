package com.grs.demo.mvvm.test;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:2018/12/6/15:37
 * @email:grs0515@163.com
 */
public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

	private List<User> mData;

	public MyAdapter(List<User> mData) {
		this.mData = mData;
	}

	/**
	 * 创建ViewHolder
	 * @param parent
	 * @param viewType
	 * @return
	 */
	@NonNull
	@Override
	public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return MyHolder.create(LayoutInflater.from(parent.getContext()), parent);
	}

	/**
	 * 绑定数据
	 * @param holder
	 * @param position
	 */
	@Override
	public void onBindViewHolder(@NonNull MyHolder holder, int position) {
		holder.bindto(mData,position);
	}

	@Override
	public int getItemCount() {
		if (mData == null) {
			return 0;
		}
		return mData.size();
	}
}
