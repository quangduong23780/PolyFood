package com.example.duan_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan_1.R;

public class ManHinhChaoActivity extends AppCompatActivity {
    ImageView imgwelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);
        imgwelcome=findViewById(R.id.imgwelcome);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.welcome);
        imgwelcome.startAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ManHinhChaoActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}