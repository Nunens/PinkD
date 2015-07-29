package com.nunens.pinkd.admin;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.nunens.pinkd.dto.QuestionAnswerDTO;
import com.nunens.pinkd.dto.RequestDTO;
import com.nunens.pinkd.dto.ResponseDTO;
import com.nunens.pinkd.util.VolleyUtil;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Sipho on 2014/11/12.
 */
public class QuestionListAdapter extends ArrayAdapter<QuestionAnswerDTO> implements QuestionActivity.QuestionAndAnswer {
    private final int layoutRes;
    private final LayoutInflater inflater;
    private List<QuestionAnswerDTO> list;
    private Context context;
    private String log = "QuestionListAdapter", LOG = "QuestionListAdapter";
    TextView questionName, questionDate, questionText;
    EditText answerText;
    View view = null;
    Button answer;
    RequestDTO req;
    ResponseDTO resp;
    SharedPreferences sp;
    //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss");
    SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd, HH:mm");

    public QuestionListAdapter(Context context, int resource, List<QuestionAnswerDTO> logs) {
        super(context, resource, logs);
        sp = PreferenceManager.getDefaultSharedPreferences(context);
        this.layoutRes = resource;
        this.list = logs;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (list == null) {
            Log.i(log, "Call Log List is empty");
            return;
        }
        Log.i(log, "Call Log List contains records");
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            view = inflater.inflate(layoutRes, parent, false);
        } else {
            view = convertView;
        }
        questionName = (TextView) view.findViewById(R.id.questionerName);
        questionDate = (TextView) view.findViewById(R.id.questionDate);
        questionText = (TextView) view.findViewById(R.id.questionText);
        try {
            animate(answer, 400);
            animate(questionName, 200);
            animate(questionDate, 200);
            animate(answerText, 300);
            animate(questionText, 400);
        } catch (Exception e) {
        }
        questionName.setText(list.get(position).getUsername());
        //questionDate.setText(list.get(position).getDate());
        questionText.setText(list.get(position).getQuestion());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(v.getRootView().getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.answer_dialog);
                dialog.setCanceledOnTouchOutside(true);
                Button post, cancel;
                TextView question = (TextView) dialog.findViewById(R.id.dialogQuestion);
                question.setText(list.get(position).getQuestion());
                final EditText answer = (EditText) dialog.findViewById(R.id.dialogAnswer);
                post = (Button) dialog.findViewById(R.id.dialogAnswerBtn);
                post.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        req = new RequestDTO();
                        if (!answer.getText().toString().equals(null) || !answer.getText().toString().equals("")) {
                            req.setRequestType(RequestDTO.updateQuestionAndAnswer);
                            QuestionAnswerDTO dto = new QuestionAnswerDTO();
                            dto.setAdminID(sp.getInt("adminID", 0));
                            dto.setAnswer(answer.getText().toString());
                            dto.setQuestion(questionText.getText().toString());
                            dto.setQuestionID(list.get(position).getQuestionID());
                            dto.setStatus("Answered");
                            dto.setUserID(list.get(position).getUserID());
                            req.setQuestionAnswerDTO(dto);
                            VolleyUtil.sendVolleyRequest(context, req, new VolleyUtil.VolleyListener() {
                                @Override
                                public void onResponse(ResponseDTO dto) {
                                    Log.d("RESP", "" + dto.getMessage());
                                }

                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            });
                        } else {
                            Toast.makeText(context, "Please supply answer", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                cancel = (Button) dialog.findViewById(R.id.dialogCancelBtn);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        context.startActivity(new Intent(context, Dashboard.class));
                    }
                });
                dialog.show();
            }
        });
        return view;
    }

    void animate(View v, int i) {
        final ObjectAnimator an = ObjectAnimator.ofFloat(v, View.SCALE_X, 0);
        an.setRepeatCount(1);
        an.setDuration(i);
        an.setRepeatMode(ValueAnimator.REVERSE);
        an.start();
    }

    @Override
    public void closeActivity() {
        getContext().startActivity(new Intent(context, Dashboard.class));
    }

    class Holder {
        ImageView image;
        TextView phone, type, time;
        View ditsebe;
    }
}
