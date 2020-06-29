package com.imuons.globalfunds.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.imuons.globalfunds.R;
import com.imuons.globalfunds.utils.AppCommon;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        handler();
    }

    private void handler() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                    String token = AppCommon.getInstance(SplashScreen.this).getToken();
                    //SharedPreferenceUtils.getAccesstoken(SplashScreen.this);
                    //  token == null || token.isEmpty()
                    if (AppCommon.getInstance(SplashScreen.this).isUserLogIn()) {
                        startActivity(new Intent(SplashScreen.this, HomeActivity.class));
                    } else {
                        startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                    }
                    finish();

                } catch (Exception e) {

                }
            }
        };
        thread.start();
    }
}
