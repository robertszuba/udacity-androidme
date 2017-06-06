package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;

import java.util.ArrayList;
import java.util.List;

public class BodyPartFragment extends Fragment {

    private List<Integer> imageIds = new ArrayList<>(0);
    private int imageIndex;

    public BodyPartFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fregment_body_part, container, false);
        final ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_image_view);
        if (imageIds.size() > imageIndex) {
            imageView.setImageResource(imageIds.get(imageIndex));
        }

        return rootView;
    }


    public void setImageIds(@NonNull final List<Integer> imageIds) {
        this.imageIds = (imageIds == null) ? new ArrayList<Integer>(0) : imageIds;
    }

    public void setImageIndex(final int imageIndex) {
        this.imageIndex = imageIndex;
    }
}
