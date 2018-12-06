package com.grs.demo.mvvm.test;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableArrayMap;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;

/**
 * @author:gaoruishan
 * @date:2018/12/6/15:00
 * @email:grs0515@163.com
 */
public class User2 {

	public ObservableField<String> firstName = new ObservableField<>();
	public ObservableField<String> lastName = new ObservableField<>();
	public ObservableInt age = new ObservableInt();
	public ObservableBoolean isUser = new ObservableBoolean();

	public ObservableArrayMap<String, String> map = new ObservableArrayMap<>();

	public ObservableList<String> list = new ObservableArrayList<>();

}
