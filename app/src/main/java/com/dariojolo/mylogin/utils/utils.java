package com.dariojolo.mylogin.utils;

import android.content.SharedPreferences;

/**
 * Created by Dario on 17/3/2017.
 */

public class utils {

    public static String getUserMailPrefs(SharedPreferences prefs){
        return prefs.getString("email","");
    }
    public static String getUserPassPrefs(SharedPreferences prefs){
        return prefs.getString("pass","");
    }
}
