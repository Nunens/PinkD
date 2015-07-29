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
import com.google.gson.Gson;
import com.nunens.pinkd.dto.AdminDTO;
import com.nunens.pinkd.dto.RequestDTO;
import com.nunens.pinkd.dto.ResponseDTO;
import com.nunens.pinkd.util.VolleyUtil;


public class LoginActivity extends ActionBarActivity {
    EditText email, password;
    Button login;
    RequestDTO req;
    SharedPreferences sp;
    SharedPreferences.Editor ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Drawable d = getResources().getDrawable(R.drawable.title);
        try {
            getActionBar().setBackgroundDrawable(d);
            getActionBar().setIcon(R.drawable.whitelogo);
            getActionBar().setHomeButtonEnabled(true);
        } catch (Exception e) {
        }
        setFields();
        checkVirginity();
    }

    private void checkVirginity() {
        int adminID = sp.getInt("adminID", 0);
        if (adminID > 0) {
            startActivity(new Intent(getApplicationContext(), Dashboard.class));
            finish();
        }
    }

    void setFields() {
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ed = sp.edit();
        email = (EditText) findViewById(R.id.loginEmail);
        password = (EditText) findViewById(R.id.loginPassword);
        login = (Button) findViewById(R.id.loginBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                req = new RequestDTO();
                req.setRequestType(RequestDTO.adminLogin);
                AdminDTO dto = new AdminDTO();
                dto.setEmail(email.getText().toString());
                dto.setPassword(password.getText().toString());
                req.setAdminDTO(dto);
                VolleyUtil.sendVolleyRequest(getApplicationContext(), req, new VolleyUtil.VolleyListener() {
                    @Override
                    public void onResponse(ResponseDTO resp) {

                        Gson gson = new Gson();
                        Log.d("", gson.toJson(resp));
                        if (resp.getStatus() == 0) {
                            Log.d("RESP", "" + resp.getAdmin().getEmail().toString());
                            ed.putString("gcmID", resp.getAdmin().getGcmID());
                            ed.putString("adminName", resp.getAdmin().getName() + " " + resp.getAdmin().getSurname());
                            ed.putInt("adminID", resp.getAdmin().getAdminID());
                            ed.putString("email", resp.getAdmin().getEmail());
                            ed.putString("phone", resp.getAdmin().getPhone());
                            ed.commit();
                            startActivity(new Intent(getApplicationContext(), Dashboard.class));
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), resp.getMessage(), Toast.LENGTH_LONG).show();
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
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
