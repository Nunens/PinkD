package com.pinkd.pinkdrive;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nunens.pinkd.dto.RequestDTO;
import com.nunens.pinkd.dto.ResponseDTO;
import com.nunens.pinkd.dto.UserDTO;
import com.nunens.pinkd.util.Statics;
import com.nunens.pinkd.util.ToastUtil;
import com.nunens.pinkd.util.WebSocketUtil;


public class LoginActivity extends ActionBarActivity {
    EditText email, password;
    Button login; TextView signup;
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
        ActionBar actionBar = getSupportActionBar();
        PinkUtil.setActionBar(actionBar, getApplication(), sp.getString("username", ""));
        ed = sp.edit();
        email = (EditText)findViewById(R.id.loginEmail);
        password = (EditText) findViewById(R.id.loginPassword);
        signup = (TextView) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
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
                WebSocketUtil.sendRequest(getApplication(), req, Statics.socket, new WebSocketUtil.WebSocketListener() {
                    @Override
                    public void onMessage(ResponseDTO response) {
                        resp = response;
                        if(response.getStatusCode() == 0){
                            ed.putInt("userID", response.getUser().getUserID());
                            ed.putString("username", response.getUser().getName());
                            ed.commit();
                            //startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                            finish();
                        }else{
                            ToastUtil.toast(getApplication(), resp.getMessage());
                        }
                    }

                    @Override
                    public void onClose() {

                    }

                    @Override
                    public void onError(String message) {
                        ToastUtil.errorToast(getApplication(), message);
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
