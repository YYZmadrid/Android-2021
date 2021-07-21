package com.example.mediaworksdemo;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class VideoDetailActivity extends AppCompatActivity {
    MediaPlayer player = new MediaPlayer();
    String mockUrl1 = "https://stream7.iqilu.com/10339/upload_transcode/202002/18/20200218114723HDu3hhxqIT.mp4";
    android.widget.RelativeLayout.LayoutParams originalParams;
    VideoView videoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        videoView = findViewById(R.id.videoView);
        originalParams = (android.widget.RelativeLayout.LayoutParams) videoView.getLayoutParams();
        videoView.setVideoURI(Uri.parse(mockUrl1));
        MediaController MC = new MediaController(this);

        videoView.setMediaController(new MediaController(this));
        videoView.start();
//        SurfaceView surfaceView = findViewById(R.id.surfaceView);
//        surfaceView.setVisibility(View.INVISIBLE);
//        try {
//            player.setDataSource(mockUrl2);
//            SurfaceHolder holder = surfaceView.getHolder();
//            holder.addCallback(new PlayerCallBack());
//            player.prepare();
//            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mp) {
//                    player.start();
//                    player.setLooping(true);
//                }
//            });
//        }catch(IOException e){
//            e.printStackTrace();
//        }
    }

    @Override
    public void onConfigurationChanged(@NonNull @NotNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //Toast.makeText(VideoDetailActivity.this, "横屏", Toast.LENGTH_SHORT).show();
            RelativeLayout.LayoutParams layoutParams =
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            videoView.setLayoutParams(layoutParams);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            //Toast.makeText(VideoDetailActivity.this, "竖屏", Toast.LENGTH_LONG).show();
            android.widget.RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) videoView.getLayoutParams();
            params.width = originalParams.width;
            params.height = originalParams.height;
            videoView.setLayoutParams(originalParams);
        }
    }


    private class PlayerCallBack implements SurfaceHolder.Callback {
        @Override
        public void surfaceCreated(@NonNull SurfaceHolder holder) {
            player.setDisplay(holder);
        }

        @Override
        public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

        }
        @Override
        public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

        }
    }
}
