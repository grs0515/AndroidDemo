package com.grs.demo.utils.recorder;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * 一个音频录制,播放工具类
 * @作者:gaoruishan
 * @时间:2016/12/19/17:20
 * @邮箱:grs0515@163.com
 */

public class VoiceRecorder {

	public static int MAX_TIME = 60;//最大时间
	private static final String TAG = "VoiceRecorder";
	public static final String EXTENSION = ".mp3";//后缀名
	public static final String HAIER_VOICE = "haierVoice";//sdcard 保存文件夹
	private int audio_source = MediaRecorder.AudioSource.MIC;//默认音频来源
	private int output_format = MediaRecorder.OutputFormat.THREE_GPP;//默认文件的格式
	private int audio_encoder = MediaRecorder.AudioEncoder.AMR_NB;//默认编码格式
	private File file;
	private AudioManager mAudioMgr;
	private MediaRecorder recorder;
	private String voiceFilePath;
	private boolean isRecording;
	private Context context;
	private static AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener = null;
	private boolean isPlaying;
	private MediaPlayerCallback mMediaPlayerCallback;
	private String playSource;
	private MediaPlayer mediaPlayer;
	private VoiceRecorder currentPlayListener;


	public VoiceRecorder(Context context) {
		this.context = context;
		//监听
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ECLAIR_MR1) {
			mAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
				@Override
				public void onAudioFocusChange(int focusChange) {
					if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
						//失去焦点之后的操作
						Log.e(TAG, "AUDIOFOCUS_LOSS");
						if (isPlaying) {
							stopPlayVoice();
						}
					} else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
						//获得焦点之后的操作
						Log.e(TAG, "AUDIOFOCUS_GAIN");
					}
				}
			};
		}
	}

	/**
	 * 是否录制中
	 * @return
	 */
	public boolean isRecording() {
		return this.isRecording;
	}

	/**
	 * 停止播放音频
	 */
	public void stopPlayVoice() {
		// stop play voice
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
			mMediaPlayerCallback.onStop();
		}
		abandonAudioFocus();
		isPlaying = false;
	}

	/**
	 * 开始播放音频
	 * @param filePath 路径
	 * @param callback 回调
	 */
	public void startPlayVoice(String filePath, MediaPlayerCallback callback) {
		Log.e("playVoice", filePath);
		if (!(new File(filePath).exists())) {
			Log.e(TAG, "not exits");
			return;
		}
		if (isPlaying) {
			stopPlayVoice();
			if (playSource.equals(filePath)) {
				return;
			} else {
				doPlay(filePath, callback);
			}
		} else {
			doPlay(filePath, callback);
		}
	}

	//播放
	private void doPlay(String filePath, MediaPlayerCallback callback) {
		mMediaPlayerCallback = callback;
		AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		audioManager.setSpeakerphoneOn(true);
		audioManager.setMode(AudioManager.MODE_NORMAL);
		((Activity) context).setVolumeControlStream(AudioManager.STREAM_VOICE_CALL);
		requestAudioFocus();
		mediaPlayer = new MediaPlayer();
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_VOICE_CALL);
		try {
			mediaPlayer.setDataSource(filePath);
			mediaPlayer.prepare();
			mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

				@Override
				public void onCompletion(MediaPlayer mp) {
					mMediaPlayerCallback.onStop();
					stopPlayVoice(); // stop animation
				}
			});
			isPlaying = true;
			playSource = filePath;
			currentPlayListener = this;
			mediaPlayer.start();
			mMediaPlayerCallback.onStart();
		} catch (Exception e) {
			Log.e(TAG, e.toString());
		}
	}

	//获取AudioFocus, Android 2.2开始,Android平台为应用程序提供了一个方式来协商设备的音频输出，这个机制被称为音频焦点。
	private void requestAudioFocus() {
		if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.ECLAIR_MR1) {
			return;
		}

		if (mAudioMgr == null)
			mAudioMgr = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		if (mAudioMgr != null) {
			Log.e(TAG, "Request audio focus");
			int ret = mAudioMgr.requestAudioFocus(mAudioFocusChangeListener,
					AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
			if (ret != AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
				Log.e(TAG, "request audio focus fail. " + ret);
			}
		}

	}

	//归还AudioFocus
	private void abandonAudioFocus() {
		if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.ECLAIR_MR1) {
			return;
		}
		if (mAudioMgr != null) {
			Log.e(TAG, "Abandon audio focus");
			mAudioMgr.abandonAudioFocus(mAudioFocusChangeListener);
			mAudioMgr = null;
		}
	}

	/**
	 * 设置音频来源
	 * @param audio_source
	 * @return
	 */
	public VoiceRecorder setAudioSource(int audio_source) {
		this.audio_source = audio_source;
		return this;
	}

	/**
	 * 设置最大限制
	 * @param maxDuration
	 * @return
	 */
	public VoiceRecorder setMaxDuration(int maxDuration) {
		this.MAX_TIME = maxDuration;
		return this;
	}

	/**
	 * 设置音视频文件的格式
	 * @param output_format
	 * @return
	 */
	public VoiceRecorder setOutputFormat(int output_format) {
		this.output_format = output_format;
		return this;
	}

	/**
	 * 设置编码格式
	 * @param audio_encoder
	 * @return
	 */
	public VoiceRecorder setAudioEncoder(int audio_encoder) {
		this.audio_encoder = audio_encoder;
		return this;
	}

	/**
	 * 开始录音
	 */
	public void startRecording(String voiceFilePath) {
		this.file = null;
		try {
			//复位
			discardRecording();
			this.recorder = new MediaRecorder();
			//设置用于录制的音频来源
			this.recorder.setAudioSource(audio_source);
			//设置所录制的音视频文件的格式
			this.recorder.setOutputFormat(output_format);
			//设置所录制的声音的编码格式
			this.recorder.setAudioEncoder(audio_encoder);
			this.recorder.setMaxDuration(MAX_TIME * 1000);// 最大期限 ,录制时间 毫秒
			this.recorder.setAudioChannels(1);
			this.recorder.setAudioSamplingRate(8000);
			this.recorder.setAudioEncodingBitRate(64);
			//设置录制的音频文件的保存位置
			if (voiceFilePath == null) {
				this.voiceFilePath = getVoiceFilePath();
			}
			this.recorder.setOutputFile(this.voiceFilePath);
			//准备录制
			this.recorder.prepare();
			this.isRecording = true;
			this.recorder.start();
		} catch (Exception e) {
			Log.e(TAG, "voice prepare() failed " + e.toString());
		}
	}

	/**
	 * 获得音频文件路径
	 * @return
	 */
	public String getFilePath() {
		return voiceFilePath;
	}

	private String getVoiceFilePath() {
		String folderName = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + HAIER_VOICE;
		File fileMp3 = new File(folderName);
		if (!fileMp3.exists()) {
			fileMp3.mkdirs();
		}
		String fileName = folderName + File.separator + System.currentTimeMillis() + EXTENSION;
		Log.d(TAG, "folderName=" + folderName);
		return fileName;
	}

	/**
	 * 复位录音
	 */
	public void discardRecording() {
		if (this.recorder != null) {
			try {
				this.recorder.stop();
				this.recorder.release();
				this.recorder = null;
				if ((this.file != null) && (this.file.exists()) && (!this.file.isDirectory())) {
					this.file.delete();
				}
			} catch (IllegalStateException e) {
				Log.e(TAG, "vdiscardRecording() failed" + e.toString());
			}
			this.isRecording = false;
		}
	}

	/**
	 * 回调接口
	 */
	public interface MediaPlayerCallback {
		void onStart();

		void onStop();
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		if (this.recorder != null) {
			this.recorder.release();
		}
	}
}
