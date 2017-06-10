package com.example.android.android_me.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class MasterListFragment extends Fragment {

    OnImageClickListener listener;

    public interface OnImageClickListener {
        void onImageClicked(int position);
    }

    public MasterListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);
        final GridView gridView = (GridView) rootView.findViewById(R.id.body_grid_view);
        final MasterListAdapter adapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
                listener.onImageClicked(position);
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);

        try {
            listener = (OnImageClickListener) context;
        } catch (final ClassCastException e) {
            throw new ClassCastException("The activity doesn't implement the OnImageListener interface");
        }
    }
}
