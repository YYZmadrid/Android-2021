package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = findViewById(R.id.button1);
        TextView tv1 = findViewById(R.id.textView);
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(v.getId() == R.id.button1){
                    if(tv1.getText().equals("Hello World!")) tv1.setText("Welcome to Android!");
                    else tv1.setText("Hello World!");
                    tv1.setHighlightColor(2);
                }
            }
        });

        Button btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(v.getId() == R.id.button2){
                    CheckBox checkBox1 = findViewById(R.id.checkBoxManager);
                    CheckBox checkBox2 = findViewById(R.id.checkBoxEmployee);
                    CheckBox checkBox3 = findViewById(R.id.checkBoxClient);
                    StringBuilder s = new StringBuilder();
                    int cnt = 0;
                    if(checkBox1.isChecked() == true){
                        s.append("Manager!");
                        cnt++;
                    }
                    if(checkBox2.isChecked() == true){
                        s.append("Employee!");
                        cnt++;
                    }
                    if (checkBox3.isChecked() == true){
                        s.append("Client!");
                        cnt++;
                    }
                    if(cnt == 1)  Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                    else Toast.makeText(MainActivity.this, "You need to choose exactly one role", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Switch aSwitch = findViewById(R.id.switch1);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            int cnt = 0;
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    progressBar.setVisibility(View.VISIBLE);
                    ratingBar.setVisibility(View.VISIBLE);
                }else {
                    progressBar.setVisibility(View.INVISIBLE);
                    ratingBar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}