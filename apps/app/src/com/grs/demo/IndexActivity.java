package com.grs.demo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.grs.demo.andfix.AndFixActivity;
import com.grs.demo.arouter.ARouterActivity;
import com.grs.demo.ball.CarActivity;
import com.grs.demo.base.BaseActivity;
import com.grs.demo.chart.ChartActivity;
import com.grs.demo.download.DownloadListActivity;
import com.grs.demo.drag.ClickActivity;
import com.grs.demo.drag.TestDragActivity;
import com.grs.demo.drag.photo.PhotoActivity;
import com.grs.demo.indicator.IndicatorActivity;
import com.grs.demo.mvp.view.HomeActivity;
import com.grs.demo.mvp.view.ShopActivity;
import com.grs.demo.mvvm.sample.view.SimpleMainActivity;
import com.grs.demo.mvvm.test.MvvmActivity;
import com.grs.demo.notification.NotificationActivity;
import com.grs.demo.qmui.TestQMUIActivity;
import com.grs.demo.rxjava.RxJavaActivity;
import com.grs.demo.sunfly.SunFlyActivity;
;
import com.grs.demo.utils.video.VideoActivity;
import com.grs.demo.verify.MixedVerifyActivity;
import com.grs.demo.vr.BitmapPlayerActivity;
import com.grs.demo.web.WebActivity;

import org.xutils.view.annotation.ContentView;

@ContentView(R.layout.activity_index)
public class IndexActivity extends BaseActivity {

	public static final String TITLE = "title";
	private LayoutInflater mInflater;
	private ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/**
		 * ClassLoader的类型:PathClassLoader和BootClassLoader
		 */
		ClassLoader classLoader = getClassLoader();
		if (classLoader != null) {
			//dalvik.system.PathClassLoader[DexPathList[[zip file "/data/app/com.grs.demo-1/base.apk"],nativeLibraryDirectories=[/data/app/com.grs.demo-1/lib/arm64, /data/app/com.grs.demo-1/base.apk!/lib/arm64-v8a, /system/lib64, /vendor/lib64]]]
			Log.e(TAG, "(IndexActivity.java:51) " + classLoader.toString());
			while (classLoader.getParent() != null) {
				classLoader = classLoader.getParent();
				//java.lang.BootClassLoader@b9e7216
				Log.e(TAG,"(IndexActivity.java:54) "+classLoader.toString());
			}
		}
		mInflater = LayoutInflater.from(this);

		mListView = (ListView) findViewById(R.id.id_listview);

		mListView.setAdapter(
				new ArrayAdapter<Class>(this, -1, CLAZZES) {
					@Override
					public View getView(int position, View convertView, ViewGroup parent) {
						String title = getItem(position).getSimpleName();
						if (convertView == null) {
							convertView = mInflater.inflate(R.layout.item_list, parent, false);
						}
						TextView tv = (TextView) convertView.findViewById(R.id.id_title);
						tv.setText(title);
						return convertView;
					}
				}

		);

		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(IndexActivity.this, CLAZZES[position]);
				intent.putExtra(IndexActivity.TITLE, CLAZZES[position].getSimpleName());
				startActivity(intent);
			}
		});
	}

	//添加Activity 到列表中
	private Class[] CLAZZES = new Class[]{
			UtilTestActivity.class,
			HomeActivity.class,//mvp
			DownloadListActivity.class,
			RxJavaActivity.class,
			IndicatorActivity.class,
			ShopActivity.class,
			ChartActivity.class,
			SunFlyActivity.class,
			MixedVerifyActivity.class,
			AndFixActivity.class,
			VideoActivity.class,
			TestDragActivity.class,
			PhotoActivity.class,
			ClickActivity.class,
			// 3D汽车模型
			CarActivity.class,
			// vr全景
			BitmapPlayerActivity.class,
			MvvmActivity.class,
			SimpleMainActivity.class,
			ARouterActivity.class,
			WebActivity.class,
			TestQMUIActivity.class,
			NotificationActivity.class
	};
}
