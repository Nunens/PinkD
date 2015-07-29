package com.nunens.pinkd.admin;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
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
import android.widget.Toast;

import com.google.android.gcm.GCMRegistrar;
import com.nunens.pinkd.util.Statics;


public class SplashActivity extends ActionBarActivity {
    String regID;
    SharedPreferences sp;
    SharedPreferences.Editor ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        begin();
    }

    void getGCMID() {
        sp = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        ed = sp.edit();
        regID = GCMRegistrar.getRegistrationId(getApplicationContext());
        if (regID.equals("")) {
            GCMRegistrar.register(getApplicationContext(), Statics.SENDER_ID);
            regID = GCMRegistrar.getRegistrationId(getApplicationContext());
            Log.d("Registered GCM", regID);
        } else {
            Log.d("REGISTRA", "This device is already registered...");
            Log.d("GCM ID", regID);
        }
        ed.putString("regID", regID);
        ed.commit();
    }

    void animate(View v, int i) {
        final ObjectAnimator an = ObjectAnimator.ofFloat(v, View.SCALE_X, 0);
        an.setRepeatCount(1);
        an.setDuration(i);
        an.setRepeatMode(ValueAnimator.REVERSE);
        an.start();
    }

    void begin() {
        Drawable d = getResources().getDrawable(R.drawable.header);
        try {
            getActionBar().setBackgroundDrawable(d);
        } catch (Exception e) {
        }
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ed = sp.edit();
        View view = findViewById(R.id.splash);
        animate(view, 500);
        getGCMID();
        Thread timer = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(3000);
                    }
                } catch (InterruptedException e) {
                    Toast.makeText(getApplicationContext(),
                            "Unable to start the timer", Toast.LENGTH_LONG)
                            .show();
                } finally {
                    finish();
                    startActivity(new Intent(getApplicationContext(),
                            LoginActivity.class));
                }
            }
        };
        timer.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
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
