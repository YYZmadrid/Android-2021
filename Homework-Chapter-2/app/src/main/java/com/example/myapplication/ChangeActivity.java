package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangeActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        Button btnBack = findViewById(R.id.btn_change);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pattern pattern = Pattern.compile("[0-9]*");
                Matcher match;
                int hot;
                String title;

                EditText hotEdit = findViewById(R.id.editText_Hot);
                String hotStr = hotEdit.getText().toString();
                if(hotStr.isEmpty()){
                    Toast.makeText(ChangeActivity.this, "hot can not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                match = pattern.matcher(hotStr);
                if(match.matches())  hot = Integer.valueOf(hotStr);
                else{
                    Toast.makeText(ChangeActivity.this, "hot must be digits", Toast.LENGTH_SHORT).show();
                    return;
                }

                EditText titleEdit = findViewById(R.id.editText_Title);
                title = titleEdit.getText().toString();
                if(title.isEmpty()){
                    Toast.makeText(ChangeActivity.this, "title can not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent();
                intent.putExtra("hot", hot);
                intent.putExtra("title", title);
                setResult(1, intent);
                finish();
            }
        });
    }
}