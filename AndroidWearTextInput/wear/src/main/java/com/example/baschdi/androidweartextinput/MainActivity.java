package com.example.baschdi.androidweartextinput;

import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.wearable.view.DismissOverlayView;
import android.support.wearable.view.WatchViewStub;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.baschdi.firstwearapp.R;

public class MainActivity extends Activity implements View.OnTouchListener, GestureDetector.OnGestureListener {

    private TextView mTextView;

    private Button button1, button2, button3, button4, button5, button6, button7, button8;
    private TextView textViewTop, textViewRight, textViewBottom, textViewLeft, textViewField;
    private RelativeLayout middlePressed, middleReleased, rootView;

    private GestureDetector detector;
    private DismissOverlayView mDismissOverlay;
    private GestureDetector mDetector;

    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;

    private int symbolSet = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);



//        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
//        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
//            @Override
//            public void onLayoutInflated(WatchViewStub stub) {
//                mTextView = (TextView) stub.findViewById(R.id.text);
//            }
//        });

        setContentView(R.layout.rect_activity_main);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);

        middlePressed = (RelativeLayout) findViewById(R.id.middlePressed);
        middleReleased = (RelativeLayout) findViewById(R.id.middleReleased);
        rootView = (RelativeLayout) findViewById(R.id.rootView);

        textViewTop = (TextView) findViewById(R.id.textViewTop);
//        setTextViewClippingBounds(textViewTop);
        textViewRight = (TextView) findViewById(R.id.textViewRight);
//        setTextViewClippingBounds(textViewRight);
        textViewBottom = (TextView) findViewById(R.id.textViewBottom);
//        setTextViewClippingBounds(textViewBottom);
        textViewLeft = (TextView) findViewById(R.id.textViewLeft);
//        setTextViewClippingBounds(textViewLeft);
        textViewField = (TextView) findViewById(R.id.textViewField);




        button1.setOnTouchListener(this);
        button2.setOnTouchListener(this);
        button3.setOnTouchListener(this);
        button4.setOnTouchListener(this);
        button5.setOnTouchListener(this);
        button6.setOnTouchListener(this);
        button7.setOnTouchListener(this);
        button8.setOnTouchListener(this);


        detector = new GestureDetector(this,this);

        // Obtain the DismissOverlayView element
        mDismissOverlay = (DismissOverlayView) findViewById(R.id.dismiss_overlay);
        mDismissOverlay.showIntroIfNecessary();


        mDismissOverlay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                rootView.setVisibility(View.VISIBLE);
                return false;
            }
        });

        mDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            public void onLongPress(MotionEvent ev) {
                mDismissOverlay.show();

                rootView.setVisibility(View.INVISIBLE);
            }
        });
    }

//    public void setTextViewClippingBounds(TextView txtView) {
//        final TextView view = txtView;
//        txtView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                int[] location = new int[2];
//                view.invalidate();
//                view.getLocationOnScreen(location);
//                int x1 = location[0];
//                int x2 = x1 + view.getWidth();
//                int y1 = location[1];
//                int y2 = y1 +  view.getHeight();
//
//                view.setClipBounds(new Rect(x1, y1, x2, y2));
//            }
//        }, 1);
//    }

    // Capture long presses
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        return mDetector.onTouchEvent(ev) || super.onTouchEvent(ev) || detector.onTouchEvent(ev) ;
    }


    public boolean onTouch(View view, MotionEvent event) {
//        System.out.println(event.getAction() + "::::" + view);
        if (event.getAction() == MotionEvent.ACTION_DOWN && view.getId() != R.id.middleReleased) {
            switch (view.getId()) {
                case R.id.button1:
                    if(symbolSet == 0) {
                        textViewTop.setText("A");
                        textViewRight.setText("B");
                        textViewBottom.setText("C");
                        textViewLeft.setText("D");
                    }else if(symbolSet == 1){
                        textViewTop.setText("a");
                        textViewRight.setText("b");
                        textViewBottom.setText("c");
                        textViewLeft.setText("d");
                    }else{
                        textViewTop.setText("1");
                        textViewRight.setText("2");
                        textViewBottom.setText("3");
                        textViewLeft.setText("4");
                    }
                    break;
                case R.id.button2:
                    if(symbolSet == 0) {
                        textViewTop.setText("E");
                        textViewRight.setText("F");
                        textViewBottom.setText("G");
                        textViewLeft.setText("H");
                    }else if(symbolSet == 1){
                        textViewTop.setText("e");
                        textViewRight.setText("f");
                        textViewBottom.setText("g");
                        textViewLeft.setText("h");
                    }else{
                        textViewTop.setText("5");
                        textViewRight.setText("6");
                        textViewBottom.setText("7");
                        textViewLeft.setText("8");
                    }
                    break;
                case R.id.button3:
                    if(symbolSet == 0) {
                        textViewTop.setText("I");
                        textViewRight.setText("J");
                        textViewBottom.setText("K");
                        textViewLeft.setText("L");
                    }else if(symbolSet == 1){
                        textViewTop.setText("i");
                        textViewRight.setText("j");
                        textViewBottom.setText("k");
                        textViewLeft.setText("l");
                    }else{
                        textViewTop.setText("9");
                        textViewRight.setText("0");
                        textViewBottom.setText("<");
                        textViewLeft.setText(">");
                    }
                    break;
                case R.id.button4:
                    if(symbolSet == 0) {
                        textViewTop.setText("M");
                        textViewRight.setText("N");
                        textViewBottom.setText("O");
                        textViewLeft.setText("P");
                    }else if(symbolSet == 1){
                        textViewTop.setText("m");
                        textViewRight.setText("n");
                        textViewBottom.setText("o");
                        textViewLeft.setText("p");
                    }else{
                        textViewTop.setText("+");
                        textViewRight.setText("-");
                        textViewBottom.setText("*");
                        textViewLeft.setText("/");
                    }
                    break;
                case R.id.button5:
                    if(symbolSet == 0) {
                        textViewTop.setText("Q");
                        textViewRight.setText("R");
                        textViewBottom.setText("S");
                        textViewLeft.setText("T");
                    }else if(symbolSet == 1){
                        textViewTop.setText("q");
                        textViewRight.setText("r");
                        textViewBottom.setText("s");
                        textViewLeft.setText("t");
                    }else{
                        textViewTop.setText("(");
                        textViewRight.setText(")");
                        textViewBottom.setText("[");
                        textViewLeft.setText("]");
                    }
                    break;
                case R.id.button6:
                    if(symbolSet == 0) {
                        textViewTop.setText("U");
                        textViewRight.setText("V");
                        textViewBottom.setText("W");
                        textViewLeft.setText("X");
                    }else if(symbolSet == 1){
                        textViewTop.setText("u");
                        textViewRight.setText("v");
                        textViewBottom.setText("w");
                        textViewLeft.setText("x");
                    }else{
                        textViewTop.setText("%");
                        textViewRight.setText("$");
                        textViewBottom.setText("§");
                        textViewLeft.setText("\"");
                    }
                    break;
                case R.id.button7:
                    if(symbolSet == 0) {
                        textViewTop.setText("Y");
                        textViewRight.setText("Z");
                        textViewBottom.setText(".");
                        textViewLeft.setText(",");
                    }else if(symbolSet == 1){
                        textViewTop.setText("y");
                        textViewRight.setText("z");
                        textViewBottom.setText(":");
                        textViewLeft.setText(";");
                    }else{
                        textViewTop.setText("ß");
                        textViewRight.setText("ä");
                        textViewBottom.setText("ö");
                        textViewLeft.setText("ü");
                    }
                    break;
                case R.id.button8:
                    if(symbolSet == 0) {
                        textViewTop.setText("!");
                        textViewRight.setText("?");
                        textViewBottom.setText("@");
                        textViewLeft.setText("€");
                    }else if(symbolSet == 1){
                        textViewTop.setText("#");
                        textViewRight.setText("-");
                        textViewBottom.setText("_");
                        textViewLeft.setText("&");
                    }else{
                        textViewTop.setText("^");
                        textViewRight.setText("°");
                        textViewBottom.setText("'");
                        textViewLeft.setText("|");
                    }
                    break;
            }
            middleReleased.setVisibility(View.INVISIBLE);
            middlePressed.setVisibility(View.VISIBLE);
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            int x = (int) event.getRawX();
            int y = (int) event.getRawY();
            PointF topLeft = new PointF(middlePressed.getX(), middlePressed.getY());
            PointF topRight = new PointF(middlePressed.getX()+middlePressed.getWidth(), middlePressed.getY());
            PointF bottomLeft = new PointF(middlePressed.getX(), middlePressed.getY()+middlePressed.getHeight());
            PointF bottomRight = new PointF(middlePressed.getX()+middlePressed.getWidth(), middlePressed.getY()+middlePressed.getHeight());
            PointF middle = new PointF(middlePressed.getX()+middlePressed.getWidth()/2, middlePressed.getY()+middlePressed.getHeight()/2);
            PointF pressed =  new PointF( x, y);

            if(pointInTriangle(topLeft,topRight,middle,pressed)) {
                String str1 = textViewField.getText().toString();
                String str2 = textViewTop.getText().toString();
                textViewField.setText(str1 + str2);
            }
            if(pointInTriangle(topLeft,bottomLeft,middle,pressed)) {
                String str1 = textViewField.getText().toString();
                String str2 = textViewLeft.getText().toString();
                textViewField.setText(str1 + str2);
            }
            if(pointInTriangle(bottomLeft,bottomRight,middle,pressed)) {
                String str1 = textViewField.getText().toString();
                String str2 = textViewBottom.getText().toString();
                textViewField.setText(str1 + str2);
            }
            if(pointInTriangle(topRight,bottomRight,middle,pressed)) {
                String str1 = textViewField.getText().toString();
                String str2 = textViewRight.getText().toString();
                textViewField.setText(str1 + str2);
            }

            middlePressed.setVisibility(View.INVISIBLE);
            middleReleased.setVisibility((View.VISIBLE));
        }


        return true;
    }

    public boolean pointInTriangle(PointF point1, PointF point2, PointF point3, PointF pointPressed){
        float value1 = ((point2.y - point3.y)*(pointPressed.x - point3.x) + (point3.x - point2.x)*(pointPressed.y - point3.y)) /
                ((point2.y - point3.y)*(point1.x - point3.x) + (point3.x - point2.x)*(point1.y - point3.y));
        float value2 = ((point3.y - point1.y)*(pointPressed.x - point3.x) + (point1.x - point3.x)*(pointPressed.y - point3.y)) /
                ((point2.y - point3.y)*(point1.x - point3.x) + (point3.x - point2.x)*(point1.y - point3.y));
        float value3 = 1.0f - value1 - value2;

        if(value1 > 0 && value2 > 0 && value3 > 0){
            return true;
        }else{
            return false;
        }
    }



    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        if(symbolSet == 0 || symbolSet == 1){
            symbolSet = 2;
            button1.setText("1  2  3  4");
            button2.setText("5  6  7  8");
            button3.setText("9   0   <   >");
            button4.setText("+   -   *   /");
            button5.setText("]  [  )  (");
            button6.setText("\"  §  $  %");
            button7.setText("ü    ö   ä   ß");
            button8.setText("|    '   °   ^");
        }else{
            symbolSet = 0;
            button1.setText("A  B  C  D");
            button2.setText("E  F  G  H");
            button3.setText("I   J   K   L");
            button4.setText("M   N   O   P");
            button5.setText("T  S  R  Q");
            button6.setText("X  W  V  U");
            button7.setText(",    .   Z   Y");
            button8.setText("€   @   ?   !");
        }
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        boolean result = false;
        try {
            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        onSwipeRight();
                    } else {
                        onSwipeLeft();
                    }
                }
                result = true;
            }
            else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY > 0) {
                    onSwipeBottom();
                } else {
                    onSwipeTop();
                }
            }
            result = true;

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return result;

    }

    public void onSwipeRight(){
        String text = (String) textViewField.getText();
        text += " ";
        textViewField.setText(text);
    }
    public void onSwipeLeft(){
        String text = (String) textViewField.getText();
        if(text.length() > 0) {
            text = text.substring(0, text.length() - 1);
        }
        textViewField.setText(text);
    }
    public void onSwipeBottom(){
        // TODO: kleinbuchstaben layout
        if(symbolSet == 0){
            symbolSet = 1;
            button1.setText("a  b  c  d");
            button2.setText("e  f  g  h");
            button3.setText("i   j   k   l");
            button4.setText("m   n   o   p");
            button5.setText("t  s  r  q");
            button6.setText("x  w  v  u");
            button7.setText(";    :   z   y");
            button8.setText("&   _   -   #");
        }
    }
    public void onSwipeTop(){
        // TODO: großbuchstaben layout
        if(symbolSet == 1){
            symbolSet = 0;
            button1.setText("A  B  C  D");
            button2.setText("E  F  G  H");
            button3.setText("I   J   K   L");
            button4.setText("M   N   O   P");
            button5.setText("T  S  R  Q");
            button6.setText("X  W  V  U");
            button7.setText(",    .   Z   Y");
            button8.setText("€   @   ?   !");
        }
    }

}