package a310openeuler.raspicar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.shy.rockerview.MyRockerView;

import a310openeuler.raspicar.R;
import a310openeuler.raspicar.service.MediaPlayerService;
import a310openeuler.raspicar.service.PiCommutationService;
import a310openeuler.raspicar.service.MovementService;
import a310openeuler.raspicar.widgets.SwitchImageButton;

public class MainActivity extends AppCompatActivity {

    static Intent newIntent(Context packageContext) {
        return new Intent(packageContext, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_video);

        //获取部件
        ImageButton model3dButton = findViewById(R.id.model3dButton);
        ImageButton bulbButton = findViewById(R.id.bulbButton);
        ImageButton roadBlockButton = findViewById(R.id.roadBlockButton);
        TextView model3dComment = findViewById(R.id.model3dComment);
        TextView bulbComment = findViewById(R.id.bulbComment);
        TextView roadBlockComment = findViewById(R.id.roadBlockComment);
        MyRockerView rockerView = findViewById(R.id.carRocker);
        ImageButton leftRotateButton = findViewById(R.id.leftRotateButton);
        ImageButton rightRotateButton = findViewById(R.id.rightRotateButton);

        //todo 实现控制
        PiCommutationService.start();
        MovementService movementService = new MovementService(rockerView, leftRotateButton, rightRotateButton);
        movementService.start();

        //使用下列部件
        SwitchImageButton switchModel3dButton = new SwitchImageButton(
                model3dButton, model3dComment,
                R.drawable.off_3d, R.drawable.on_3d,
                R.string.off_model_3d_comment,R.string.on_model_3d_comment);
        SwitchImageButton switchBulbButton = new SwitchImageButton(
                bulbButton, bulbComment,
                R.drawable.off_bulb, R.drawable.on_bulb,
                R.string.off_bulb_comment, R.string.on_bulb_comment);
        SwitchImageButton switchRoadBlockButton = new SwitchImageButton(
                roadBlockButton, roadBlockComment,
                R.drawable.off_roadblock, R.drawable.on_roadblock,
                R.string.off_road_block_comment, R.string.on_road_block_comment);

        //设置listener
        model3dButton.setOnClickListener((View view) -> {
            switchModel3dButton.changeState();
            //todo
        });

        bulbButton.setOnClickListener((View view) -> {
            switchBulbButton.changeState();
            //todo
        });

        roadBlockButton.setOnClickListener((View view) -> {
            switchRoadBlockButton.changeState();
            if(switchRoadBlockButton.isOn()) {
                PiCommutationService.send("0:1");
            } else {
                PiCommutationService.send("0:0");
            }
            //todo
        });

        //todo 无法获取视频
/*
        MediaPlayerService mediaPlayerService = new MediaPlayerService(this,
                styledPlayerView, Uri.parse("rtmp://192.168.226.162:7777/live"));
  */
    }
}