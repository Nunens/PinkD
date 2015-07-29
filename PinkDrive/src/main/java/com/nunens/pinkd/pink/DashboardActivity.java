package com.nunens.pinkd.pink;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.nunens.pinkd.dto.RequestDTO;
import com.nunens.pinkd.dto.ResponseDTO;
import com.nunens.pinkd.util.VolleyUtil;

import java.util.Timer;
import java.util.TimerTask;


public class DashboardActivity extends ActionBarActivity {
    View event, tutorial, questionAndAnswer, calender, myth, howTo, learnMore, userExperience, gogo;
    Timer timer;
    TextView gogoMyth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setFields();
    }

    void setFields() {
        gogo = findViewById(R.id.gogo);
        timer = new Timer();
        gogoMyth = (TextView) findViewById(R.id.mythGogo);
        Drawable d = getResources().getDrawable(R.drawable.title);
        try {
            getActionBar().setBackgroundDrawable(d);
            getActionBar().setIcon(R.drawable.whitelogo);
            getActionBar().setHomeButtonEnabled(true);
        } catch (Exception e) {
        }
        event = findViewById(R.id.eventMenu);
        tutorial = findViewById(R.id.tutsMenu);
        questionAndAnswer = findViewById(R.id.qaMenu);
        myth = findViewById(R.id.mythMenu);
        howTo = findViewById(R.id.howtoMenu);
        calender = findViewById(R.id.calenderMenu);

        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CalendarActivity.class));
            }
        });

        howTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ExamActivity.class));
            }
        });

        questionAndAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), QuestionActivity.class));
            }
        });

        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), GameActivity.class));
            }
        });

        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TutorialActivity.class));
            }
        });
        myth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MythActivity.class));
            }
        });
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                RequestDTO req = new RequestDTO();
                req.setRequestType(RequestDTO.getOneMyth);
                VolleyUtil.sendVolleyRequest(getApplication(), req, new VolleyUtil.VolleyListener() {
                    @Override
                    public void onResponse(ResponseDTO dto) {
                        gogoMyth.setText(dto.getMyth().getContent());
                        gogo.setVisibility(View.VISIBLE);
                        gogo.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                gogo.setVisibility(View.GONE);
                            }
                        }, 5000);
                    }

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
            }
        }, 25000, 25000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
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
