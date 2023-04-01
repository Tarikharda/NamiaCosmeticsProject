package com.example.namiacosmeticsproject.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.namiacosmeticsproject.User.DashboardActivity;
import com.example.namiacosmeticsproject.R;


public class splashScreen extends AppCompatActivity {

    Animation splashScreenAnim;
    ImageView logoImg;

    private static int DURATION = 4000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // for hide the bar at the top
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash_screen);

        logoImg = findViewById(R.id.company_logo);

        splashScreenAnim = AnimationUtils.loadAnimation(this , R.anim.splash_screen_anim);

        logoImg.setAnimation(splashScreenAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(getApplicationContext() , DashboardActivity.class);
                startActivity(intent);

                finish();
            }
        } , DURATION);

    }
}