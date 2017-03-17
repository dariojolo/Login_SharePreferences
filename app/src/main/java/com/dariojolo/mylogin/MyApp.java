package com.dariojolo.mylogin;

import android.app.Application;
import android.os.SystemClock;

/**
 * Created by Dario on 17/3/2017.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(3000);
    }
}
