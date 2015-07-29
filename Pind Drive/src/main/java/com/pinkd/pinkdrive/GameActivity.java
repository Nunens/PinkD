package com.pinkd.pinkdrive;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nunens.pinkd.dto.GameDTO;
import com.nunens.pinkd.dto.RequestDTO;
import com.nunens.pinkd.dto.ResponseDTO;
import com.nunens.pinkd.util.FileUtil;
import com.nunens.pinkd.util.Statics;
import com.nunens.pinkd.util.ToastUtil;
import com.nunens.pinkd.util.WebSocketUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameActivity extends ActionBarActivity {
    TextView question, score, questionB, realAnswer;
    RadioButton answerA, answerB, answerC, answerD;
    RequestDTO req;
    ResponseDTO resp;
    String correct;
    Gson gson = new Gson();
    View waar;
    int sco = 0;
    RadioGroup radios;
    List<GameDTO> gameList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setFields();
    }

    void setFields() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ActionBar actionBar = getSupportActionBar();
        PinkUtil.setActionBar(actionBar, getApplication(), sp.getString("username", ""));
        radios = (RadioGroup) findViewById(R.id.radios);
        question = (TextView) findViewById(R.id.question);
        answerA = (RadioButton) findViewById(R.id.answer1);
        answerB = (RadioButton) findViewById(R.id.answer2);
        answerC = (RadioButton) findViewById(R.id.answer3);
        answerD = (RadioButton) findViewById(R.id.answer4);
        score = (TextView) findViewById(R.id.score);
        questionB = (TextView) findViewById(R.id.questionB);
        realAnswer = (TextView) findViewById(R.id.realAnswer);
        waar = findViewById(R.id.correct);
        readSavedInstance();
        if (gameList.isEmpty()) {
            getGameQuestion();
        } else {
            getRandomGame();
        }

    }

    void getGameQuestion() {
        req = new RequestDTO();
        req.setRequestType(RequestDTO.getGameQuestion);
        WebSocketUtil.sendRequest(getApplication(), req, Statics.socket, new WebSocketUtil.WebSocketListener() {
            @Override
            public void onMessage(ResponseDTO dto) {
                Log.d("GameActivity", gson.toJson(dto));
                resp = dto;
                if (resp.getStatusCode() == 0) {
                    for (GameDTO g : resp.getGames()) {
                        gameList.add(g);
                    }
                    saveGame();
                    getRandomGame();
                }
            }

            @Override
            public void onClose() {

            }

            @Override
            public void onError(String message) {

            }
        });
    }

    void saveGame() {
        List<String> list = new ArrayList<>();
        JSONObject obj = new JSONObject();
        try {
            obj.put("myths", gson.toJson(gameList));
            Log.d("JSON", obj.toString() + gson.toJson(obj));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        FileUtil.saveFile(gson.toJson(obj), "games", getApplication());
        Log.d("", "File Saved: " + gson.toJson(obj));
    }

    private void readSavedInstance() {
        String file = null;
        try {
            file = FileUtil.readFile("myths", getApplication());
        } catch (Exception e) {
            Log.d(DashboardActivity.class.getSimpleName(), "Can't read savedInstance from file");
        }
        Log.i("Line 131: ", file);
        if (!file.equals("error")) {
            try {
                JSONObject rb = new JSONObject(file);
                JSONObject r = new JSONObject(rb.get("nameValuePairs").toString());
                List<String> list = new ArrayList<String>();
                JSONArray games = new JSONArray(r.get("games").toString());
                if (games.length() > 0) {
                    for (int i = 0; i < games.length(); i++) {
                        list.add(games.getString(i));
                        GameDTO dto = gson.fromJson(games.getString(i), GameDTO.class);
                        gameList.add(dto);
                        getRandomGame();
                    }
                } else {
                    Log.d("", "A gona selo mo di myth clever");
                }
            } catch (Exception e) {
                Log.d("", "error reading savedInstance");
                e.printStackTrace();
            }
        } else {
        }
    }

    void getRandomGame() {

        radios.clearCheck();
        Random r = new Random();
        int Low = 1;
        int High = gameList.size();
        final int i = r.nextInt(High - Low) + Low;
        final GameDTO g = gameList.get(i);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                correct = g.getCorrect();
                question.setText(g.getQuestion());
                answerA.setText(g.getAnswerA());
                answerA.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (answerA.getText().equals(correct)) {
                            sco = Integer.parseInt(score.getText().toString()) + 10;
                            score.setText(sco + "");
                            ToastUtil.toast(getApplication(), "Excellent + 10 points");
                        } else {
                            ToastUtil.errorToast(getApplication(), "Sorry, the correct answer is: " + correct);
                            sco = Integer.parseInt(score.getText().toString()) - 5;
                            score.setText(sco + "");
                        }
                        getRandomGame();
                    }
                });
                answerB.setText(g.getAnswerB());
                answerB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (answerB.getText().equals(correct)) {
                            sco = Integer.parseInt(score.getText().toString()) + 10;
                            score.setText(sco + "");
                            ToastUtil.toast(getApplication(), "Excellent + 10 points");
                        } else {
                            ToastUtil.errorToast(getApplication(), "Sorry, the correct answer is: " + correct);
                            sco = Integer.parseInt(score.getText().toString()) - 5;
                            score.setText(sco + "");
                        }
                        getRandomGame();
                    }
                });
                answerC.setText(g.getAnswerC());
                answerC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (answerC.getText().equals(correct)) {
                            sco = Integer.parseInt(score.getText().toString()) + 10;
                            score.setText(sco + "");
                            ToastUtil.toast(getApplication(), "Excellent + 10 points");
                        } else {
                            ToastUtil.errorToast(getApplication(), "Sorry, the correct answer is: " + correct);
                            sco = Integer.parseInt(score.getText().toString()) - 5;
                            score.setText(sco + "");
                        }
                        getRandomGame();
                    }
                });
                answerD.setText(g.getAnswerD());
                answerD.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (answerD.getText().equals(correct)) {
                            sco = Integer.parseInt(score.getText().toString()) + 10;
                            score.setText(sco + "");
                            ToastUtil.toast(getApplication(), "Excellent + 10 points");
                        } else {
                            ToastUtil.errorToast(getApplication(), "Sorry, the correct answer is: " + correct);
                            sco = Integer.parseInt(score.getText().toString()) - 5;
                            score.setText(sco + "");
                        }
                        getRandomGame();
                    }
                });
            }
        });
    }

    void showDialog() {
        waar.postDelayed(new Runnable() {
            @Override
            public void run() {
                waar.setVisibility(View.VISIBLE);
                questionB.setText(question.getText().toString());
                realAnswer.setText(correct);
            }
        }, 2000);
        waar.setVisibility(View.GONE);
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
