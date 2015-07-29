package com.pinkd.pinkdrive;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.gson.Gson;
import com.nunens.pinkd.dto.MythDTO;
import com.nunens.pinkd.dto.RequestDTO;
import com.nunens.pinkd.dto.ResponseDTO;
import com.nunens.pinkd.util.Statics;
import com.nunens.pinkd.util.WebSocketUtil;
import com.pinkd.pinkdrive.Fragments.MythFragment;
import com.pinkd.pinkdrive.Fragments.MythPageFragment;
import com.pinkd.pinkdrive.adapter.MythListAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class MythActivity extends ActionBarActivity {
    ListView myths;
    List<MythDTO> list;
    MythListAdapter opva;
    private List<MythPageFragment> pageFragmentList;
    PagerAdapter pageAdapter;
    MythFragment mythFragment;
    ViewPager mPager;
    int currentPageIndex;
    Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myth);
        //myths = (ListView) findViewById(R.id.myths);
        mPager = (ViewPager) findViewById(R.id.myth_container);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ActionBar actionBar = getSupportActionBar();
        PinkUtil.setActionBar(actionBar, getApplication(), sp.getString("username", ""));
        RequestDTO req = new RequestDTO();
        req.setRequestType(RequestDTO.getMyth);
        req.setCancerID(1);
        WebSocketUtil.sendRequest(getApplication(), req, Statics.socket, new WebSocketUtil.WebSocketListener() {
            @Override
            public void onMessage(ResponseDTO dto) {
                list = dto.getMyths();
                if (list != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //opva = new MythListAdapter(getApplicationContext(), R.layout.myth_list_item, list);
                            //myths.setAdapter(opva);
                            Log.d("NAZO", gson.toJson(list));
                            buildPages(list);
                        }
                    });
                }
            }

            @Override
            public void onClose() {

            }

            @Override
            public void onError(String message) {

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

    private void buildPages(List<MythDTO> lst) {
        pageFragmentList = new ArrayList<>();
        for(MythDTO dto : lst) {
            mythFragment = new MythFragment();
            Bundle bundle = new Bundle();
            bundle.putString("text", dto.getContent());
            mythFragment.setArguments(bundle);

            pageFragmentList.add(mythFragment);
        }
        initializeAdapter();

    }

    private void initializeAdapter() {
        pageAdapter = new PagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pageAdapter);
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                currentPageIndex = arg0;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    static final DecimalFormat df = new DecimalFormat("###,###,###,###,##0.00");

    private class PagerAdapter extends FragmentStatePagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return (Fragment) pageFragmentList.get(i);
        }

        @Override
        public int getCount() {
            return pageFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = "";

            return title;
        }
    }
}
