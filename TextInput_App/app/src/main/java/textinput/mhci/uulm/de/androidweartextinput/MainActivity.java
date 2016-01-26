package textinput.mhci.uulm.de.androidweartextinput;

import android.app.Activity;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnTouchListener, GestureDetector.OnGestureListener{

    public static final int SYMBOLSET_UPPER = 1;
    public static final int SYMBOLSET_LOWER = 0;
    public static final int SYMBOLSET_ADDITIONAL = 2;

    // view attributes
    private TextView tvTopLeft, tvTopRight, tvRightTop, tvRightBottom, tvBottomRight, tvBottomLeft, tvLeftBottom, tvLeftTop;

    private RelativeLayout relLayoutTextContent, relLayoutSwipeContent;
    private TextView tvTextInput;
    private TextView tvInnerTop, tvInnerRight, tvInnerBottom, tvInnerLeft;

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
                View rootView;
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
        tvTopLeft = (TextView) rootView.findViewById(R.id.tvTopLeft);
        tvTopLeft.setOnTouchListener(this);
        tvTopRight = (TextView) rootView.findViewById(R.id.tvTopRight);
        tvTopRight.setOnTouchListener(this);
        tvRightTop = (TextView) rootView.findViewById(R.id.tvRightTop);
        tvRightTop.setOnTouchListener(this);
        tvRightBottom = (TextView) rootView.findViewById(R.id.tvRightBottom);
        tvRightBottom.setOnTouchListener(this);
        tvBottomRight = (TextView) rootView.findViewById(R.id.tvBottomRight);
        tvBottomRight.setOnTouchListener(this);
        tvBottomLeft = (TextView) rootView.findViewById(R.id.tvBottomLeft);
        tvBottomLeft.setOnTouchListener(this);
        tvLeftBottom = (TextView) rootView.findViewById(R.id.tvLeftBottom);
        tvLeftBottom.setOnTouchListener(this);
        tvLeftTop = (TextView) rootView.findViewById(R.id.tvLeftTop);
        tvLeftTop.setOnTouchListener(this);
        // get inner RelativeLayouts
        relLayoutSwipeContent = (RelativeLayout) rootView.findViewById(R.id.relLayout_SwipeContent);
        relLayoutTextContent = (RelativeLayout) rootView.findViewById(R.id.relLayout_TextContent);
        // get TextContent TextView
        tvTextInput = (TextView) rootView.findViewById(R.id.tvTextInput);
        // get SwipeContent TextViews
        tvInnerTop = (TextView) rootView.findViewById(R.id.tvInnerTop);
        tvInnerRight = (TextView) rootView.findViewById(R.id.tvInnerRight);
        tvInnerBottom = (TextView) rootView.findViewById(R.id.tvInnerBottom);
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
        // TODO
    }

    /*
    LISTENER
     */

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        System.out.println("ONTOUCH");
        if(event.getAction() == MotionEvent.ACTION_DOWN && view.getId() != R.id.relLayout_Inner ) {
            System.out.println("ON TOUCH TEXTVIEW");
            // button clicked
            int id = view.getId();
            handleOuterTextViewTouch(id);
            relLayoutTextContent.setVisibility(View.GONE);
            relLayoutSwipeContent.setVisibility(View.VISIBLE);
        }

        if(event.getAction() == MotionEvent.ACTION_UP) {
            relLayoutSwipeContent.setVisibility(View.GONE);
            relLayoutTextContent.setVisibility(View.VISIBLE);
        }
        return false;
    }

    /**
     * Changes innerTextViews text.
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
                    setInnerTextViews("a", "b", "c", "d");
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
                    setInnerTextViews("U", "B", "C", "X");
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
                    setInnerTextViews("1", "2", "3", "4");
                }
                break;
            case R.id.tvLeftTop:
                if(symbolSet == SYMBOLSET_LOWER) {
                    setInnerTextViews("a", "b", "c", "d");
                } else if(symbolSet == SYMBOLSET_UPPER) {
                    setInnerTextViews("A", "B", "C", "D");
                } else {
                    setInnerTextViews("1", "2", "3", "4");
                }
                break;
            default:
                break;
        }
    }

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
        return false;
    }
}
