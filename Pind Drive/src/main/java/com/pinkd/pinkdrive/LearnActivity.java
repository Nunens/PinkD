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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nunens.pinkd.dto.RequestDTO;
import com.nunens.pinkd.dto.ResponseDTO;
import com.nunens.pinkd.dto.TutorialDTO;
import com.nunens.pinkd.util.Statics;
import com.nunens.pinkd.util.WebSocketUtil;
import com.pinkd.pinkdrive.Fragments.TutorialFragment;
import com.pinkd.pinkdrive.Fragments.TutorialPageFragment;
import com.pinkd.pinkdrive.adapter.TutorialListAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class LearnActivity extends ActionBarActivity {
    TutorialListAdapter opva;
    TextView count;
    ListView listView;
    Gson gson = new Gson();

    PagerAdapter pageAdapter;
    TutorialFragment firstFragment;
    ViewPager mPager;
    int currentPageIndex;

    List<TutorialDTO> list = new ArrayList<>();
    private List<TutorialPageFragment> pageFragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        //count = (TextView) findViewById(R.id.tutorialCount);
        //listView = (ListView) findViewById(R.id.tutorials);
        mPager = (ViewPager) findViewById(R.id.learn_container);
        RequestDTO req = new RequestDTO();
        req.setCancerID(1);
        req.setRequestType(RequestDTO.getTutorial);
        WebSocketUtil.sendRequest(getApplication(), req, Statics.socket, new WebSocketUtil.WebSocketListener() {
            @Override
            public void onMessage(ResponseDTO dto) {
                list = dto.getTutorials();
                if (list != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //opva = new TutorialListAdapter(getApplicationContext(), R.layout.tutorial_list_item, list);
                            //listView.setAdapter(opva);
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
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ActionBar actionBar = getSupportActionBar();
        PinkUtil.setActionBar(actionBar, getApplication(), sp.getString("username", ""));
    }

    private void buildPages(List<TutorialDTO> lst) {
        pageFragmentList = new ArrayList<>();
        for (TutorialDTO dto : lst) {
            firstFragment = new TutorialFragment();
            Bundle bundle = new Bundle();
            bundle.putString("text", dto.getContent());
            firstFragment.setArguments(bundle);
            pageFragmentList.add(firstFragment);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_learn, menu);
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
