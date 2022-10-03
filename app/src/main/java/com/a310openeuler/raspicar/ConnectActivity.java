package com.a310openeuler.raspicar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConnectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
        //初始化部件
        Button buttonConnect = findViewById(R.id.connectButton);

        //设置listener
        buttonConnect.setOnClickListener( (View view) ->
                {
                    Intent intent = MainActivity.newIntent(this);
                    startActivity(intent);
                }
        );

        //todo 实现文字输入栏功能
    }
}