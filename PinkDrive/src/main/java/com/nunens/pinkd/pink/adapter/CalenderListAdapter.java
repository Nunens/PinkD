package com.nunens.pinkd.pink.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nunens.pinkd.dto.CalenderDTO;
import com.nunens.pinkd.pink.R;

import java.util.List;

/**
 * Created by admin on 2015/03/04.
 */
public class CalenderListAdapter extends ArrayAdapter<CalenderDTO> {
    List<CalenderDTO> list;
    LayoutInflater inflater;
    int layoutRes;
    Context context;

    public CalenderListAdapter(Context context, int resource, List<CalenderDTO> objects) {
        super(context, resource, objects);
        list = objects;
        this.context = context;
        layoutRes = resource;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = inflater.inflate(layoutRes, null);
            holder.calenderDate = (TextView) convertView.findViewById(R.id.calenderDate);
            holder.calenderMenDate = (TextView) convertView.findViewById(R.id.calenderMenturationDate);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        CalenderDTO dto = list.get(position);
        holder.calenderDate.setText(dto.getDate());
        holder.calenderMenDate.setText(dto.getmDate());
        return convertView;
    }

    class Holder {
        TextView calenderDate, calenderMenDate;
    }
}
