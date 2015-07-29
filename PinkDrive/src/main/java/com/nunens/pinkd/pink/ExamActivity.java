package com.nunens.pinkd.pink;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.nunens.pinkd.dto.ExamDTO;
import com.nunens.pinkd.dto.RequestDTO;
import com.nunens.pinkd.dto.ResponseDTO;
import com.nunens.pinkd.pink.adapter.ExamListAdapter;
import com.nunens.pinkd.util.VolleyUtil;

import java.util.List;


public class ExamActivity extends ActionBarActivity {
    ListView exams;
    List<ExamDTO> list;
    ExamListAdapter opva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        exams = (ListView) findViewById(R.id.exams);
        RequestDTO req = new RequestDTO();
        req.setRequestType(RequestDTO.getExam);
        req.setCancerID(1);
        VolleyUtil.sendVolleyRequest(getApplicationContext(), req, new VolleyUtil.VolleyListener() {
            @Override
            public void onResponse(ResponseDTO dto) {
                if (dto.getStatus() == 0) {
                    list = dto.getExams();
                    opva = new ExamListAdapter(getApplicationContext(), R.layout.exam_list_item, list);
                    exams.setAdapter(opva);
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
