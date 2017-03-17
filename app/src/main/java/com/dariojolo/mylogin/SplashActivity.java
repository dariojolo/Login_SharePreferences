package com.dariojolo.mylogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.dariojolo.mylogin.utils.utils;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        Intent intentLogin = new Intent(SplashActivity.this,LoginActivity.class);
        Intent intentMain = new Intent(SplashActivity.this,MainActivity.class);

        if (!TextUtils.isEmpty(utils.getUserMailPrefs(prefs)) && !TextUtils.isEmpty(utils.getUserPassPrefs(prefs))){
            //intentMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intentMain);
        }else{
            startActivity(intentLogin);
        }
        finish();
        }
}
