package com.pinkd.pinkdrive;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nunens.pinkd.dto.CalenderDTO;
import com.nunens.pinkd.dto.MythDTO;
import com.nunens.pinkd.dto.RequestDTO;
import com.nunens.pinkd.dto.ResponseDTO;
import com.nunens.pinkd.util.FileUtil;
import com.nunens.pinkd.util.Statics;
import com.nunens.pinkd.util.ToastUtil;
import com.nunens.pinkd.util.WebSocketUtil;
import com.pinkd.pinkdrive.Fragments.DashboardPageFragment;
import com.pinkd.pinkdrive.Fragments.HomeAFragment;
import com.pinkd.pinkdrive.Fragments.HomeBFragment;
import com.pinkd.pinkdrive.Fragments.HomeCFragment;
import com.pinkd.pinkdrive.util.NavDrawerItem;
import com.pinkd.pinkdrive.util.NavDrawerListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class DashboardActivity extends AppCompatActivity {

    Gson gson = new Gson();
    SharedPreferences sp;


    // nav drawer title
    private CharSequence mDrawerTitle;

    // used to store app title
    private CharSequence mTitle;

    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;


    private List<DashboardPageFragment> pageFragmentList;
    PagerAdapter pageAdapter;
    HomeAFragment homeAFragment;
    HomeBFragment homeBFragment;
    HomeCFragment homeCFragment;
    ViewPager mPager;
    int currentPageIndex;


    View gogo;
    Timer timer;
    TextView gogoMyth;
    List<MythDTO> mythList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setup();
    }

    void setup() {
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ActionBar actionBar = getSupportActionBar();
        PinkUtil.setActionBar(actionBar, getApplication(), sp.getString("username", ""));
        mPager = (ViewPager) findViewById(R.id.frame_container);
        gogo = findViewById(R.id.gogo);
        timer = new Timer();
        gogoMyth = (TextView) findViewById(R.id.mythGogo);
        buildPages();
        setDrawer();
        readSavedInstance();
        if (mythList.isEmpty()) {
            RequestDTO req = new RequestDTO();
            req.setRequestType(RequestDTO.getMyth);
            WebSocketUtil.sendRequest(getApplication(), req, Statics.socket, new WebSocketUtil.WebSocketListener() {
                @Override
                public void onMessage(ResponseDTO response) {
                    if (!response.getMessage().equals("connected")) {
                        for (MythDTO dto : response.getMyths()) {
                            mythList.add(dto);
                        }
                        saveMyth();
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
        startTimer();
    }

    void startTimer() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!mythList.isEmpty()) {
                    getRandomMyth();
                    Log.d("", "getting myth for gogo");
                }
            }
        }, 0, 15000);
    }

    void saveMyth() {
        List<String> list = new ArrayList<>();
        JSONObject obj = new JSONObject();
        try {
            obj.put("myths", gson.toJson(mythList));
            Log.d("JSON", obj.toString() + gson.toJson(obj));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        FileUtil.saveFile(gson.toJson(obj), "myths", getApplication());
        Log.d("", "File Saved: " + gson.toJson(obj));
    }

    private void readSavedInstance() {
        String file = null;
        try {
            file = FileUtil.readFile("myths", getApplication());
        } catch (Exception e) {
            Log.d(DashboardActivity.class.getSimpleName(), "Can't read savedInstance from file");
        }
        Log.i("Line 131: ", file);
        if (!file.equals("error")) {
            try {
                JSONObject rb = new JSONObject(file);
                JSONObject r = new JSONObject(rb.get("nameValuePairs").toString());
                List<String> list = new ArrayList<String>();
                JSONArray myths = new JSONArray(r.get("myths").toString());
                if (myths.length() > 0) {
                    for (int i = 0; i < myths.length(); i++) {
                        list.add(myths.getString(i));
                        MythDTO dto = gson.fromJson(myths.getString(i), MythDTO.class);
                        mythList.add(dto);
                    }
                } else {
                    Log.d("", "A gona selo mo di myth clever");
                }
            } catch (Exception e) {
                Log.d("", "error reading savedInstance");
                e.printStackTrace();
            }
        } else {
        }
    }

    void getRandomMyth() {
        Random r = new Random();
        int Low = 1;
        int High = mythList.size();
        final int i = r.nextInt(High - Low) + Low;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                gogoMyth.setText(mythList.get(i).getContent());
                gogo.setVisibility(View.GONE);
                Log.d("", "getting myth for gogo set the visibility gone");
                gogo.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gogo.setVisibility(View.VISIBLE);
                        Log.d("", "getting myth for gogo and set her visible " + mythList.get(i).getContent());
                    }
                }, 2000);
            }
        });
    }

    ActionBarDrawerToggle mDrawerToggle;

    void setDrawer() {
        mTitle = mDrawerTitle = getTitle();

        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // nav drawer icons from resources
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        navDrawerItems = new ArrayList<NavDrawerItem>();

        // adding nav drawer items to array
        // Home
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // Find People
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        // Find People
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));

        // Recycle the typed array
        navMenuIcons.recycle();

        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems, new NavDrawerListAdapter.DrawerListener() {
            @Override
            public void onClick(int position, String text) {
                switch (position) {
                    case 0:
                        mDrawerLayout.closeDrawers();
                        showCalenderDialog();
                        break;
                    case 1:
                        mDrawerLayout.closeDrawers();
                        //startActivity(new Intent(getApplicationContext(), MapActivity.class));
                        startActivity(new Intent(getApplicationContext(), LearnActivity.class));
                        break;
                    case 2:
                        mDrawerLayout.closeDrawers();
                        startActivity(new Intent(getApplicationContext(), MythActivity.class));
                        break;
                    case 3:
                        //finish();
                        mDrawerLayout.closeDrawers();
                        startActivity(new Intent(getApplicationContext(), TutorialActivity.class));
                        break;
                    case 5:
                        mDrawerLayout.closeDrawers();
                        startActivity(new Intent(getApplicationContext(), GameActivity.class));
                        break;
                    case 4:
                        mDrawerLayout.closeDrawers();
                        startActivity(new Intent(getApplicationContext(), QuestionAndAnswer.class));
                        break;
                }
            }
        });

        LayoutInflater in = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = in.inflate(R.layout.drawer_header, null);
        final ActionBar actionBar = getSupportActionBar();
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.drawable.ic_drawer,
                R.string.drawer_open,
                R.string.drawer_closed
        ) {
            public void onDrawerClosed(View view) {
                //actionBar.setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }


            public void onDrawerOpened(View drawerView) {
                //actionBar.setTitle("Preview Mode");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        //mDrawerToggle.syncState();
        //mDrawerToggle.setDrawerIndicatorEnabled(true);
        //mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerList.addHeaderView(v);
        mDrawerList.setAdapter(adapter);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    private void buildPages() {
        pageFragmentList = new ArrayList<>();
        homeCFragment = new HomeCFragment();
        homeBFragment = new HomeBFragment();
        homeAFragment = new HomeAFragment();


        pageFragmentList.add(homeCFragment);
        pageFragmentList.add(homeBFragment);
        pageFragmentList.add(homeAFragment);
        initializeAdapter();

    }

    private void initializeAdapter() {
        pageAdapter = new PagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pageAdapter);
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                currentPageIndex = arg0;
                if (arg0 == 0) {
                    setTitle("Home");
                } else if (arg0 == 1) {
                    setTitle("Home");
                } else {
                    setTitle("Home");
                }
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
            if (position == 1) {
                title = "Home";
            } else if (position == 2) {
                title = ("Home");
            } else {
                title = "Home";
            }

            return title;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer = new Timer();
        timer.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        timer = new Timer();
        startTimer();
    }

    private void showCalenderDialog() {
        //Log.d("LatLng", latLng.latitude + ", " + latLng.longitude);
        LayoutInflater factory = LayoutInflater.from(this);
        View view = factory.inflate(R.layout.calender_dialog, null);
        final AlertDialog findDialog = new AlertDialog.Builder(this).create();
        findDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        findDialog.setView(view);
        final CalendarView cal = (CalendarView) view.findViewById(R.id.calenderPicker);
        Button addCalender = (Button) view.findViewById(R.id.add_calender);
        addCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestDTO req = new RequestDTO();
                req.setRequestType(RequestDTO.addCalender);
                CalenderDTO dto = new CalenderDTO();
                dto.setmDate(cal.getDate());
                dto.setUserID(sp.getInt("userID", 0));
                req.setCalenderDTO(dto);
                WebSocketUtil.sendRequest(getApplication(), req, Statics.socket, new WebSocketUtil.WebSocketListener() {
                    @Override
                    public void onMessage(ResponseDTO response) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.toast(getApplication(), "Menstruation date successfully added");
                                //view.finish();
                                findDialog.cancel();
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
        });
        findDialog.show();
    }
}
