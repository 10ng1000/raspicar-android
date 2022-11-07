package a310openeuler.raspicar.service;

//在这个类里对小车运动的计算进行了封装
public class MovementComputationService {
    public static final int MAX_SPEED = 100;
    public static final int MIN_SPEED = 0;
    public static final int MAX_ANGLE = 100;
    public static final int MIN_ANGLE = -100;
    public static final int MAX_ANGLE_SPEED = 100;
    public static final int MIN_ANGLE_SPEED = -100;

    public static String toMessage(int speed0, int speed1, int speed2, int speed3){
        return String.format("%d:%d:%d:%d", speed0, speed1, speed2, speed3);
    }

    public static String forward(int speed){
        speed = Math.min(speed, MAX_SPEED);
        speed = Math.max(speed, MIN_SPEED);
        return toMessage(speed, speed, speed, speed);
    }

    public static String stop(){
        return toMessage(0, 0, 0, 0);
    }

    public static String backward(int speed){
        speed = Math.min(speed, MAX_SPEED);
        speed = Math.max(speed, MIN_SPEED);
        return toMessage(-speed, -speed, -speed, -speed);
    }

    public static String turnLeft(int speed){
        speed = Math.min(speed, MAX_SPEED);
        speed = Math.max(speed, MIN_SPEED);
        return toMessage(-speed, speed, -speed, speed);
    }

    public static String turnRight(int speed){
        speed = Math.min(speed, MAX_SPEED);
        speed = Math.max(speed, MIN_SPEED);
        return toMessage(speed, -speed, speed, -speed);
    }
}
