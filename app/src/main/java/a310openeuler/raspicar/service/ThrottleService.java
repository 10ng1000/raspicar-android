package a310openeuler.raspicar.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.lang.reflect.Method;

public class ThrottleService{
    public static final long MIN_TIME = 100;
    private static long lastTime = 0;
    public ThrottleService() {}

    // 限制最大速度，如果超过最大速度则返回false
    public static boolean throttle(){
        long currentTime = System.currentTimeMillis();
        System.out.println(currentTime - lastTime);
        if (currentTime - lastTime > MIN_TIME){
            lastTime = currentTime;
            System.out.println("throttle");
            return true;
        }
        return false;
    }
}