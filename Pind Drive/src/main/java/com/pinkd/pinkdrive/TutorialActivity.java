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

import com.google.gson.Gson;
import com.nunens.pinkd.dto.TutorialDTO;
import com.pinkd.pinkdrive.Fragments.FirstStepFragment;
import com.pinkd.pinkdrive.Fragments.SecondStepFragment;
import com.pinkd.pinkdrive.Fragments.ThirdStepFragment;
import com.pinkd.pinkdrive.Fragments.TutorialPageFragment;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class TutorialActivity extends ActionBarActivity {
    Gson gson = new Gson();

    PagerAdapter pageAdapter;
    FirstStepFragment first = new FirstStepFragment();
    SecondStepFragment second = new SecondStepFragment();
    ThirdStepFragment third = new ThirdStepFragment();
    ViewPager mPager;
    int currentPageIndex;

    List<TutorialDTO> list = new ArrayList<>();
    private List<TutorialPageFragment> pageFragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ActionBar actionBar = getSupportActionBar();
        PinkUtil.setActionBar(actionBar, getApplication(), sp.getString("username", ""));
        mPager = (ViewPager) findViewById(R.id.tutorial_container);
        buildPages();
        /*RequestDTO req = new RequestDTO();
        req.setRequestType(RequestDTO.getTutorial);
        req.setCancerID(1);
        WebSocketUtil.sendRequest(getApplication(), req, Statics.socket, new WebSocketUtil.WebSocketListener() {
            @Override
            public void onMessage(ResponseDTO dto) {
                list = dto.getTutorials();
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
        });*/
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

    private void buildPages() {
        pageFragmentList = new ArrayList<>();
        /*for (TutorialDTO dto : lst) {
            firstFragment = new TutorialFragment();
            Bundle bundle = new Bundle();
            bundle.putString("text", dto.getContent());
            firstFragment.setArguments(bundle);
            pageFragmentList.add(firstFragment);
        }*/
        pageFragmentList.add(first);
        pageFragmentList.add(second);
        pageFragmentList.add(third);
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
