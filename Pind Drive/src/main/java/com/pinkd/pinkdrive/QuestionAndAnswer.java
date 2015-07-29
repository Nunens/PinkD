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

import com.nunens.pinkd.dto.QuestionAnswerDTO;
import com.nunens.pinkd.dto.RequestDTO;
import com.nunens.pinkd.dto.ResponseDTO;
import com.nunens.pinkd.util.Statics;
import com.nunens.pinkd.util.WebSocketUtil;
import com.pinkd.pinkdrive.Fragments.QuestionAndAnswerFragment;
import com.pinkd.pinkdrive.Fragments.QuestionAndAnswerPageFragment;

import java.util.ArrayList;
import java.util.List;


public class QuestionAndAnswer extends ActionBarActivity {
    private List<QuestionAndAnswerPageFragment> pageFragmentList;
    PagerAdapter pageAdapter;
    QuestionAndAnswerFragment questionFragment;
    ViewPager mPager;
    List<QuestionAnswerDTO> list;
    int currentPageIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_and_answer);
        mPager = (ViewPager)findViewById(R.id.question_and_answer_container);
        getQuestionAndAnswer();
    }

    private void getQuestionAndAnswer(){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ActionBar actionBar = getSupportActionBar();
        PinkUtil.setActionBar(actionBar, getApplication(), sp.getString("username", ""));
        RequestDTO req = new RequestDTO();
        req.setRequestType(RequestDTO.getQuestionAndAnswer);
        WebSocketUtil.sendRequest(getApplication(), req, Statics.socket, new WebSocketUtil.WebSocketListener() {
            @Override
            public void onMessage(final ResponseDTO response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(!response.getQuestionAnswers().isEmpty()){
                            list = response.getQuestionAnswers();
                            buildPages(list);
                        }
                    }
                });
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
        getMenuInflater().inflate(R.menu.menu_question_and_answer, menu);
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

    private void buildPages(List<QuestionAnswerDTO> lst) {
        pageFragmentList = new ArrayList<>();
        for(QuestionAnswerDTO dto : lst) {
            questionFragment = new QuestionAndAnswerFragment();
            Bundle bundle = new Bundle();
            bundle.putString("username", dto.getUsername());
            bundle.putString("adminname", dto.getAdminname());
            bundle.putString("question", dto.getQuestion());
            bundle.putString("answer", dto.getAnswer());
            questionFragment.setArguments(bundle);

            pageFragmentList.add(questionFragment);
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
