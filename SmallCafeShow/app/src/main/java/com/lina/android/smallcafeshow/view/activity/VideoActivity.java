package com.lina.android.smallcafeshow.view.activity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lina.android.smallcafeshow.R;
import com.lina.android.smallcafeshow.http.download.Videodownload;

import java.io.IOException;

public class VideoActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    //    private TextView textView_video;
    private ImageView image_video_back;
    private ImageView image_voice_record;

    private TextView textView_songName;
    private SurfaceView surfaceView;
    private SurfaceHolder holder;
    private MediaPlayer mediaPlayer;
    boolean isExecute = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        holder = surfaceView.getHolder();
        holder.addCallback(this);
        holder.setFixedSize(320, 220);


        textView_songName = (TextView) findViewById(R.id.textView_songName);
        image_video_back = (ImageView) findViewById(R.id.image_video_back);
        image_video_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        image_voice_record = (ImageView) findViewById(R.id.image_voice_record);

        mediaPlayer = new MediaPlayer();


        surfaceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                } else {
                    mediaPlayer.start();
                }
            }
        });


        Intent intent = getIntent();
        String videoUrlKey = intent.getStringExtra("videoUrlKey");

        Videodownload videodownload = new Videodownload();
        videodownload.fun(videoUrlKey);

        videodownload.fun1(new Videodownload.IDownloadCallBack() {
            @Override
            public void downCallBack(String filePath) {

                if (isExecute == true) {
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setDisplay(holder);
                    try {
                        mediaPlayer.setDataSource(filePath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mediaPlayer.setLooping(true);
                            mediaPlayer.start();
                        }
                    });
                    mediaPlayer.prepareAsync();
                }
            }
        });

    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        isExecute = true;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
        }
    }
}
