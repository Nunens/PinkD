package com.nunens.pinkd.pink.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nunens.pinkd.dto.EventDTO;
import com.nunens.pinkd.pink.R;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by admin on 2015/03/03.
 */
public class EventListAdapter extends ArrayAdapter<EventDTO> {
    private final int layoutRes;
    private final LayoutInflater inflater;
    private List<EventDTO> list;
    private Context context;
    private String log = "EventListAdapter";
    SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd, HH:mm");

    public EventListAdapter(Context context, int resource, List<EventDTO> objects) {
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
            holder.eventDate = (TextView) convertView.findViewById(R.id.examDate);
            holder.eventDescription = (TextView) convertView.findViewById(R.id.eventText);
            holder.eventName = (TextView) convertView.findViewById(R.id.examStage);
            holder.eventVenue = (TextView) convertView.findViewById(R.id.eventVenue);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        EventDTO dto = list.get(position);
        holder.eventVenue.setText(dto.getVenue());
        holder.eventDescription.setText(dto.getDescription());
        holder.eventName.setText(dto.getName());
        holder.eventDate.setText(sdf.format(dto.getDate()));
        return convertView;
    }

    class Holder {
        TextView eventName, eventDate, eventVenue, eventDescription;
    }
}
