package com.example.testrembol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import static androidx.core.os.HandlerCompat.postDelayed;

public class splashscreen extends AppCompatActivity {
private static int SPLASH = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(splashscreen.this, Login.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH);
    }}




