package com.example.baschdi.androidweartextinput;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.baschdi.firstwearapp.R;

public class MainActivity extends Activity implements View.OnTouchListener {

    private TextView mTextView;

    private TextView textViewTop, textViewRight, textViewBottom, textViewLeft, textViewField;
    private RelativeLayout middlePressed, middleReleased;

    private GestureDetector gestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
            }
        });

        setContentView(R.layout.rect_activity_main);

        Button button1 = (Button)findViewById(R.id.button1);
        Button button2 = (Button)findViewById(R.id.button2);
        Button button3 = (Button)findViewById(R.id.button3);
        Button button4 = (Button)findViewById(R.id.button4);
        Button button5 = (Button)findViewById(R.id.button5);
        Button button6 = (Button)findViewById(R.id.button6);
        Button button7 = (Button)findViewById(R.id.button7);
        Button button8 = (Button)findViewById(R.id.button8);

        textViewTop = (TextView) findViewById(R.id.textViewTop);
        textViewRight = (TextView) findViewById(R.id.textViewRight);
        textViewBottom = (TextView) findViewById(R.id.textViewBottom);
        textViewLeft = (TextView) findViewById(R.id.textViewLeft);
        textViewField = (TextView) findViewById(R.id.textViewField);

        middlePressed = (RelativeLayout)findViewById(R.id.middlePressed);
        middleReleased = (RelativeLayout)findViewById(R.id.middleReleased);

        button1.setOnTouchListener(this);
        button2.setOnTouchListener(this);
        button3.setOnTouchListener(this);
        button4.setOnTouchListener(this);
        button5.setOnTouchListener(this);
        button6.setOnTouchListener(this);
        button7.setOnTouchListener(this);
        button8.setOnTouchListener(this);
        middleReleased.setOnTouchListener(this);

        gestureDetector = new GestureDetector(new SwipeGestureDetector());


    }

    public boolean onTouch (View view, MotionEvent event){
//        System.out.println(event.getAction() + "::::" + view);
        if(event.getAction() == MotionEvent.ACTION_DOWN && view.getId() != R.id.middleReleased){
            switch (view.getId()){
                case R.id.button1:
                    textViewTop.setText("A");
                    textViewRight.setText("B");
                    textViewBottom.setText("C");
                    textViewLeft.setText("D");
                    break;
                case R.id.button2:
                    textViewTop.setText("E");
                    textViewRight.setText("F");
                    textViewBottom.setText("G");
                    textViewLeft.setText("H");
                    break;
                case R.id.button3:
                    textViewTop.setText("I");
                    textViewRight.setText("J");
                    textViewBottom.setText("K");
                    textViewLeft.setText("L");
                    break;
                case R.id.button4:
                    textViewTop.setText("M");
                    textViewRight.setText("N");
                    textViewBottom.setText("O");
                    textViewLeft.setText("P");
                    break;
                case R.id.button5:
                    textViewTop.setText("Q");
                    textViewRight.setText("R");
                    textViewBottom.setText("S");
                    textViewLeft.setText("T");
                    break;
                case R.id.button6:
                    textViewTop.setText("U");
                    textViewRight.setText("V");
                    textViewBottom.setText("W");
                    textViewLeft.setText("X");
                    break;
                case R.id.button7:
                    textViewTop.setText("Y");
                    textViewRight.setText("Z");
                    textViewBottom.setText(".");
                    textViewLeft.setText(",");
                    break;
                case R.id.button8:
                    textViewTop.setText("!");
                    textViewRight.setText("?");
                    textViewBottom.setText("@");
                    textViewLeft.setText("€");
                    break;
            }
            middleReleased.setVisibility(View.INVISIBLE);
            middlePressed.setVisibility(View.VISIBLE);
        }else if(event.getAction() == MotionEvent.ACTION_UP){
            middleReleased.setVisibility((View.VISIBLE));
            middlePressed.setVisibility(View.INVISIBLE);

            System.out.println(view.getId());
        }
//        else if(view.getId() == R.id.middleReleased){
//            System.out.println("Yo");
//            return super.onTouchEvent(event);
//        }
//        switch ( event.getAction() ) {
//            case MotionEvent.ACTION_DOWN: System.out.println("Down");
//                break;
//            case MotionEvent.ACTION_UP: System.out.println("Up");
//                System.out.println(event.getX() + "::" + event.getY());
//                break;
//        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gestureDetector.onTouchEvent(event)) {
            return true;
        }
        return super.onTouchEvent(event);
    }


    private void onLeftSwipe() {

        System.out.println("Left");
        // Do something
    }

    private void onRightSwipe() {
        // Do something

        System.out.println("Right");
    }



    // Private class for gestures
    private class SwipeGestureDetector
            extends GestureDetector.SimpleOnGestureListener {
        // Swipe properties, you can change it to make the swipe
        // longer or shorter and speed
        private static final int SWIPE_MIN_DISTANCE = 120;
        private static final int SWIPE_MAX_OFF_PATH = 200;
        private static final int SWIPE_THRESHOLD_VELOCITY = 200;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2,
                               float velocityX, float velocityY) {
            try {
                float diffAbs = Math.abs(e1.getY() - e2.getY());
                float diff = e1.getX() - e2.getX();

                if (diffAbs > SWIPE_MAX_OFF_PATH)
                    return false;

                // Left swipe
                if (diff > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    MainActivity.this.onLeftSwipe();

                    // Right swipe
                } else if (-diff > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    MainActivity.this.onRightSwipe();
                }
            } catch (Exception e) {
                Log.e("YourActivity", "Error on gestures");
            }
            return false;
        }
    }
}
