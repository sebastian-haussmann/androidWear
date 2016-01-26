package textinput.mhci.uulm.de.androidweartextinput;

import android.app.Activity;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnTouchListener, GestureOverlayView.OnGestureListener{

    // view attributes
    private TextView tvTopLeft, tvTopRight, tvRightTop, tvRightBottom, tvBottomRight, tvBottomLeft, tvLeftBottom, tvLeftTop;

    private RelativeLayout relLayoutTextContent, relLayoutSwipeContent;
    private TextView tvTextInput;
    private TextView tvInnerTop, tvInnerRight, tvInnerBottom, tvInnerLeft;

    // app attributes
    private String strInput;
    private boolean isRound;


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
                if (insets.isRound()) {
                    System.out.println("ROUND LAYOUT");
                    isRound = true;
                    rootView = getLayoutInflater().inflate(R.layout.round_activity_main, stub);

                } else {
                    System.out.println("SQUARE LAYOUT");
                    isRound = false;
                    rootView = getLayoutInflater().inflate(R.layout.rect_activity_main, stub);
                }
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
        tvTopRight = (TextView) rootView.findViewById(R.id.tvTopRight);
        tvRightTop = (TextView) rootView.findViewById(R.id.tvRightTop);
        tvRightBottom = (TextView) rootView.findViewById(R.id.tvRightBottom);
        tvBottomRight = (TextView) rootView.findViewById(R.id.tvBottomRight);
        tvBottomLeft = (TextView) rootView.findViewById(R.id.tvBottomLeft);
        tvLeftBottom = (TextView) rootView.findViewById(R.id.tvLeftBottom);
        tvLeftTop = (TextView) rootView.findViewById(R.id.tvLeftTop);
        // get inner RelativeLayouts
        relLayoutSwipeContent = (RelativeLayout) rootView.findViewById(R.id.relLayout_SwipeContent);
        relLayoutTextContent = (RelativeLayout) rootView.findViewById(R.id.relLayout_TextContent);
        // get TextContent TextView
        tvTextInput = (TextView) rootView.findViewById(R.id.tvTextInput);
        // get SwipeContent TextViews
        tvInnerTop = (TextView) rootView.findViewById(R.id.tvInnerTop);
        tvInnerRight = (TextView) rootView.findViewById(R.id.tvInnerRight);
        tvInnerBottom = (TextView) rootView.findViewById(R.id.tvInnerBottom);
        tvInnerLeft = (TextView) rootView.findViewById(R.id.tvInnerBottom);
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
    public void onGestureStarted(GestureOverlayView overlay, MotionEvent event) {
        // TODO
    }

    @Override
    public void onGesture(GestureOverlayView overlay, MotionEvent event) {
        // TODO
    }

    @Override
    public void onGestureEnded(GestureOverlayView overlay, MotionEvent event) {
        // TODO
    }

    @Override
    public void onGestureCancelled(GestureOverlayView overlay, MotionEvent event) {
        // TODO
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // TODO
        return false;
    }
}
