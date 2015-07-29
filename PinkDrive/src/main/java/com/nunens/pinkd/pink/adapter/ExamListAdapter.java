package com.nunens.pinkd.pink.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nunens.pinkd.dto.ExamDTO;
import com.nunens.pinkd.pink.R;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by admin on 2015/03/03.
 */
public class ExamListAdapter extends ArrayAdapter<ExamDTO> {
    List<ExamDTO> list;
    private final int layoutRes;
    private final LayoutInflater inflater;
    private Context context;
    private String log = "EventListAdapter";
    SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd, HH:mm");

    public ExamListAdapter(Context context, int resource, List<ExamDTO> objects) {
        super(context, resource, objects);
        list = objects;
        layoutRes = resource;
        this.context = context;
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
            holder.examContent = (TextView) convertView.findViewById(R.id.examContent);
            holder.examNote = (TextView) convertView.findViewById(R.id.examNote);
            holder.examStage = (TextView) convertView.findViewById(R.id.examStage);
            holder.examNoteContent = (TextView) convertView.findViewById(R.id.examNoteContent);
            holder.examDate = (TextView) convertView.findViewById(R.id.examDate);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        ExamDTO dto = list.get(position);
        holder.examNoteContent.setText(dto.getNoteContent());
        holder.examNote.setText(dto.getNote());
        holder.examContent.setText(dto.getContent());
        holder.examStage.setText(dto.getStage());
        holder.examDate.setText(dto.getDate());
        return convertView;
    }

    class Holder {
        TextView examStage, examContent, examNote, examNoteContent, examDate;
    }
}
