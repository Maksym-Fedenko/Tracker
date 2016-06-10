package com.maksym.tracker;


import android.os.Bundle;
import android.app.Activity;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
    }

    //------------------------------------------------------draw class
   /* class DrawView extends View  {
        Paint p;
        StringBuilder sb;


        public DrawView(Context context) {
            super(context);
            p = new Paint();
            sb = new StringBuilder();
            //Bitmap b = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            //Canvas c = new Canvas(b);
        }

        @Override
        protected void onDraw(Canvas c) {
// красный цвет
            p.setColor(Color.RED);
// толщина линии = 10
            p.setStrokeWidth(100);

            //c.set;
            c.drawPoint(x,y,p);
        }
    }*/
    //-------------------------------------------------------

    /*class DrawView extends SurfaceView implements SurfaceHolder.Callback {

        private DrawThread drawThread;

        public DrawView(Context context) {
            super(context);
            getHolder().addCallback(this);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {

        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            drawThread = new DrawThread(getHolder());
            drawThread.setRunning(true);
            drawThread.start();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            boolean retry = true;
            drawThread.setRunning(false);
            while (retry) {
                try {
                    drawThread.join();
                    retry = false;
                } catch (InterruptedException e) {
                }
            }
        }

        class DrawThread extends Thread {

            Paint p;
            private boolean running = false;
            private SurfaceHolder surfaceHolder;

            public DrawThread(SurfaceHolder surfaceHolder) {
                this.surfaceHolder = surfaceHolder;
            }

            public void setRunning(boolean running) {
                this.running = running;
            }

            @Override
            public void run() {
                Canvas canvas;
                while (running) {
                    canvas = null;
                    try {
                        canvas = surfaceHolder.lockCanvas(null);
                        if (canvas == null)
                            continue;
                        canvas.drawPoint(x,y,p);
                    } finally {
                        if (canvas != null) {
                            surfaceHolder.unlockCanvasAndPost(canvas);
                        }
                    }
                }
            }
        }
    }
     //------------------------------------------------------
        @Override
    public boolean onTouch(View v, MotionEvent event) {
        x = event.getX();
        y = event.getY();





        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // нажатие
                sDown = "Down: " + x + "," + y;
                sMove = ""; sUp = "";
                break;
            case MotionEvent.ACTION_MOVE: // движение
                sMove = "Move: " + x + "," + y;

                break;
            case MotionEvent.ACTION_UP: // отпускание
            case MotionEvent.ACTION_CANCEL:
                sMove = "";
                sUp = "Up: " + x + "," + y;

                break;
        }
        //tv.setText(sDown + "\n" + sMove + "\n" + sUp);
        setContentView(new DrawView(this));

        return true;
    }*/
}
