package com.example.ramkeshsingh.helidodge;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.*;
import android.view.animation.Animation;
import android.widget.ImageView;

public class Splash extends Activity {
    MediaPlayer bckgmsc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final ImageView iv=(ImageView)findViewById(R.id.imageView);
        final Animation an= AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);
        final Animation an1= AnimationUtils.loadAnimation(getBaseContext(), R.anim.abc_fade_out);
        iv.startAnimation(an);
        bckgmsc=MediaPlayer.create(Splash.this,R.raw.spls);
        bckgmsc.setLooping(true);
        bckgmsc.start();

        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv.startAnimation(an1);
                finish();
                Intent i=new Intent(getBaseContext(),MainActivity.class);
                startActivity(i);

            }


            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });    }
    @Override
    public void onPause(){
        super.onPause();
        bckgmsc.release();
        finish();
    }

}
