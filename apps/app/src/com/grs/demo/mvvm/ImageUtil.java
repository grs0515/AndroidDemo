package com.grs.demo.mvvm;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import org.xutils.x;

/**
 * 显示图片:
 * BindingAdapter注解
 * （这方法必须是public static的，否则会报错）
 * @author:gaoruishan
 * @date:2018/12/6/14:24
 * @email:grs0515@163.com
 */
public class ImageUtil {
	/**
	 * 这里只用了 bind 声明了一个 image 自定义属性，等下在布局中会用到。
	 * @param view
	 * @param url
	 */
	@BindingAdapter({"image"})
	public static void setImageUrl(ImageView view, String url) {
		x.image().bind(view, url);
	}

}
