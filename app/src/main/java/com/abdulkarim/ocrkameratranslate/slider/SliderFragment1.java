package com.abdulkarim.ocrkameratranslate.slider;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.abdulkarim.ocrkameratranslate.R;

import java.util.Objects;

public class SliderFragment1 extends Fragment {

    private TextView test;

    public SliderFragment1() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slider, container, false);
        test = view.findViewById(R.id.test);
        return view;
    }

}
