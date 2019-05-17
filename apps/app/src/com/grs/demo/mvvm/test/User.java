package com.grs.demo.mvvm.test;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.grs.demo.BR;


/**
 * @author:gaoruishan
 * @date:2018/12/6/13:09
 * @email:grs0515@163.com
 */
public class User extends BaseObservable {
	private String firstName;
	private String lastName;
	private boolean show;
	private String url;

	public String getUrl() {
		return url == null ? "" : url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Bindable
	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
		notifyPropertyChanged(BR.show);
	}

	@Bindable
	public String getFirstName() {
		return firstName == null ? "" : firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
		notifyPropertyChanged(BR.firstName);
	}

	@Bindable
	public String getLastName() {
		return lastName == null ? "" : lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
		notifyPropertyChanged(BR.lastName);
	}
}
