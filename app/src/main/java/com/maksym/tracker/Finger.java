package com.maksym.tracker;

import android.graphics.Point;
import android.util.Log;

/**
 * Created by Maksym on 06.06.2016.
 */
public class Finger {
    public int ID;
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

    public void setNow(Point Now1, int x, int y){
        Logging logging = new Logging(this.getClass().getCanonicalName());
        if(!enabled){
            logging.log(String.valueOf(enabled));
            enabled = true;
            Now = Before = startPoint = new Point(x, y);
        }
        logging.log(Before.toString());
        logging.log(Now.toString());
        Before = Now1;
        Now1 = new Point(x, y);
        Now=Now1;
        logging.logCoords(Before.x,Before.y,Now.x,Now.y);
        if(DrawView.checkDistance(Now1, startPoint) > DrawView.density * 25)
            enabledLongTouch = false;
    }
}