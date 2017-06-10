package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.android.android_me.R;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    public static final String EXTRA_HEAD = "head";
    public static final String EXTRA_BODY = "body";
    public static final String EXTRA_LEGS = "legs";

    private int headPartIndex;
    private int bodyPartIndex;
    private int legPartIndex;

    private Button nextButton;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                sendIntent();
            }
        });
    }

    @Override
    public void onImageClicked(final int position) {
        final int part = position / 12;
        final int index = position % 12;

        if (part == 0) {
            headPartIndex = index;
        } else if (part == 1) {
            bodyPartIndex = index;
        } else {
            legPartIndex = index;
        }
    }

    private void sendIntent() {
        final Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_HEAD, headPartIndex);
        bundle.putInt(EXTRA_BODY, bodyPartIndex);
        bundle.putInt(EXTRA_LEGS, legPartIndex);

        final Intent intent = new Intent(this, AndroidMeActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
