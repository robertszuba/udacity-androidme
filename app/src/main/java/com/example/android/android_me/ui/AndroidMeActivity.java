/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        if (savedInstanceState == null) {
            initializeFragments();
        }
    }

    private void initializeFragments() {
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
