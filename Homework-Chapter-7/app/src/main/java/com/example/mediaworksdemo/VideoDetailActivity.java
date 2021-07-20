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
    String mockUrl2 = "https://ugcws.video.gtimg.com/uwMROfz2r5xhIaQXGdGnCmdfJ6o3c4T7WXbX" +
            "_dHHxZTkFh2G/h03103wznz3.p702.1.mp4?sdtfrom=v1010&guid=40c0b39c4461497ccd0d24b3f8eb90a8&vkey=153878AD294D312" +
            "5D2538104DC24DCC0BFE3F922C3552CA709C45AAB78B42D0AC17520C260DF1A9AE37FB49EEA90748FBF4F9A5EB3C9BFAD1C8C93362887C9DFEF207EAC0CC5FF" +
            "3F3794FC7C754871CBCA1DA4C09A91CCC0D4F2814C9EB505CF70" +
            "C7EA0E45B8A4D702CAEB769E9E17882B8BE3C82BF413D274604BCC7BE75CEA0B74C2846CC4223E";
    android.widget.RelativeLayout.LayoutParams originalParams;
    VideoView videoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        videoView = findViewById(R.id.videoView);
        originalParams = (android.widget.RelativeLayout.LayoutParams) videoView.getLayoutParams();
        videoView.setVideoURI(Uri.parse(mockUrl2));
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
