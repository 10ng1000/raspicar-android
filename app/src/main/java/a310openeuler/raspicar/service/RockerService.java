package a310openeuler.raspicar.service;

import com.shy.rockerview.MyRockerView;
import a310openeuler.raspicar.service.PiCommutationService;

public class RockerService {
    MyRockerView rockerView;
    double basic = 50;
    double speed = 0;

    public RockerService(MyRockerView rockerView){
        this.rockerView = rockerView;
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
                double leftFront = basic * (vx + vy);
                double rightFront = basic * (vx - vy);
                double leftBack = basic * (vx - vy);
                double rightBack = basic * (vx + vy);

                PiCommutationService.send(leftFront, rightFront, leftBack, rightBack);
                System.out.println(leftFront + ":" + rightFront + ":" + leftBack + ":" + rightBack);
            }
            @Override
            public void onFinish() {}
        });
    }
}
