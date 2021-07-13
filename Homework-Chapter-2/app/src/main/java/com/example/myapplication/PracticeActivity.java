package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.Toast;

public class PracticeActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        findViewById(R.id.go_to_study).setOnClickListener(this);
        findViewById(R.id.checkBox).setOnClickListener(this);
        ProgressBar bar = findViewById(R.id.progressBar);
        bar.setVisibility(View.INVISIBLE);
        Log.i(TAG, "MainActivity onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "MainActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "MainActivity onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "MainActivity onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "MainActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "MainActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "MainActivity onDestroy");
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.go_to_study:
                CheckBox check1 = findViewById(R.id.checkBox);
                if(check1.isChecked()){
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
                else Toast.makeText(PracticeActivity.this, "Please check before go to study" +
                        "", Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkBox:
                CheckBox check2 = findViewById(R.id.checkBox);
                ProgressBar bar = findViewById(R.id.progressBar);
                if(check2.isChecked()) bar.setVisibility(View.VISIBLE);
                else bar.setVisibility(View.INVISIBLE);
        }
    }

}