package com.maksym.tracker;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;

/**
 * Created by Maksym on 12.06.2016.
 */
public class DrawButtons {
    Path bpath, path1, path2, path3;
    Paint p;
    RectF but1, but2;

    public DrawButtons (int x, int y, Point size){
        p = new Paint();
        bpath = new Path();
        path1 = new Path();
        path2 = new Path();
        path3 = new Path();

        but1 = new RectF(size.x-100,size.y/3-50,size.x-30,size.y/3+50);
        but2 = new RectF(size.x-100,size.y*2/3-50,size.x-30,size.y*2/3+50);
        bpath.addRect(but1 ,Path.Direction.CW);
        bpath.addRect(but2,Path.Direction.CW);

    }

    public boolean showButtons(){

        return true;
    }

    public void resetButton (){

    }
}
