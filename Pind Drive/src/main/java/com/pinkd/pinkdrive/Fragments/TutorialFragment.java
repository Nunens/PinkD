package com.pinkd.pinkdrive.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pinkd.pinkdrive.R;

/**
 * Created by admin on 2015/06/11.
 */
public class TutorialFragment extends Fragment implements TutorialPageFragment{
    TextView text;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tutorial_fragment, container, false);
        //return super.onCreateView(inflater, container, savedInstanceState);
        String txt = getArguments().getString("text");
        text = (TextView) view.findViewById(R.id.fr_tutorial_text);
        text.setText(txt);
        return view;
    }
}
