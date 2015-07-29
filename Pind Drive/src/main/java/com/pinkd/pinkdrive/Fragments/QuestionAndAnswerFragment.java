package com.pinkd.pinkdrive.Fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nunens.pinkd.dto.QuestionAnswerDTO;
import com.nunens.pinkd.dto.RequestDTO;
import com.nunens.pinkd.dto.ResponseDTO;
import com.nunens.pinkd.util.Statics;
import com.nunens.pinkd.util.ToastUtil;
import com.nunens.pinkd.util.WebSocketUtil;
import com.pinkd.pinkdrive.LoginActivity;
import com.pinkd.pinkdrive.R;

/**
 * Created by admin on 2015/06/24.
 */
public class QuestionAndAnswerFragment extends Fragment implements QuestionAndAnswerPageFragment {
    View view;
    TextView username, adminname, question, answer;
    Button add;
    SharedPreferences sp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.question_and_answer_fragment, container, false);
        String uName = getArguments().getString("username");
        String aName = getArguments().getString("adminname");
        String ques = getArguments().getString("question");
        String ans = getArguments().getString("answer");
        username = (TextView) view.findViewById(R.id.fr_question_username);
        adminname = (TextView) view.findViewById(R.id.fr_question_adminname);
        question = (TextView) view.findViewById(R.id.fr_question_text);
        answer = (TextView) view.findViewById(R.id.fr_question_answer_text);
        username.setText(uName);
        adminname.setText(aName);
        question.setText(ques);
        answer.setText(ans);
        sp = PreferenceManager.getDefaultSharedPreferences(getActivity());

        add = (Button)view.findViewById(R.id.add_question);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userID = sp.getInt("userID", 0);
                if(userID == 0){
                    ToastUtil.toast(getActivity(), "Sorry, You need to login first!");
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }else {
                    showCalculatorDialog();
                }
            }
        });

        return view;
    }
    private void showCalculatorDialog() {
        //Log.d("LatLng", latLng.latitude + ", " + latLng.longitude);
        LayoutInflater factory = LayoutInflater.from(getActivity());
        View view = factory.inflate(R.layout.question_dialog, null);
        final AlertDialog findDialog = new AlertDialog.Builder(getActivity()).create();
        findDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        findDialog.setView(view);
        final EditText qq = (EditText) view.findViewById(R.id.question_text);
        Button addQuestion = (Button) view.findViewById(R.id.add_question);
        addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestDTO req = new RequestDTO();
                req.setRequestType(RequestDTO.addQuestionAndAnswer);
                QuestionAnswerDTO dto = new QuestionAnswerDTO();
                dto.setQuestion(qq.getText().toString());
                dto.setUserID(sp.getInt("userID", 0));
                dto.setAdminID(1);
                req.setQuestionAnswerDTO(dto);
                WebSocketUtil.sendRequest(getActivity(), req, Statics.socket, new WebSocketUtil.WebSocketListener() {
                    @Override
                    public void onMessage(ResponseDTO response) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.toast(getActivity(), "Question successfully added");
                                getActivity().finish();
                                findDialog.cancel();
                            }
                        });
                    }

                    @Override
                    public void onClose() {

                    }

                    @Override
                    public void onError(String message) {

                    }
                });
            }
        });
        findDialog.show();
    }
}
