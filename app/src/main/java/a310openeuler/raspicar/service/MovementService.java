package a310openeuler.raspicar.service;

import static java.lang.Thread.sleep;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.VideoView;

import com.shy.rockerview.MyRockerView;

public class MovementService {
    MyRockerView rockerView;
    MyRockerView rotateRockerView;
    ImageButton leftRotateButton;
    ImageButton rightRotateButton;
    final double BASIC = 50;
    double speed = 0;

    public MovementService(MyRockerView rockerView, MyRockerView rotateRockerView) {
        this.rockerView = rockerView;
        this.rotateRockerView = rotateRockerView;
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
            }
            @Override
            public void onFinish() {
                try {
                    sleep(ThrottleService.MIN_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                PiCommutationService.send(0, 0, 0, 0);
            }
        });

        /*
        rockerView.setOnTouchListener((View view, MotionEvent motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                try {
                    sleep(ThrottleService.MIN_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                PiCommutationService.send(0, 0, 0, 0);
            }
            return false;
        });*/

        rotateRockerView.setOnShakeListener(MyRockerView.DirectionMode.DIRECTION_4_ROTATE_45, new MyRockerView.OnShakeListener() {
            @Override
            public void onStart() {}
            @Override
            public void direction(MyRockerView.Direction direction) {
                switch (direction) {
                    case DIRECTION_LEFT:
                    case DIRECTION_UP_LEFT:
                    case DIRECTION_DOWN_LEFT:
                        PiCommutationService.send(-40, 40, -40, 40);
                        break;
                    case DIRECTION_RIGHT:
                    case DIRECTION_UP_RIGHT:
                    case DIRECTION_DOWN_RIGHT:
                        PiCommutationService.send(40, -40, 40, -40);
                        break;
                    case DIRECTION_UP:
                    case DIRECTION_DOWN:
                        break;
                }
            }
            @Override
            public void onFinish() {
                try {
                    sleep(ThrottleService.MIN_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                PiCommutationService.send(0, 0, 0, 0);
            }
        });
        /*
        leftRotateButton.setOnTouchListener((View view, MotionEvent motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                PiCommutationService.send(-40, 40, -40, 40);
            }
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                try {
                    sleep(ThrottleService.MIN_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                PiCommutationService.send(0, 0, 0, 0);
            }
            return false;
        });
        rightRotateButton.setOnTouchListener((View view, MotionEvent motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                PiCommutationService.send(40, -40, 40, -40);
            }
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                try {
                    sleep(ThrottleService.MIN_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                PiCommutationService.send(0, 0, 0, 0);
            }
            return false;
        }); */
    }
}
