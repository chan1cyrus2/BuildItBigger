package com.udacity.gradle.builditbigger;

/**
 * Created by chan1cyrus2 on 11/21/2015.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button jokeButton = (Button) root.findViewById(R.id.jokeButton);
        jokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set up progress bar
                ProgressBar pgBar = (ProgressBar) root.findViewById(R.id.pbHeaderProgress);
                pgBar.setVisibility(View.VISIBLE);
                new EndpointsAsyncTask().execute(getActivity());
            }
        });

        return root;
    }
}
