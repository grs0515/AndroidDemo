/*
 * Copyright (C) 2013 yixia.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.grs.demo.utils.video;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.grs.demo.R;
import com.grs.demo.base.AppConst;
import com.grs.demo.utils.recorder.VoiceRecorder;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class VideoActivity extends Activity {

	/**
	 * TODO: Set the path variable to a streaming video URL or a local media file
	 * path.
	 */

	public static final String TAG = "VideoActivity";
	private String path = "";
	private int id;
	private VideoView mVideoView;
	private EditText etUrl;
	private VoiceRecorder recorder;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		Vitamio.isInitialized(this);
		setContentView(R.layout.activity_video);
		id = getIntent().getIntExtra(AppConst.EXTRA_DATA_INT, 0);
		path = getIntent().getStringExtra(AppConst.EXTRA_DATA_STRING);
		etUrl = (EditText) findViewById(R.id.et_url);
		recorder = new VoiceRecorder(this);
	}

	/**
	 * 播放本地视频
	 */
	public void startPlayLocal(View v) {
		mVideoView = (VideoView) findViewById(R.id.surface_view);
		path = Environment.getExternalStorageDirectory() + "/test.mp4";
//		path ="rtsp://111.44.243.114/live/030101111000034-1/1";
//		path="http://dlqncdn.miaopai.com/stream/MVaux41A4lkuWloBbGUGaQ__.mp4";
//        path = "http://112.54.207.48/media/qhkl/model/201603/A72221CF7BDE4F698C6EFF215675DE97.mp4";
		if (path == "") {
			// Tell the user to provide a media file URL/path.
			Toast.makeText(VideoActivity.this, "Please edit VideoActivity Activity, and set path" + " variable to your media file URL/path", Toast.LENGTH_LONG).show();
			return;
		} else {
			openVideoView();
		}
	}

	//打开播放
	private void openVideoView() {
		mVideoView.setVideoPath(path);
//            MediaController mediaController = new VideoMediaController(this);
		mVideoView.setMediaController(new MediaController(this));
		mVideoView.requestFocus();
		mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer mediaPlayer) {
				// optional need Vitamio 4.0
				mediaPlayer.setPlaybackSpeed(1.0f);
			}
		});
	}

	/**
	 * 开始录音
	 * @param v
	 */
	public void startRecorder(View v) {
		recorder.startRecording(null);
	}

	/**
	 * 复位录音
	 * @param v
	 */
	public void stopRecorder(View v) {
		recorder.discardRecording();
	}

	/**
	 * 播放录音
	 * @param v
	 */
	public void startPlayer(View v) {
		recorder.startPlayVoice(recorder.getFilePath(), new VoiceRecorder.MediaPlayerCallback() {
			@Override
			public void onStart() {
				Log.e(TAG, "onStart");
			}

			@Override
			public void onStop() {
				Log.e(TAG, "onStop");
			}
		});
	}
	/**
	 * 停止播放
	 * @param v
	 */
	public void stopPlayer(View v) {
		recorder.stopPlayVoice();
	}

	/**
	 * 播放URL视频
	 * @param v
	 */
	public void startPlay(View v) {
		if (mVideoView.isPlaying()) {
			mVideoView.stopPlayback();
		}
		mVideoView = (VideoView) findViewById(R.id.surface_view);
		path = etUrl.getText().toString();
		openVideoView();
	}

	class VideoMediaController extends MediaController {

		public VideoMediaController(Context context) {
			super(context);
		}

		@Override
		protected void startDownLoad() {
			Log.e("==", "startDownLoad");
		}
	}

}
