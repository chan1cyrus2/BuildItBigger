package com.example.chan1cyrus2.jokeacitivity;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class JokeFragment extends Fragment {

    public static final String JOKE_INTENT_ID = "com.exmaple.chan1cyrus2.jokeactivity.joke";
    public JokeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Intent intent = getActivity().getIntent();
        View rootView =inflater.inflate(R.layout.fragment_joke, container, false);
        if(intent != null){
            String joke = intent.getStringExtra(JOKE_INTENT_ID);
            TextView jokeView = (TextView)rootView.findViewById(R.id.joke);
            jokeView.setText(joke);
        }
        return rootView;
    }
}
