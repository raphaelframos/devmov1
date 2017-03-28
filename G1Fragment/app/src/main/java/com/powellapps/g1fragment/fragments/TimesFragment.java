package com.powellapps.g1fragment.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.powellapps.g1fragment.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimesFragment extends Fragment {

    private Button buttonFlamengo;

    public TimesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_times, container, false);
        buttonFlamengo = (Button) view.findViewById(R.id.buttonFlamengo);
        buttonFlamengo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FlamengoFragment flamengoFragment = new FlamengoFragment();
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.time, flamengoFragment).commit();
            }
        });

        return view;
    }


}
