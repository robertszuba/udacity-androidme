package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    public static final String EXTRA_HEAD = "head";
    public static final String EXTRA_BODY = "body";
    public static final String EXTRA_LEGS = "legs";

    private int headPartIndex;
    private int bodyPartIndex;
    private int legPartIndex;

    private boolean isTwoPaneMode;

    private Button nextButton;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkIfIsTwoPaneMode();
        if (isTwoPaneMode) {
            initializeTwoPaneMode(savedInstanceState);
        } else {
            nextButton = (Button) findViewById(R.id.next_button);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    sendIntent();
                }
            });
        }
    }

    @Override
    public void onImageClicked(final int position) {
        final int part = position / 12;
        final int index = position % 12;

        if (isTwoPaneMode) {
            final BodyPartFragment newFragment = new BodyPartFragment();
            int fragmentId;
            if (part == 0) {
                newFragment.setImageIds(AndroidImageAssets.getHeads());
                fragmentId = R.id.head_container;
            } else if (part == 1) {
                newFragment.setImageIds(AndroidImageAssets.getBodies());
                fragmentId = R.id.body_container;
            } else {
                newFragment.setImageIds(AndroidImageAssets.getLegs());
                fragmentId = R.id.leg_container;
            }
            newFragment.setImageIndex(index);
            getSupportFragmentManager().beginTransaction()
                    .replace(fragmentId, newFragment)
                    .commit();
        } else {
            if (part == 0) {
                headPartIndex = index;
            } else if (part == 1) {
                bodyPartIndex = index;
            } else {
                legPartIndex = index;
            }
        }
    }

    private void checkIfIsTwoPaneMode() {
        isTwoPaneMode = (findViewById(R.id.android_me_linear_layout) != null);
    }

    private void initializeTwoPaneMode(final Bundle savedInstanceState) {
        nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setVisibility(View.GONE);

        final GridView gridView = (GridView) findViewById(R.id.body_grid_view);
        gridView.setNumColumns(2);

        if (savedInstanceState == null) {
            final BodyPartFragment headPartFragment = new BodyPartFragment();
            final BodyPartFragment bodyPartFragment = new BodyPartFragment();
            final BodyPartFragment legPartFragment = new BodyPartFragment();


            headPartFragment.setImageIds(AndroidImageAssets.getHeads());
            bodyPartFragment.setImageIds(AndroidImageAssets.getBodies());
            legPartFragment.setImageIds(AndroidImageAssets.getLegs());

            final Intent intent = getIntent();
            headPartFragment.setImageIndex(intent.getIntExtra(MainActivity.EXTRA_HEAD, 0));
            bodyPartFragment.setImageIndex(intent.getIntExtra(MainActivity.EXTRA_BODY, 0));
            legPartFragment.setImageIndex(intent.getIntExtra(MainActivity.EXTRA_LEGS, 0));

            final FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .add(R.id.head_container, headPartFragment)
                    .add(R.id.body_container, bodyPartFragment)
                    .add(R.id.leg_container, legPartFragment)
                    .commit();
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
