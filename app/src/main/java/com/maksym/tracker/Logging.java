package com.maksym.tracker;

import android.util.Log;

/**
 * Created by andriy on 6/10/16.
 */
public class Logging {

    private String location;

    public Logging(String where){
        this.location = where;
    }


    public void log(String what){
        Log.d(location, what);
    }
    public void logCoords(int beforeX, int beforeY, int nowX, int nowY) {
        StringBuilder sb = new StringBuilder();
        sb.append("BeforeX:");
        sb.append(beforeX);
        sb.append(" BeforeY:");
        sb.append(beforeY);
        sb.append(" NowX:");
        sb.append(nowX);
        sb.append(" NowY:");
        sb.append(nowY);
        String coords = sb.toString();
        Log.d(location, coords);
    }
}
