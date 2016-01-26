package textinput.mhci.uulm.de.androidweartextinput;

import android.app.Activity;
import android.gesture.GestureOverlayView;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.wearable.view.DismissOverlayView;
import android.support.wearable.view.WatchViewStub;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends Activity implements View.OnTouchListener, GestureDetector.OnGestureListener{

    public static final int SYMBOLSET_UPPER = 1;
    public static final int SYMBOLSET_LOWER = 0;
    public static final int SYMBOLSET_ADDITIONAL = 2;

    // view attributes

    private View rootView;


    private RelativeLayout relLayoutTextContent, relLayoutSwipeContent, relLayoutInner;
    private TextView tvTextInput;
    private TextView tvInnerTop, tvInnerRight, tvInnerBottom, tvInnerLeft;

    private DismissOverlayView dismissOverlayView;

    private GestureDetector gestureDetector;
    private GestureDetector longPressDetector;

    private Button tvTopLeft, tvTopRight, tvRightTop, tvRightBottom, tvBottomRight, tvBottomLeft, tvLeftBottom, tvLeftTop;
    // app attributes
    private String strInput;
    private boolean isRound;
    private int symbolSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // infalter listener
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        // detect smartwatch shape
        stub.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            @Override
            public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                System.out.println("SELECT LAYOUT");
                if (insets.isRound()) {
                    System.out.println("ROUND LAYOUT");
                    isRound = true;
                    rootView = getLayoutInflater().inflate(R.layout.round_activity_main, stub);

                } else {
                    System.out.println("SQUARE LAYOUT");
                    isRound = false;
                    rootView = getLayoutInflater().inflate(R.layout.rect_activity_main, stub);
                }
                symbolSet = SYMBOLSET_UPPER;
                // read ui elements
                initialiseUi(rootView);
                // create gestureDetector
                gestureDetector = new GestureDetector(MainActivity.this, MainActivity.this);
                longPressDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public void onLongPress(MotionEvent e) {
                        System.out.println("ON LONG PRESS");
                        dismissOverlayView.show();
                        rootView.setVisibility(View.INVISIBLE);
                    }
                });
                dismissOverlayView.setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        rootView.setVisibility(View.VISIBLE);
                        return false;
                    }
                });
                return insets;
            }
        });
    }


    /*
    UI
     */

    /**
     * Read and instantiate all necessary UI elements.
     * @param rootView
     */
    private void initialiseUi(View rootView) {
        // get outer TextViews
        tvTopLeft = (Button) rootView.findViewById(R.id.tvTopLeft);
        tvTopLeft.setOnTouchListener(this);
        tvTopRight = (Button) rootView.findViewById(R.id.tvTopRight);
        tvTopRight.setOnTouchListener(this);
        tvRightTop = (Button) rootView.findViewById(R.id.tvRightTop);
        tvRightTop.setOnTouchListener(this);
        tvRightBottom = (Button) rootView.findViewById(R.id.tvRightBottom);
        tvRightBottom.setOnTouchListener(this);
        tvBottomRight = (Button) rootView.findViewById(R.id.tvBottomRight);
        tvBottomRight.setOnTouchListener(this);
        tvBottomLeft = (Button) rootView.findViewById(R.id.tvBottomLeft);
        tvBottomLeft.setOnTouchListener(this);
        tvLeftBottom = (Button) rootView.findViewById(R.id.tvLeftBottom);
        tvLeftBottom.setOnTouchListener(this);
        tvLeftTop = (Button) rootView.findViewById(R.id.tvLeftTop);
        tvLeftTop.setOnTouchListener(this);
        // get inner RelativeLayouts
        relLayoutSwipeContent = (RelativeLayout) rootView.findViewById(R.id.relLayout_SwipeContent);
        relLayoutTextContent = (RelativeLayout) rootView.findViewById(R.id.relLayout_TextContent);
        relLayoutInner = (RelativeLayout) rootView.findViewById(R.id.relLayout_Inner);
        // get TextContent TextView
        tvTextInput = (TextView) rootView.findViewById(R.id.tvTextInput);
        // get SwipeContent TextViews
        tvInnerTop = (TextView) rootView.findViewById(R.id.tvInnerTop);
        tvInnerRight = (TextView) rootView.findViewById(R.id.tvInnerRight);
        tvInnerBottom = (TextView) rootView.findViewById(R.id.tvInnerBottom);
        tvInnerLeft = (TextView) rootView.findViewById(R.id.tvInnerLeft);
        // get DismissOverlay
        dismissOverlayView = (DismissOverlayView) findViewById(R.id.dismiss_overlay);
        dismissOverlayView.showIntroIfNecessary();


    }

    public void switchInnerLayouts() {
        if(relLayoutSwipeContent != null && relLayoutTextContent != null) {
            if(relLayoutSwipeContent.isShown()) {
                relLayoutSwipeContent.setVisibility(View.GONE);
                relLayoutTextContent.setVisibility(View.VISIBLE);
            } else {
                relLayoutTextContent.setVisibility(View.GONE);
                relLayoutSwipeContent.setVisibility(View.VISIBLE);
            }
        }
    }

    public void changeToUpperCase() {
        tvTopLeft.setText(getResources().getString(R.string.text_top_left_upper));
        tvTopRight.setText(getResources().getString(R.string.text_top_right_upper));
        tvRightTop.setText(getResources().getString(R.string.text_right_top_upper));
        tvRightBottom.setText(getResources().getString(R.string.text_right_bottom_upper));
        tvBottomRight.setText(getResources().getString(R.string.text_bottom_right_upper));
        tvBottomLeft.setText(getResources().getString(R.string.text_bottom_left_upper));
        tvLeftBottom.setText(getResources().getString(R.string.text_left_bottom_upper));
        tvLeftTop.setText(getResources().getString(R.string.text_left_top_upper));
    }

    public void changeToLowerCase() {
        tvTopLeft.setText(getResources().getString(R.string.text_top_left_lower));
        tvTopRight.setText(getResources().getString(R.string.text_top_right_lower));
        tvRightTop.setText(getResources().getString(R.string.text_right_top_lower));
        tvRightBottom.setText(getResources().getString(R.string.text_right_bottom_lower));
        tvBottomRight.setText(getResources().getString(R.string.text_bottom_right_lower));
        tvBottomLeft.setText(getResources().getString(R.string.text_bottom_left_lower));
        tvLeftBottom.setText(getResources().getString(R.string.text_left_bottom_lower));
        tvLeftTop.setText(getResources().getString(R.string.text_left_top_lower));
    }

    public void changeToAdditionals() {
        tvTopLeft.setText(getResources().getString(R.string.text_top_left_additional));
        tvTopRight.setText(getResources().getString(R.string.text_top_right_additional));
        tvRightTop.setText(getResources().getString(R.string.text_right_top_additional));
        tvRightBottom.setText(getResources().getString(R.string.text_right_bottom_additional));
        tvBottomRight.setText(getResources().getString(R.string.text_bottom_right_additional));
        tvBottomLeft.setText(getResources().getString(R.string.text_bottom_left_additional));
        tvLeftBottom.setText(getResources().getString(R.string.text_left_bottom_additional));
        tvLeftTop.setText(getResources().getString(R.string.text_left_top_additional));
    }

    /*
    LISTENER
     */



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //System.out.println("ONTOUCHEVENT");
        return super.onTouchEvent(event) || longPressDetector.onTouchEvent(event) || gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
       // System.out.println(event.getAction());
        System.out.println("ONTOUCH");
   //     if(view != null) {
            if(event.getAction() == MotionEvent.ACTION_DOWN && view.getId() != R.id.relLayout_TextContent) {
                System.out.println("ON TOUCH TEXTVIEW");
                // action down
                int id = view.getId();
                handleOuterTextViewTouch(id);
                switchInnerLayouts();

       //     }
        } else if(event.getAction() == MotionEvent.ACTION_UP) {
            System.out.println("ACTION UP");
            // action up
            int x = (int) event.getRawX();
            int y = (int) event.getRawY();
            PointF topLeft = new PointF(relLayoutInner.getX(), relLayoutInner.getY());
            PointF topRight = new PointF(relLayoutInner.getX()+relLayoutInner.getWidth(), relLayoutInner.getY());
            PointF bottomLeft = new PointF(relLayoutInner.getX(), relLayoutInner.getY()+relLayoutInner.getHeight());
            PointF bottomRight = new PointF(relLayoutInner.getX()+relLayoutInner.getWidth(), relLayoutInner.getY()+relLayoutInner.getHeight());
            PointF middle = new PointF(relLayoutInner.getX()+relLayoutInner.getWidth()/2, relLayoutInner.getY()+relLayoutInner.getHeight()/2);
            PointF pressed =  new PointF( x, y);
            // check if swiped to middle
            if(pointInTriangle(topLeft,topRight,middle,pressed)) {
                String str1 = tvTextInput.getText().toString();
                String str2 = tvInnerTop.getText().toString();
                tvTextInput.setText(str1 + str2);
            }
            if(pointInTriangle(topLeft,bottomLeft,middle,pressed)) {
                String str1 = tvTextInput.getText().toString();
                String str2 = tvInnerLeft.getText().toString();
                tvTextInput.setText(str1 + str2);
            }
            if(pointInTriangle(bottomLeft,bottomRight,middle,pressed)) {
                String str1 = tvTextInput.getText().toString();
                String str2 = tvInnerBottom.getText().toString();
                tvTextInput.setText(str1 + str2);
            }
            if(pointInTriangle(topRight,bottomRight,middle,pressed)) {
                String str1 = tvTextInput.getText().toString();
                String str2 = tvInnerRight.getText().toString();
                tvTextInput.setText(str1 + str2);
            }
            switchInnerLayouts();
        }
        return false;
    }

    /**
     * Checks if ACTION_UP in Triangle.
     * @param point1
     * @param point2
     * @param point3
     * @param pointPressed
     * @return
     */
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

    /**
     * Handles Touch event on outerTextViews.
     * @param id
     */
    public void handleOuterTextViewTouch(int id) {
        switch(id) {
            case R.id.tvTopLeft:
                if(symbolSet == SYMBOLSET_LOWER) {
                    setInnerTextViews("a", "b", "c", "d");
                } else if(symbolSet == SYMBOLSET_UPPER) {
                    setInnerTextViews("A", "B", "C", "D");
                } else {
                    setInnerTextViews("1", "2", "3", "4");
                }
                break;
            case R.id.tvTopRight:
                if(symbolSet == SYMBOLSET_LOWER) {
                    setInnerTextViews("e", "f", "g", "h");
                } else if(symbolSet == SYMBOLSET_UPPER) {
                    setInnerTextViews("E", "F", "G", "H");
                } else {
                    setInnerTextViews("5", "6", "7", "8");
                }
                break;
            case R.id.tvRightTop:
                if(symbolSet == SYMBOLSET_LOWER) {
                    setInnerTextViews("i", "j", "k", "l");
                } else if(symbolSet == SYMBOLSET_UPPER) {
                    setInnerTextViews("I", "J", "K", "L");
                } else {
                    setInnerTextViews("9", "0", "<", ">");
                }
                break;
            case R.id.tvRightBottom:
                if(symbolSet == SYMBOLSET_LOWER) {
                    setInnerTextViews("m", "n", "o", "p");
                } else if(symbolSet == SYMBOLSET_UPPER) {
                    setInnerTextViews("M", "N", "O", "P");
                } else {
                    setInnerTextViews("+", "-", "*", "/");
                }
                break;
            case R.id.tvBottomRight:
                if(symbolSet == SYMBOLSET_LOWER) {
                    setInnerTextViews("q", "r", "s", "t");
                } else if(symbolSet == SYMBOLSET_UPPER) {
                    setInnerTextViews("Q", "R", "S", "T");
                } else {
                    setInnerTextViews("(", ")", "[", " ]");
                }
                break;
            case R.id.tvBottomLeft:
                if(symbolSet == SYMBOLSET_LOWER) {
                    setInnerTextViews("u", "v", "w", "x");
                } else if(symbolSet == SYMBOLSET_UPPER) {
                    setInnerTextViews("U", "V", "W", "X");
                } else {
                    setInnerTextViews("%", "$", "§", "\"");
                }
                break;
            case R.id.tvLeftBottom:
                if(symbolSet == SYMBOLSET_LOWER) {
                    setInnerTextViews("y", "z", "ä", "ö");
                } else if(symbolSet == SYMBOLSET_UPPER) {
                    setInnerTextViews("Y", "Z", "Ä", "Ö");
                } else {
                    setInnerTextViews("#", "_", "@", "€");
                }
                break;
            case R.id.tvLeftTop:
                if(symbolSet == SYMBOLSET_LOWER) {
                    setInnerTextViews("ü", "ß", ".", "?");
                } else if(symbolSet == SYMBOLSET_UPPER) {
                    setInnerTextViews("Ü", "&", "!", ",");
                } else {
                    setInnerTextViews("\\", "^", "°", "|");
                }
                break;
            default:
                break;
        }
    }

    /**
     * Sets innerTextViews text values.
     * @param top
     * @param right
     * @param bottom
     * @param left
     */
    public void setInnerTextViews(String top, String right, String bottom, String left) {
        tvInnerTop.setText(top);
        tvInnerRight.setText(right);
        tvInnerBottom.setText(bottom);
        tvInnerLeft.setText(left);
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
        if(symbolSet == SYMBOLSET_LOWER || symbolSet == SYMBOLSET_UPPER){
            symbolSet = SYMBOLSET_ADDITIONAL;
            changeToAdditionals();
        }else{
            symbolSet = SYMBOLSET_UPPER;
            changeToUpperCase();
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
                if (Math.abs(diffX) > 100 && Math.abs(velocityX) > 100) {
                    if (diffX > 0) {
                        onSwipeRight();
                    } else {
                        onSwipeLeft();
                    }
                }
                result = true;
            }
            else if (Math.abs(diffY) > 100 && Math.abs(velocityY) > 100) {
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
        String text = (String) tvTextInput.getText();
        text += " ";
        tvTextInput.setText(text);
    }
    public void onSwipeLeft(){
        String text = (String) tvTextInput.getText();
        if(text.length() > 0) {
            text = text.substring(0, text.length() - 1);
        }
        tvTextInput.setText(text);
    }
    public void onSwipeBottom() {
        // change to LowerCase
        symbolSet = SYMBOLSET_LOWER;
        changeToLowerCase();

    }
    public void onSwipeTop(){
        // change to UpperCase
        symbolSet = SYMBOLSET_UPPER;
        changeToUpperCase();
    }
}
