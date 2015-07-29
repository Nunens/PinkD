package com.pinkd.pinkdrive.util;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pinkd.pinkdrive.R;

import java.util.ArrayList;

/**
 * Created by admin on 2015/02/17.
 */
public class NavDrawerListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<NavDrawerItem> navDrawerItems;
    DrawerListener listener;

    public NavDrawerListAdapter(Context context, ArrayList<NavDrawerItem> navDrawerItems, DrawerListener listener) {
        this.context = context;
        this.navDrawerItems = navDrawerItems;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return navDrawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return navDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.drawer_list_item, null);
        }

        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        final TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
        final TextView txtCount = (TextView) convertView.findViewById(R.id.counter);

        imgIcon.setImageResource(navDrawerItems.get(position).getIcon());
        txtTitle.setText(navDrawerItems.get(position).getTitle());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(position, txtTitle.getText().toString());
            }
        });
        // displaying count
        // check whether it set visible or not
        if (navDrawerItems.get(position).getCounterVisibility()) {
            txtCount.setText(navDrawerItems.get(position).getCount());
        } else {
            // hide the counter view
            txtCount.setVisibility(View.GONE);
        }

        return convertView;
    }

    public interface DrawerListener {
        public void onClick(int position, String text);
    }

}