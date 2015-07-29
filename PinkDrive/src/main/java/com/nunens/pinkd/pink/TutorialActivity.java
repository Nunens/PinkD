package com.nunens.pinkd.pink;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.nunens.pinkd.dto.RequestDTO;
import com.nunens.pinkd.dto.ResponseDTO;
import com.nunens.pinkd.dto.TutorialDTO;
import com.nunens.pinkd.pink.adapter.TutorialListAdapter;
import com.nunens.pinkd.util.VolleyUtil;

import java.util.List;


public class TutorialActivity extends ActionBarActivity {
    TutorialListAdapter opva;
    TextView count;
    ListView listView;
    List<TutorialDTO> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        count = (TextView) findViewById(R.id.tutorialCount);
        listView = (ListView) findViewById(R.id.tutorials);
        RequestDTO req = new RequestDTO();
        req.setCancerID(1);
        req.setRequestType(RequestDTO.getTutorial);
        VolleyUtil.sendVolleyRequest(getApplicationContext(), req, new VolleyUtil.VolleyListener() {
            @Override
            public void onResponse(ResponseDTO dto) {
                list = dto.getTutorials();
                if (list != null) {
                    opva = new TutorialListAdapter(getApplicationContext(), R.layout.tutorial_list_item, list);
                    listView.setAdapter(opva);
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
