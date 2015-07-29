package com.nunens.pinkd.admin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.nunens.pinkd.dto.QuestionAnswerDTO;
import com.nunens.pinkd.dto.RequestDTO;
import com.nunens.pinkd.dto.ResponseDTO;
import com.nunens.pinkd.util.VolleyUtil;

import java.util.ArrayList;
import java.util.List;


public class QuestionActivity extends ActionBarActivity {
    ResponseDTO resp;
    RequestDTO req;
    SharedPreferences sp;
    SharedPreferences.Editor ed;
    List<QuestionAnswerDTO> questionAnswerList;
    QuestionListAdapter opva;
    ListView questions;
    TextView count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Drawable d = getResources().getDrawable(R.drawable.title);
        try {
            getActionBar().setBackgroundDrawable(d);
            getActionBar().setIcon(R.drawable.whitelogo);
            getActionBar().setHomeButtonEnabled(true);
        } catch (Exception e) {
        }
        setFields();
    }

    void setFields() {
        count = (TextView) findViewById(R.id.questionCount);
        questions = (ListView) findViewById(R.id.questions);
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ed = sp.edit();
        req = new RequestDTO();
        req.setRequestType(RequestDTO.getPendingQuestions);
        VolleyUtil.sendVolleyRequest(getApplicationContext(), req, new VolleyUtil.VolleyListener() {
            @Override
            public void onResponse(ResponseDTO resp) {
                Gson gson = new Gson();
                Log.d("", gson.toJson(resp));
                if (resp.getQuestionAnswers() != null) {
                    questionAnswerList = new ArrayList<QuestionAnswerDTO>();
                    for (QuestionAnswerDTO dto : resp.getQuestionAnswers()) {
                        questionAnswerList.add(dto);
                    }
                    count.setText(questionAnswerList.size() + "");
                    setChatList(questionAnswerList);
                } else {
                    Toast.makeText(getApplicationContext(), resp.getMessage(), Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), Dashboard.class));
                    finish();
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    private void setChatList(List<QuestionAnswerDTO> log) {
        opva = new QuestionListAdapter(getApplicationContext(), R.layout.question_answer_list,
                log);

        questions.setAdapter(opva);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_question, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public interface QuestionAndAnswer {
        public void closeActivity();
    }
}
