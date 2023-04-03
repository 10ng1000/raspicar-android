package a310openeuler.raspicar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import a310openeuler.raspicar.R;
import a310openeuler.raspicar.service.utils;

// 连接页面
public class ConnectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
        //初始化部件
        Button buttonConnect = findViewById(R.id.connectButton);
        EditText ipEditText = findViewById(R.id.ipInput);

        //设置listener
        buttonConnect.setOnClickListener( (View view) ->
                {
                    if (!ipEditText.getText().toString().equals("")){
                        utils.ip = ipEditText.getText().toString();
                    }
                    Intent intent = MainActivity.newIntent(this);
                    startActivity(intent);
                }
        );
    }
}