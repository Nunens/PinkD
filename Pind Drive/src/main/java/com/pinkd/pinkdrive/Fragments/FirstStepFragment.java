package com.pinkd.pinkdrive.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pinkd.pinkdrive.R;

/**
 * Created by admin on 2015/05/27.
 */
public class FirstStepFragment extends Fragment implements TutorialPageFragment{
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.first_step_tutorial, container, false);
        return view;
    }
}
