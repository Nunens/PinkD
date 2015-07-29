package com.nunens.pinkd.pink;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.nunens.pinkd.dto.QuestionAnswerDTO;
import com.nunens.pinkd.dto.RequestDTO;
import com.nunens.pinkd.dto.ResponseDTO;
import com.nunens.pinkd.pink.adapter.QuestionListAdapter;
import com.nunens.pinkd.util.VolleyUtil;

import java.util.List;


public class QuestionActivity extends ActionBarActivity {
    QuestionListAdapter opva;
    List<QuestionAnswerDTO> list;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        listView = (ListView)findViewById(R.id.questions);
        RequestDTO req = new RequestDTO();
        req.setRequestType(RequestDTO.getQuestionAndAnswer);
        VolleyUtil.sendVolleyRequest(getApplicationContext(), req, new VolleyUtil.VolleyListener() {
            @Override
            public void onResponse(ResponseDTO dto) {
                list = dto.getQuestionAnswers();
                if(dto.getStatus() == 0) {
                    opva = new QuestionListAdapter(getApplicationContext(), R.layout.question_answer_list, list);
                    listView.setAdapter(opva);
                }else{
                    Toast.makeText(getApplicationContext(), "No Q and A found", Toast.LENGTH_LONG).show();
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
        getMenuInflater().inflate(R.menu.menu_question, menu);
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
