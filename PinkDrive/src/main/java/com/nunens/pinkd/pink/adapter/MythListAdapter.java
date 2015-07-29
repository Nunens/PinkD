package com.nunens.pinkd.pink.adapter;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nunens.pinkd.dto.MythDTO;
import com.nunens.pinkd.pink.R;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by admin on 2015/03/03.
 */
public class MythListAdapter extends ArrayAdapter<MythDTO> {
    private final int layoutRes;
    private final LayoutInflater inflater;
    private List<MythDTO> list;
    private Context context;
    private String log = "MythListAdapter";
    SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd, HH:mm");

    public MythListAdapter(Context context, int resource, List<MythDTO> objects) {
        super(context, resource, objects);
        this.layoutRes = resource;
        this.list = objects;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (list == null) {
            Log.i(log, "Myth List is empty");
            return;
        }
        Log.i(log, "Myth List contains records");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = inflater.inflate(layoutRes, null);
            holder.date = (TextView) convertView.findViewById(R.id.mythDate);
            holder.myth = (TextView) convertView.findViewById(R.id.mythText);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        MythDTO dto = list.get(position);
        holder.date.setText(dto.getDate());
        holder.myth.setText(dto.getContent());
        //animate(convertView);
        return convertView;
    }

    void animate(View v) {
        final ObjectAnimator an = ObjectAnimator.ofFloat(v, View.SCALE_X, 0);
        an.setRepeatCount(1);
        an.setDuration(200);
        an.setRepeatMode(ValueAnimator.REVERSE);
        an.start();
    }

    public class Holder {
        TextView myth, date;
    }
}
