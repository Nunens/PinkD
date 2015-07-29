package com.pinkd.pinkdrive.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nunens.pinkd.dto.TutorialDTO;
import com.pinkd.pinkdrive.R;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by admin on 2015/03/03.
 */
public class TutorialListAdapter extends ArrayAdapter<TutorialDTO> {
    private final int layoutRes;
    private final LayoutInflater inflater;
    private List<TutorialDTO> list;
    private Context context;
    private String log = "EventListAdapter";
    SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd, HH:mm");

    public TutorialListAdapter(Context context, int resource, List<TutorialDTO> objects) {
        super(context, resource, objects);
        list = objects;
        this.context = context;
        layoutRes = resource;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (list == null) {
            return;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = inflater.inflate(layoutRes, null);
            holder.heading = (TextView) convertView.findViewById(R.id.tutorialHeading);
            holder.content = (TextView) convertView.findViewById(R.id.tutorialContent);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        TutorialDTO dto = list.get(position);
        holder.heading.setText(dto.getHeading());
        holder.content.setText(dto.getContent());
        return convertView;
    }

    class Holder {
        TextView heading, content;
    }
}
