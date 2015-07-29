package com.nunens.pinkd.pink;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.VolleyError;
import com.nunens.pinkd.dto.RequestDTO;
import com.nunens.pinkd.dto.ResponseDTO;
import com.nunens.pinkd.dto.UserDTO;
import com.nunens.pinkd.util.VolleyUtil;


public class LoginActivity extends ActionBarActivity {
    EditText email, password;
    Button login;
    SharedPreferences sp;
    SharedPreferences.Editor ed;
    RequestDTO req;
    ResponseDTO resp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setFields();
    }

    void setFields(){
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ed = sp.edit();
        email = (EditText)findViewById(R.id.loginEmail);
        password = (EditText) findViewById(R.id.loginPassword);
        login = (Button) findViewById(R.id.loginBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                req = new RequestDTO();
                UserDTO user = new UserDTO();
                user.setEmail(email.getText().toString());
                user.setPassword(password.getText().toString());
                req.setRequestType(RequestDTO.login);
                req.setUserDTO(user);
                VolleyUtil.sendVolleyRequest(getApplicationContext(), req, new VolleyUtil.VolleyListener() {
                    @Override
                    public void onResponse(ResponseDTO dto) {
                        if(dto.getStatusCode() == 0){
                            ed.putInt("userID", dto.getUser().getUserID());
                            ed.putString("username", dto.getUser().getName()+" "+dto.getUser().getSurname());
                            ed.commit();
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
