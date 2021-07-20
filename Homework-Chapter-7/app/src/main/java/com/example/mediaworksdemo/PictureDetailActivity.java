package com.example.mediaworksdemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;

import java.io.File;

public class PictureDetailActivity extends AppCompatActivity {

    String mockUrl = "https://lf1-cdn-tos.bytescm.com/obj/static/ies/bytedance_official_cn/_next/static/images/0-390b5def140dc370854c98b8e82ad394.png";
    String mockErrorUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Android_logo_2019_%28stacked%29.svg/400px-Android_logo_2019_%28stacked%29.svg.png";
    private Uri imageUri;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detail);

        ImageView imageView = findViewById(R.id.iv_detail);
        Button btnSuccess = findViewById(R.id.btn_load_success);
        Button btnFail = findViewById(R.id.btn_load_fail);
        Button btnRoundedCorners = findViewById(R.id.btn_rounded_corners);
        Button btnLocal = findViewById(R.id.btn_load_local);

        btnSuccess.setOnClickListener( v -> {
            imageView.setScaleType(ImageView.ScaleType.FIT_START);
            Glide.with(this).load(mockUrl)
                    .placeholder(R.drawable.placeholder_blue)
                    .error(R.drawable.error_red)
                    .into(imageView);
        });

        btnFail.setOnClickListener( v -> {
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            Glide.with(this).load(mockErrorUrl)
                    .placeholder(R.drawable.placeholder_blue)
                    .error(R.drawable.error_red)
                    .into(imageView);
        });

        btnRoundedCorners.setOnClickListener( v-> {
            DrawableCrossFadeFactory drawableCrossFadeFactory = new DrawableCrossFadeFactory.Builder(3000).setCrossFadeEnabled(true).build();
            imageView.setScaleType(ImageView.ScaleType.FIT_END);
            Glide.with(this).load(mockUrl)
                    .placeholder(R.drawable.placeholder_blue)
                    .error(R.drawable.error_red)
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(60)))
                    .transition(DrawableTransitionOptions.with(drawableCrossFadeFactory))
                    .into(imageView);
        });

        btnLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false);
                intent.putExtra(Intent.EXTRA_TITLE, "选择本地图片");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            if(resultCode == Activity.RESULT_OK){
                imageUri = data.getData();
                ImageView imageView = findViewById(R.id.iv_detail);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(this).load(imageUri)
                        .placeholder(R.drawable.placeholder_blue)
                        .error(R.drawable.error_red)
                        .into(imageView);
            }
            else{
                Toast.makeText(this, "加载本地图片失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
