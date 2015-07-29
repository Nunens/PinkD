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
import com.nunens.pinkd.dto.MythDTO;
import com.nunens.pinkd.dto.RequestDTO;
import com.nunens.pinkd.dto.ResponseDTO;
import com.nunens.pinkd.util.VolleyUtil;


public class MythActivity extends ActionBarActivity {
    EditText myth;
    Button add;
    RequestDTO req;
    SharedPreferences sp;
    SharedPreferences.Editor ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myth);
        setFields();
    }

    void setFields() {

        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ed = sp.edit();
        myth = (EditText) findViewById(R.id.mythText);
        add = (Button) findViewById(R.id.mythBtn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                req = new RequestDTO();
                req.setRequestType(RequestDTO.addMyth);
                MythDTO dto = new MythDTO();
                dto.setAdminID(sp.getInt("adminID", 0));
                dto.setContent(myth.getText().toString());
                req.setMythDTO(dto);
                VolleyUtil.sendVolleyRequest(getApplicationContext(), req, new VolleyUtil.VolleyListener() {
                    @Override
                    public void onResponse(ResponseDTO resp) {
                        if (resp.getAdmin() != null) {
                            Log.d("RESP", "" + resp.getMyth().toString());
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
        getMenuInflater().inflate(R.menu.menu_myth, menu);
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
