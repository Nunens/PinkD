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
 * Created by admin on 2015/06/18.
 */
public class MythFragment extends Fragment implements MythPageFragment {

    View view;
    TextView text;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.myth_fragment, container, false);
        //return super.onCreateView(inflater, container, savedInstanceState);
        String txt = getArguments().getString("text");
        text = (TextView) view.findViewById(R.id.fr_myth_text);
        text.setText(txt);
        return view;
    }
}
