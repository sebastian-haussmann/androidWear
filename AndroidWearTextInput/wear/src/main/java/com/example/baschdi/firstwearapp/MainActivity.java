package com.example.baschdi.firstwearapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    private TextView mTextView;


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

        System.out.println("Test");
        Button buttonMain = (Button) findViewById(R.id.button2);
        System.out.println(buttonMain);
        // geht leider noch nicht. Quelle: http://developer.android.com/guide/topics/ui/ui-events.html
     //   buttonMain.setOnClickListener(this);
    }
    public void onClick(View v) {
        System.out.println("Test2");
    }

}
