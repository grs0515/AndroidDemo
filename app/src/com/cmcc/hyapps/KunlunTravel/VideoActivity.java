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

package com.cmcc.hyapps.KunlunTravel;


import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class VideoActivity extends Activity {

    /**
     * TODO: Set the path variable to a streaming video URL or a local media file
     * path.
     */

    boolean ifUpdate;
    ;


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        Vitamio.isInitialized(this);

        setContentView(R.layout.videoview);

        playfunction();
        Log.e("==", getPackageName() + ", " + Build.VERSION.SDK_INT);
    }


    void playfunction() {
        String path = "";
        VideoView mVideoView;
        EditText mEditText;
        mEditText = (EditText) findViewById(R.id.url);
        mVideoView = (VideoView) findViewById(R.id.surface_view);
//        path = Environment.getExternalStorageDirectory()
//                + "/test.mp4";
//		path ="rtsp://111.44.243.114/live/030101111000034-1/1";
//		path="http://dlqncdn.miaopai.com/stream/MVaux41A4lkuWloBbGUGaQ__.mp4";
        path = "http://112.54.207.48/media/qhkl/model/201603/A72221CF7BDE4F698C6EFF215675DE97.mp4";
        if (path == "") {
            // Tell the user to provide a media file URL/path.
            Toast.makeText(VideoActivity.this, "Please edit VideoActivity Activity, and set path" + " variable to your media file URL/path", Toast.LENGTH_LONG).show();
            return;
        } else {
            mVideoView.setVideoPath(path);
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
    }

}
