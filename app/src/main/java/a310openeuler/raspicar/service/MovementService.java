package a310openeuler.raspicar.service;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.shy.rockerview.MyRockerView;

public class MovementService {
    MyRockerView rockerView;
    ImageButton leftRotateButton;
    ImageButton rightRotateButton;
    final double BASIC = 50;
    double speed = 0;

    public MovementService(MyRockerView rockerView, ImageButton leftRotateButton, ImageButton rightRotateButton) {
        this.rockerView = rockerView;
        this.leftRotateButton = leftRotateButton;
        this.rightRotateButton = rightRotateButton;
    }

    public void start() {
        rockerView.setOnDistanceLevelListener(new MyRockerView.OnDistanceLevelListener() {
            @Override
            public void onDistanceLevel(int level) {
                System.out.println(level / 9.0);
                speed = level / 9.0;
            }
        });

        rockerView.setOnAngleChangeListener(new MyRockerView.OnAngleChangeListener() {
            @Override
            public void onStart() {}
            @Override
            public void angle(double angle) {
                angle = ((90 + angle) % 360) / 180 * Math.PI;
                double vx = (speed * Math.cos(angle));
                double vy = (speed * Math.sin(angle));
                double leftFront = BASIC * (vx + vy);
                double rightFront = BASIC * (vx - vy);
                double leftBack = BASIC * (vx - vy);
                double rightBack = BASIC * (vx + vy);

                PiCommutationService.send(leftFront, rightFront, leftBack, rightBack);
                System.out.println(leftFront + ":" + rightFront + ":" + leftBack + ":" + rightBack);
            }
            @Override
            public void onFinish() {}
        });
        leftRotateButton.setOnTouchListener((View view, MotionEvent motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                PiCommutationService.send(-40, 40, -40, 40);
                System.out.println("left");
            }
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                PiCommutationService.send(0, 0, 0, 0);
                System.out.println("left stop");
            }
            return false;
        });
        rightRotateButton.setOnTouchListener((View view, MotionEvent motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                PiCommutationService.send(40, -40, 40, -40);
            }
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                PiCommutationService.send(0, 0, 0, 0);
            }
            return false;
        });
    }
}
