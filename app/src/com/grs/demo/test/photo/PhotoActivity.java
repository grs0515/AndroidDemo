package com.grs.demo.test.photo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.grs.demo.R;
import com.grs.demo.utils.common.ToastUtils;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.view.ViewPropertyAnimator;

public class PhotoActivity extends Activity {
	public  GridView gridView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_test);
		
		gridView = (GridView) findViewById(R.id.gridview);
		final ImageView imageView = (ImageView) findViewById(R.id.imageView);
		imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ViewPropertyAnimator.animate(imageView)
						.translationX(400)//X轴方向的移动距离
						.translationY(400)
						.alpha(1)
						.scaleX(2)
						.scaleY(2)
						.setDuration(1000)
						.setListener(new AnimatorListenerAdapter() {
							@Override
							public void onAnimationEnd(Animator animation) {
								//Item滑出界面之后执行删除
//							    performDismiss(mDownView, mDownPosition);
								ToastUtils.show(PhotoActivity.this,"执行完了");
							}
						});
			}
		});
		gridView.setAdapter(new ImageAdapter(this));

	}


}
