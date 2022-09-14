package com.example.ichor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private  static  int SPLASH_SCREEN=4000;
    private ImageView image;
    private TextView appname;
    Animation topanim,bottomanim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        image =findViewById(R.id.applogo);
        appname=findViewById(R.id.Appname);
        topanim= AnimationUtils.loadAnimation(this,R.anim.top_anim);
        bottomanim=AnimationUtils.loadAnimation(this,R.anim.bottom_anim);
        image.setAnimation(topanim);
        appname.setAnimation(bottomanim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this, LoginScreen.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}