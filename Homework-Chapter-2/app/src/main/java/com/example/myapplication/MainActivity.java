package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private void initView() {
        findViewById(R.id.btn_toast).setOnClickListener(this);
        findViewById(R.id.btn_baidu).setOnClickListener(this);
        findViewById(R.id.btn_phone).setOnClickListener(this);
        findViewById(R.id.btn_top_search).setOnClickListener(this);
        findViewById(R.id.btn_practice_activity).setOnClickListener(this);
    }

    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "MainActivity onCreate");
        initView();
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
            case R.id.btn_toast:
                Toast.makeText(MainActivity.this, "button click", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_baidu:
                Intent intent1 = new Intent(Intent.ACTION_VIEW);
                intent1.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent1);
                break;
            case R.id.btn_phone:
                Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "19858874821"));
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent2);
                break;
            case R.id.btn_practice_activity:
                Intent intent3 = new Intent(this, PracticeActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_top_search:
                Intent intent4 = new Intent(this, RecyclerActivity.class);
                startActivity(intent4);
                break;
        }
    }

    public static class ChangeActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_change);
        }
    }
}