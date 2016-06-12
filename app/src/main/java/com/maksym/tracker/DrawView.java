package com.maksym.tracker;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.util.FloatMath;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Maksym on 04.06.2016.
 */
public class DrawView extends View {
    Paint p = new Paint();
    boolean drawingMode, erase;
    float x ;
    float y ;
    Canvas canvas;
    Bitmap image;
    static float density;
    Handler handler = new Handler();
    Finger finger;// = new Finger((int)x, (int)y);
    long lastTapTime;
    Point lastTapPosition;
    Path path;
    Point size;
    //Bitmap imageCopy = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_4444); //commented because it's useless anyway
    RectF but1, but2;
    Path bpath;


    public DrawView(Context context) {
        super(context);
        density = getResources().getDisplayMetrics().density;
        //sb = new StringBuilder();
        p.setStrokeWidth(10);//5*density);
        p.setColor(Color.RED);


        /** This block of code gets dimensions of screen into Point size**/
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        size = new Point();
        display.getSize(size);
        /**End of block**/

        image = Bitmap.createBitmap(size.x, size.y, Bitmap.Config.ARGB_4444);
        canvas = new Canvas(image);
        path = new Path();
        canvas.drawColor(Color.WHITE);
        //this.setBackgroundColor(Color.GRAY);
        erase=false;
        bpath = new Path();
        but1 = new RectF(size.x/3-200,size.y-170,size.x/3+100,size.y-30);
        but2 = new RectF(size.x*2/3-100,size.y-170,size.x*2/3+200,size.y-30);
        bpath.addRect(but1 ,Path.Direction.CW);
        bpath.addRect(but2,Path.Direction.CW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(image, 0, 0, p);
        DrawButtons(size);
        //canvas.drawPath(path, p);
    }
    //=============================-------MAIN-------------
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked(); // Действие
        float x = event.getX();
        float y = event.getY();



        if(action  == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_DOWN){
            drawingMode = true;
            finger=new Finger((int)x, (int)y);
            handler.postDelayed(longPress, 1000);
        }

        else if(action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_POINTER_UP) {
            if(System.currentTimeMillis() - finger.wasDown < 100 && finger.wasDown - lastTapTime < 200 &&
                    finger.wasDown - lastTapTime > 0 && checkDistance(finger.Now, lastTapPosition) < density * 25) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                String[] items = {"Red", "Green", "Blue", "lBlue", "Black", "White", "Yellow", "Pink"};
                final AlertDialog dialog = builder.setTitle("Choose color:").setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        int[] colors = {Color.RED, Color.GREEN, Color.BLUE, 0xFF99CCFF, Color.BLACK, Color.WHITE,
                                Color.YELLOW, 0xFFFFCC99};
                        p.setColor(colors[which]);
                    }
                }).create();
                dialog.show();
            }

            /*if(finger.enabledLongTouch && !(finger.Now==finger.Before)) {
                image= imageCopy.copy(imageCopy.getConfig(),true);
                canvas.drawBitmap(image, 0, 0, p);
            }*/

            lastTapTime = System.currentTimeMillis();
            lastTapPosition = finger.Now;

            if (x>size.x/3-200 && x<size.x/3+100 && y>size.y-170 && y<size.y-30 /*&& action != MotionEvent.ACTION_MOVE*/) {
                canvas.drawColor(Color.WHITE);
                canvas.drawBitmap(image, 0, 0, p);
                erase=false;
            }
            else if (x>size.x*2/3-100 && x<size.x*2/3+200 && y>size.y-170 && y<size.y-30/* && action != MotionEvent.ACTION_MOVE*/) {
                if (!erase){
                    p.setStrokeWidth(100);
                    p.setColor(Color.WHITE);
                    erase=true;
                } else {
                    p.setStrokeWidth(10);
                    p.setColor(Color.RED);
                    erase=false;
                }
            }
        }

        else if(action == MotionEvent.ACTION_MOVE){
            finger.setNow((int)x, (int)y);
            checkGestures();
            invalidate();
            }

        /*p.setTextSize(30);
        sb.setLength(0);
        sb.append(sDown + "   ").append(sMove + "   ").append(sUp + "   ");
        canvas.drawText(sb.toString(), 100, 100, p);*/


        //if(action == MotionEvent.ACTION_MOVE)
        //canvas.drawPoint(x,y,p);
//postInvalidateDelayed(20);
        return true;
    }

    public void checkGestures() {

        if (drawingMode) {
            //p.setStrokeWidth(10);
            /** This is here for debug purposes*/
            //canvas.drawLine(1, 1, 200, 200, p);
            //Logging logging = new Logging(this.getClass().getCanonicalName());
            //logging.logCoords(finger.Before.x,finger.Before.y,
            //        finger.Now.x,finger.Now.y);
            //pStart.x=Math.min(finger.Before.x,finger.Now.x)-(int)p.getStrokeWidth();
            //pStart.y=Math.min(finger.Before.y,finger.Now.y)-(int)p.getStrokeWidth();

            /*path.moveTo(finger.Before.x, finger.Before.y);
            path.lineTo(finger.Now.x, finger.Now.y);
            path.addCircle(finger.Before.x, finger.Before.y, p.getStrokeWidth() /2, Path.Direction.CW);
            path.addCircle(finger.Now.x, finger.Now.y, p.getStrokeWidth() /2, Path.Direction.CW);*/
            //canvas.drawPath(path, p);

            canvas.drawLine(finger.Before.x, finger.Before.y,
                    finger.Now.x, finger.Now.y, p);
            canvas.drawCircle(finger.Before.x, finger.Before.y, p.getStrokeWidth() /2, p);
            canvas.drawCircle(finger.Now.x, finger.Now.y, p.getStrokeWidth() /2, p);
        }
    }

    public void DrawButtons (Point size){
        int old=p.getColor();
        p.setColor(Color.GRAY);
        canvas.drawPath(bpath, p);
        p.setColor(Color.BLACK);
        p.setTextSize(80);
        canvas.drawText("Reset",size.x/3-150,size.y-80,p);
        canvas.drawText("Erase",size.x*2/3-50,size.y-80,p);
        p.setStyle(Paint.Style.FILL);
        p.setColor(old);

    }

    static float checkDistance(Point p1, Point p2){
        return (float)Math.sqrt((p1.x - p2.x)*(p1.x - p2.x)+(p1.y - p2.y)*(p1.y - p2.y));
    }
    Runnable longPress = new Runnable() {
        public void run() {
            if(finger.enabledLongTouch){
                finger.enabledLongTouch = false;
                drawingMode = false;//!drawingMode;
        }}};
}
