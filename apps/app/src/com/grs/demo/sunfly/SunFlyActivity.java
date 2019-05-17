package com.grs.demo.sunfly;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.grs.demo.R;

import org.xutils.common.util.LogUtil;

public class SunFlyActivity extends Activity implements OnClickListener {

	private static final String TAG = SunFlyActivity.class.getSimpleName();
	private Toast mToast;

	@SuppressLint("ShowToast")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_voice);

		mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
		SimpleAdapter listitemAdapter = new SimpleAdapter();
		((ListView) findViewById(R.id.listview_main)).setAdapter(listitemAdapter);

		RecordThread recordThread = new RecordThread(new Handler(){
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				LogUtil.d("handleMessage");
			}
		});
		recordThread.start();
	}

	@Override
	public void onClick(View view) {
		int tag = Integer.parseInt(view.getTag().toString());
		Intent intent = null;
		switch (tag) {
			case 0:
				// 语音转写
				intent = new Intent(SunFlyActivity.this, VoiceToTextActivity.class);
				break;
			case 1:
				// 语法识别
				intent = new Intent(SunFlyActivity.this, AsrActivityDemo.class);
				break;
			case 2:
				// 语义理解
				intent = new Intent(SunFlyActivity.this, UnderstanderDemo.class);
				break;
			case 3:
				// 语音合成
				intent = new Intent(SunFlyActivity.this, TtsActivityDemo.class);
				break;
		}

		if (intent != null) {
			startActivity(intent);
		}
	}

	// Menu 列表
	String items[] = {"语音听写", "语法识别", "语义理解", "语音合成"};

	private class SimpleAdapter extends BaseAdapter {
		public View getView(int position, View convertView, ViewGroup parent) {
			if (null == convertView) {
				LayoutInflater factory = LayoutInflater.from(SunFlyActivity.this);
				View mView = factory.inflate(R.layout.list_items, null);
				convertView = mView;
			}

			Button btn = (Button) convertView.findViewById(R.id.btn);
			btn.setOnClickListener(SunFlyActivity.this);
			btn.setTag(position);
			btn.setText(items[position]);

			return convertView;
		}

		@Override
		public int getCount() {
			return items.length;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}
	}

	private void showTip(final String str) {
		mToast.setText(str);
		mToast.show();
	}

	public class RecordThread extends Thread {
		private AudioRecord ar;
		private int bs = 100;
		private int SAMPLE_RATE_IN_HZ = 8000;
		private Message msg;
		private int number = 1;
		private int tal = 1;
		private Handler handler;
		private long currenttime;
		private long endtime;
		private long time = 1;
		private boolean isRun = false;
		//到达该值之后 触发事件
		private int BLOW_ACTIVI = 3000;
		private boolean isblow;
		private int num;

		public RecordThread(Handler myHandler) {
			super();
			bs = AudioRecord.getMinBufferSize(SAMPLE_RATE_IN_HZ,
					AudioFormat.CHANNEL_CONFIGURATION_MONO,
					AudioFormat.ENCODING_PCM_16BIT);
			ar = new AudioRecord(MediaRecorder.AudioSource.MIC, SAMPLE_RATE_IN_HZ,
					AudioFormat.CHANNEL_CONFIGURATION_MONO,
					AudioFormat.ENCODING_PCM_16BIT, bs);
			handler = myHandler;
		}

		@Override
		public void run() {
			try {
				ar.startRecording();
				isblow = true;
				// 用于读取的 buffer
				byte[] buffer = new byte[bs];
				while (isblow) {
					number++;
					sleep(8);
					currenttime = System.currentTimeMillis();
					int r = ar.read(buffer, 0, bs) + 1;
					int v = 0;
					for (int i = 0; i < buffer.length; i++) {
						v += (buffer[i] * buffer[i]);
					}
					int value = Integer.valueOf(v / (int) r);
					tal = tal + value;
					endtime = System.currentTimeMillis();
					time = time + (endtime - currenttime);

					if (time >= 500 || number > 5) {

						int total = tal / number;
						if (total > BLOW_ACTIVI) {
							//发送消息通知到界面 触发动画

							//利用传入的handler 给界面发送通知
							num += 1;
							handler.sendEmptyMessage(0); //改变i的值后，发送一个空message到主线程
							LogUtil.e("total="+total+"number="+number);
							//
							number = 1;
							tal = 1;
							time = 1;
						}
					}

				}
				ar.stop();
				ar.release();
				bs = 100;


			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void pause() {
			// 在调用本线程的 Activity 的 onPause 里调用，以便 Activity 暂停时释放麦克风
			isRun = false;
		}

		public void start() {
			// 在调用本线程的 Activity 的 onResume 里调用，以便 Activity 恢复后继续获取麦克风输入音量
			if (!isRun) {
				super.start();
			}
		}
	}
}
