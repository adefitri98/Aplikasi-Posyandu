package com.adefitri.posyandu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.adefitri.posyandu.activity.kader.HomeKader;
import com.adefitri.posyandu.activity.kader.JadwalPosyandu;
import com.adefitri.posyandu.activity.login.LoginPengunjung;
import com.adefitri.posyandu.activity.login.SessionManager;
import com.adefitri.posyandu.activity.pengunjung.HomePengunjung;

public class MainActivity extends AppCompatActivity {
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(getApplicationContext());

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sessionManager.checkLogin();
                finish();
            }
        },5000);
    }
}
