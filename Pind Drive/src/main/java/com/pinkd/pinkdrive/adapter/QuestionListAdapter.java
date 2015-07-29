package com.pinkd.pinkdrive.adapter;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nunens.pinkd.dto.QuestionAnswerDTO;
import com.pinkd.pinkdrive.R;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Sipho on 2014/11/12.
 */
public class QuestionListAdapter extends ArrayAdapter<QuestionAnswerDTO> {
    private final int layoutRes;
    private final LayoutInflater inflater;
    private List<QuestionAnswerDTO> list;
    private Context context;
    private String log = "QuestionListAdapter", LOG = "QuestionListAdapter";

    //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss");
    SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd, HH:mm");

    public QuestionListAdapter(Context context, int resource, List<QuestionAnswerDTO> logs) {
        super(context, resource, logs);
        this.layoutRes = resource;
        this.list = logs;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (list == null) {
            Log.i(log, "QA List is empty");
            return;
        }
        Log.i(log, "QA List contains records");
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = inflater.inflate(layoutRes, null);
            holder.name = (TextView) convertView.findViewById(R.id.questionerName);
            holder.date = (TextView) convertView.findViewById(R.id.questionDate);
            holder.question = (TextView) convertView.findViewById(R.id.questionText);
            holder.answer = (TextView) convertView.findViewById(R.id.questionAnswers);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        QuestionAnswerDTO dto = list.get(position);
        holder.answer.setText(dto.getAnswer());
        holder.question.setText(dto.getQuestion());
        holder.date.setText(sdf.format(dto.getDate()));
        holder.name.setText(dto.getUsername());
        animate(convertView, 200);
        return convertView;
    }

    void animate(View v, int i) {
        final ObjectAnimator an = ObjectAnimator.ofFloat(v, View.SCALE_X, 0);
        an.setRepeatCount(1);
        an.setDuration(i);
        an.setRepeatMode(ValueAnimator.REVERSE);
        an.start();
    }

    class Holder {
        TextView name, date, question, answer;
    }
}
