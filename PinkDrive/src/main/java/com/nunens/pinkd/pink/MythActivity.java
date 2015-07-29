package com.nunens.pinkd.pink;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.nunens.pinkd.dto.MythDTO;
import com.nunens.pinkd.dto.RequestDTO;
import com.nunens.pinkd.dto.ResponseDTO;
import com.nunens.pinkd.pink.adapter.MythListAdapter;
import com.nunens.pinkd.util.VolleyUtil;

import java.util.List;


public class MythActivity extends ActionBarActivity {
    ListView myths;
    List<MythDTO> list;
    MythListAdapter opva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myth);
        myths = (ListView) findViewById(R.id.myths);
        RequestDTO req = new RequestDTO();
        req.setRequestType(RequestDTO.getMyth);
        req.setCancerID(1);
        VolleyUtil.sendVolleyRequest(getApplicationContext(), req, new VolleyUtil.VolleyListener() {
            @Override
            public void onResponse(ResponseDTO dto) {
                list = dto.getMyths();
                if (list != null) {
                    opva = new MythListAdapter(getApplicationContext(), R.layout.myth_list_item, list);
                    myths.setAdapter(opva);
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
