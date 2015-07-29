package com.nunens.pinkd.admin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.VolleyError;
import com.nunens.pinkd.dto.RequestDTO;
import com.nunens.pinkd.dto.ResponseDTO;
import com.nunens.pinkd.dto.TutorialDTO;
import com.nunens.pinkd.util.VolleyUtil;


public class TutorialActivity extends ActionBarActivity {
    EditText topic, content;
    Button add;
    RequestDTO req;
    SharedPreferences sp;
    SharedPreferences.Editor ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        setFields();
    }

    void setFields() {
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ed = sp.edit();
        topic = (EditText) findViewById(R.id.tutorialTopic);
        content = (EditText) findViewById(R.id.tutorialContext);
        add = (Button) findViewById(R.id.tutorialBtn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                req = new RequestDTO();
                req.setRequestType(RequestDTO.addTutorial);
                TutorialDTO dto = new TutorialDTO();
                dto.setAdminID(sp.getInt("adminID", 0));
                dto.setHeading(topic.getText().toString());
                dto.setContent(content.getText().toString());
                req.setTutorialDTO(dto);
                VolleyUtil.sendVolleyRequest(getApplicationContext(), req, new VolleyUtil.VolleyListener() {
                    @Override
                    public void onResponse(ResponseDTO resp) {
                        if (resp.getAdmin() != null) {
                            Log.d("RESP", "" + resp.getTutorial().toString());
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
        getMenuInflater().inflate(R.menu.menu_tutorial, menu);
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
