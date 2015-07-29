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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.nunens.pinkd.dto.EventDTO;
import com.nunens.pinkd.dto.RequestDTO;
import com.nunens.pinkd.dto.ResponseDTO;
import com.nunens.pinkd.util.VolleyUtil;

import java.util.Date;


public class ExamActivity extends ActionBarActivity {
    EditText topic, content, date, venue;
    Button add;
    RequestDTO req;
    SharedPreferences sp;
    SharedPreferences.Editor ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        setFields();
    }

    void setFields() {
        Drawable d = getResources().getDrawable(R.drawable.title);
        try {
            getActionBar().setBackgroundDrawable(d);
            getActionBar().setIcon(R.drawable.whitelogo);
            getActionBar().setHomeButtonEnabled(true);
        } catch (Exception e) {
        }
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ed = sp.edit();
        topic = (EditText) findViewById(R.id.eventName);
        content = (EditText) findViewById(R.id.eventDescription);
        date = (EditText) findViewById(R.id.eventDate);
        venue = (EditText) findViewById(R.id.eventVenue);

        add = (Button) findViewById(R.id.eventBtn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                req = new RequestDTO();
                req.setRequestType(RequestDTO.addEvent);
                EventDTO dto = new EventDTO();
                dto.setAdminID(sp.getInt("adminID", 0));
                dto.setName(topic.getText().toString());
                dto.setEventDate(new Date().getTime());
                dto.setVenue(venue.getText().toString());
                dto.setDescription(content.getText().toString());
                req.setEventDTO(dto);
                VolleyUtil.sendVolleyRequest(getApplicationContext(), req, new VolleyUtil.VolleyListener() {
                    @Override
                    public void onResponse(ResponseDTO resp) {
                        if (resp.getAdmin() != null) {
                            Log.d("RESP", "" + resp.getEvent().toString());
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
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exam, menu);
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
