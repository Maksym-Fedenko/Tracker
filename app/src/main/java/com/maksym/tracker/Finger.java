package com.maksym.tracker;

import android.graphics.Point;
import android.util.Log;

/**
 * Created by Maksym on 06.06.2016.
 */
public class Finger {
    public Point Now;
    public Point Before;
    public long wasDown;
    boolean enabled = false;
    public boolean enabledLongTouch = true;
    Point startPoint;

    public Finger(int x, int y){
        wasDown = System.currentTimeMillis();
        //ID = id;
        Now = Before = startPoint = new Point(x, y);

    }

    public void setNow(int x, int y){
        //Logging logging = new Logging(this.getClass().getCanonicalName()); //for logging
        if(!enabled){
            //logging.log(String.valueOf(enabled)); //for logging
            enabled = true;
            //Now = Before = startPoint = new Point(x, y);
        }
        //logging.log(Before.toString()); //for logging
        //logging.log(Now.toString()); //for logging
        Before = Now;
        Now = new Point(x, y);
        //logging.logCoords(Before.x,Before.y,Now.x,Now.y); //for logging
        if(DrawView.checkDistance(Now, startPoint) > DrawView.density * 25)
            enabledLongTouch = false;
        //logging.log(String.valueOf(enabledLongTouch)); //for logging
    }
}