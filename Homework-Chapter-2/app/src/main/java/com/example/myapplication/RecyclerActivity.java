package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.recycler.LinearItemDecoration;
import com.example.myapplication.recycler.MyAdapter;
import com.example.myapplication.recycler.TestData;
import com.example.myapplication.recycler.TestDataSet;

public class RecyclerActivity extends AppCompatActivity implements MyAdapter.IOnItemClickListener {

    private static final String TAG = "TAG";

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        Log.i(TAG, "RecyclerViewActivity onCreate");
        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(TestDataSet.getData());
        mAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(3000);
        recyclerView.setItemAnimator(animator);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "RecyclerViewActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "RecyclerViewActivity onResume");
    }

    private int changePosition = 0;
    @Override
    public void onItemCLick(int position, TestData data) {
        int cnt = mAdapter.getItemCount();
        Toast.makeText(RecyclerActivity.this, "hit " + position + "list", Toast.LENGTH_SHORT).show();
        if(position != cnt - 1) {
            Intent intent = new Intent(RecyclerActivity.this, ChangeActivity.class);
            changePosition = position;
            startActivityForResult(intent, 1);
        }
        else mAdapter.addData();
    }

    @Override
    public void onItemLongCLick(int position, TestData data) {
        Toast.makeText(RecyclerActivity.this, "delete " + position + "list", Toast.LENGTH_SHORT).show();
        mAdapter.removeData(position);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == 1){
            String title = data.getStringExtra("title");
            int hot = data.getIntExtra("hot", 0);
            TestData tuple = new TestData(title, String.valueOf(hot));
            mAdapter.changeData(changePosition, tuple);

        }

    }
}