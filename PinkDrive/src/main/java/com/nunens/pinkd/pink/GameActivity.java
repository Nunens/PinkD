package com.nunens.pinkd.pink;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.nunens.pinkd.dto.GameDTO;
import com.nunens.pinkd.dto.RequestDTO;
import com.nunens.pinkd.dto.ResponseDTO;
import com.nunens.pinkd.util.VolleyUtil;


public class GameActivity extends ActionBarActivity {
    TextView question, score;
    RadioButton answerA, answerB, answerC, answerD;
    RequestDTO req;
    ResponseDTO resp;
    String correct;
    Gson gson = new Gson();
    int sco = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setFields();
    }

    void setFields() {
        question = (TextView) findViewById(R.id.question);
        answerA = (RadioButton) findViewById(R.id.answer1);
        answerB = (RadioButton) findViewById(R.id.answer2);
        answerC = (RadioButton) findViewById(R.id.answer3);
        answerD = (RadioButton) findViewById(R.id.answer4);
        score = (TextView) findViewById(R.id.score);
        getGameQuestion();
    }

    void getGameQuestion() {
        req = new RequestDTO();
        req.setRequestType(RequestDTO.getGameQuestion);
        VolleyUtil.sendVolleyRequest(getApplicationContext(), req, new VolleyUtil.VolleyListener() {
            @Override
            public void onResponse(ResponseDTO dto) {
                Log.d("GameActivity", gson.toJson(dto));
                resp = dto;
                if (resp.getStatusCode() == 0) {
                    GameDTO g = resp.getGame();
                    correct = g.getCorrect();
                    question.setText(g.getQuestion());
                    answerA.setText(g.getAnswerA());
                    answerA.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (answerA.getText().equals(correct)) {
                                sco = Integer.parseInt(score.getText().toString()) + 10;
                                score.setText(sco + "");
                            } else {
                                sco = Integer.parseInt(score.getText().toString()) - 5;
                                score.setText(sco + "");
                            }
                            getGameQuestion();
                        }
                    });
                    answerB.setText(g.getAnswerB());
                    answerB.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (answerB.getText().equals(correct)) {
                                sco = Integer.parseInt(score.getText().toString()) + 10;
                                score.setText(sco + "");
                            } else {
                                sco = Integer.parseInt(score.getText().toString()) - 5;
                                score.setText(sco + "");
                            }
                            getGameQuestion();
                        }
                    });
                    answerC.setText(g.getAnswerC());
                    answerC.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (answerC.getText().equals(correct)) {
                                sco = Integer.parseInt(score.getText().toString()) + 10;
                                score.setText(sco + "");
                            } else {
                                sco = Integer.parseInt(score.getText().toString()) - 5;
                                score.setText(sco + "");
                            }
                            getGameQuestion();
                        }
                    });
                    answerD.setText(g.getAnswerD());
                    answerD.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (answerD.getText().equals(correct)) {
                                sco = Integer.parseInt(score.getText().toString()) + 10;
                                score.setText(sco + "");
                            } else {
                                sco = Integer.parseInt(score.getText().toString()) - 5;
                                score.setText(sco + "");
                            }
                            getGameQuestion();
                        }
                    });
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
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
}
