package com.grs.demo.mvvm.test;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.grs.demo.databinding.UserItemBinding;

import java.util.List;

/**
 * @author:gaoruishan
 * @date:2018/12/6/15:46
 * @email:grs0515@163.com
 */
public class MyHolder extends RecyclerView.ViewHolder {


	private UserItemBinding binding;
	private List<User> list;

	/**
	 * 私有化 注入binding
	 * @param binding
	 */
	private MyHolder(UserItemBinding binding) {
		super(binding.getRoot());
		this.binding = binding;
	}

	/**
	 * 提供一个公共的创建方法
	 * @param inflater
	 * @param parent
	 * @return
	 */
	public static MyHolder create(LayoutInflater inflater, ViewGroup parent) {
		UserItemBinding binding = UserItemBinding.inflate(inflater, parent, false);
		return new MyHolder(binding);
	}

	/**
	 * 最后拿到binding 设置Model
	 * @param list
	 * @param position
	 */
	public void bindto(List<User> list, int position) {
		this.list = list;
		binding.setUser(list.get(position));
		binding.setHolder(this);
	}

	/**
	 * 点击事件
	 * @param view
	 */
	public void onClickBtn(View view) {
		int position = getAdapterPosition();
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setShow(i == position);
		}
		Toast.makeText(view.getContext(), "点击事件 " + position, Toast.LENGTH_LONG).show();
	}
}
