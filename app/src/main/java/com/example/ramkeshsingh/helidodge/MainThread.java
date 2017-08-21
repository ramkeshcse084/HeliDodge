package com.example.ramkeshsingh.helidodge;

import android.graphics.Canvas;
import android.provider.Settings;
import android.view.SurfaceHolder;

/**
 * Created by pawan on 30-Jan-17.
 */
public class MainThread extends Thread
{
    private int FPS = 30;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePannel;
    private boolean running;
    private static Canvas canvas;

    public MainThread(SurfaceHolder surfaceHolder,GamePanel gamePannel)
    {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePannel = gamePannel;
    }

    public void run()
    {
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime= 0;
        int frameCount=0;
        long targetTime = 1000/FPS;

        while (running) {
            startTime = System.nanoTime();
            canvas = null;
            //try locking the canvas for pixel editing
            try{
                canvas =  this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gamePannel.update();
                    this.gamePannel.draw(canvas);
                }
            }
            catch (Exception e){

            }
            finally {
                if (canvas!=null){
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch (Exception e){e.printStackTrace();}

                }
            }
            timeMillis = (System.nanoTime()-startTime)/1000000;
            waitTime = targetTime-timeMillis;

            try{
                this.sleep(waitTime);

            }catch (Exception e){}
            totalTime = System.nanoTime()-startTime;
            frameCount++;

            if (frameCount == FPS){
                averageFPS = 100/((totalTime/frameCount)/1000000);
                frameCount=0;
                totalTime=0;
                System.out.print(averageFPS);
            }
        }

    }
    public  void setRunning(boolean b){
        running=b;
    }
}

