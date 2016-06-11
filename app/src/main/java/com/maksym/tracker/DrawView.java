package com.maksym.tracker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
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
    boolean drawingMode = false;
    float x ;
    float y ;
    Canvas canvas;
    Bitmap image;
    static float density;
    Handler handler = new Handler();
    Finger finger;// = new Finger((int)x, (int)y);
    long lastTapTime;
    Point lastTapPosition;
    //Bitmap imageCopy = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_4444); //commented because it's useless anyway


    public DrawView(Context context) {
        super(context);
        density = getResources().getDisplayMetrics().density;
        //sb = new StringBuilder();
        p.setStrokeWidth(10);//5*density);
        p.setColor(Color.RED);

        /** This block of code gets dimensions of screen into Point size**/
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        /**End of block**/
        image = Bitmap.createBitmap(size.x, size.y, Bitmap.Config.ARGB_4444);

        canvas = new Canvas(image);
        canvas.drawColor(Color.WHITE);
        //this.setBackgroundColor(Color.GRAY);
        //Bitmap b = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        //Canvas c = new Canvas(b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(image, 0, 0, p);
    }
    //=============================-------MAIN-------------
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked(); // Действие
        float x = event.getX();
        float y = event.getY();


        /*String sDown = null;
        String sMove = null;
        String sUp = null;*/
        if(action  == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_DOWN){
            handler.postDelayed(longPress, 1000);
            finger=new Finger((int)x, (int)y);
        }

        else if(action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_POINTER_UP) {
            if(System.currentTimeMillis() - finger.wasDown < 100 && finger.wasDown - lastTapTime < 200 &&
                    finger.wasDown - lastTapTime > 0 && checkDistance(finger.Now, lastTapPosition) < density * 25) {
                //imageCopy=image.copy(image.getConfig(),true);
                canvas.drawColor(Color.WHITE);
            }

            /*if(finger.enabledLongTouch && !(finger.Now==finger.Before)) {
                image= imageCopy.copy(imageCopy.getConfig(),true);
                canvas.drawBitmap(image, 0, 0, p);
            }*/

            lastTapTime = System.currentTimeMillis();
            lastTapPosition = finger.Now;

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
            p.setStrokeWidth(10);
            /** This is here for debug purposes*/
            //canvas.drawLine(1, 1, 200, 200, p);
            //Logging logging = new Logging(this.getClass().getCanonicalName());
            //logging.logCoords(finger.Before.x,finger.Before.y,
            //        finger.Now.x,finger.Now.y);

            canvas.drawLine(finger.Before.x, finger.Before.y,
                    finger.Now.x, finger.Now.y, p);
            canvas.drawCircle(finger.Before.x, finger.Before.y, p.getStrokeWidth() /2, p);
            canvas.drawCircle(finger.Now.x, finger.Now.y, p.getStrokeWidth() /2, p);
            //canvas.drawPoint(finger.Now.x, finger.Now.y,p);
        }
    }

    static float checkDistance(Point p1, Point p2){
        return (float)Math.sqrt((p1.x - p2.x)*(p1.x - p2.x)+(p1.y - p2.y)*(p1.y - p2.y));
    }
    Runnable longPress = new Runnable() {
        public void run() {
            if(finger.enabledLongTouch){
                finger.enabledLongTouch = false;
                drawingMode = !drawingMode;
        }}};
}
