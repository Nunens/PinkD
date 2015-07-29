package com.nunens.pinkd.pink;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.nunens.pinkd.dto.CalenderDTO;
import com.nunens.pinkd.dto.RequestDTO;
import com.nunens.pinkd.dto.ResponseDTO;
import com.nunens.pinkd.dto.UserDTO;
import com.nunens.pinkd.pink.adapter.CalenderListAdapter;
import com.nunens.pinkd.util.VolleyUtil;

import java.util.Date;
import java.util.List;


public class CalendarActivity extends ActionBarActivity {
    ListView listView;
    Button add;
    List<CalenderDTO> list;
    CalenderListAdapter opva;
    CalendarView calender;
    Date selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        listView = (ListView) findViewById(R.id.previoustCalenders);
        add = (Button) findViewById(R.id.addCalender);
        calender = (CalendarView) findViewById(R.id.calenderPicker);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calender.setVisibility(View.VISIBLE);
                calender.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedDate = new Date(calender.getDate());
                        calender.setVisibility(View.GONE);
                        RequestDTO req = new RequestDTO();
                        req.setRequestType(RequestDTO.addCalender);
                        UserDTO dto = new UserDTO();
                        dto.setUserID(1);
                        req.setUserID(1);
                        req.setUserDTO(dto);
                        VolleyUtil.sendVolleyRequest(getApplicationContext(), req, new VolleyUtil.VolleyListener() {
                            @Override
                            public void onResponse(ResponseDTO dto) {
                                if (dto.getStatus() == 0) {
                                    Gson gson = new Gson();
                                    Log.d("RESP ##", gson.toJson(dto));
                                } else {
                                    
                                }
                            }

                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
                    }
                });
            }
        });
        RequestDTO req = new RequestDTO();
        req.setRequestType(RequestDTO.getCalender);
        req.setUserID(1);
        VolleyUtil.sendVolleyRequest(getApplicationContext(), req, new VolleyUtil.VolleyListener() {
            @Override
            public void onResponse(ResponseDTO dto) {
                if (dto.getStatus() == 0) {
                    list = dto.getCalenders();
                    opva = new CalenderListAdapter(getApplicationContext(), R.layout.calender_list_item, list);
                    listView.setAdapter(opva);
                } else {

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
        getMenuInflater().inflate(R.menu.menu_calendar, menu);
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
