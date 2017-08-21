package com.example.ramkeshsingh.helidodge;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.media.MediaPlayer;

public class MainActivity extends Activity {
    MediaPlayer bckgmsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //trun tillte off
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //set to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new GamePanel(this));
        bckgmsc=MediaPlayer.create(MainActivity.this,R.raw.fg);
        bckgmsc.setLooping(true);
        bckgmsc.start();

    }
    @Override
    public void onPause(){
        super.onPause();
        bckgmsc.release();
        finish();
    }
}
