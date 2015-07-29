package com.pinkd.pinkdrive;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by admin on 2015/03/02.
 */
public class PinkUtil {
    public static void setCustomActionBar(Context ctx,
                                          ActionBar actionBar, String text, String subTitle, Drawable image) {
        actionBar.setDisplayShowCustomEnabled(true);

        LayoutInflater inflator = (LayoutInflater)
                ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.action_bar, null);
        TextView title = (TextView) v.findViewById(R.id.action_bar_title);
        TextView sub = (TextView) v.findViewById(R.id.action_bar_subtitle);
        ImageView logo = (ImageView) v.findViewById(R.id.action_bar_logo);
        title.setText(text);
        sub.setText(subTitle);

        logo.setImageDrawable(image);
        actionBar.setCustomView(v);
        actionBar.setTitle("");
        actionBar.setSubtitle("");
    }

    public static void setActionBar(ActionBar actionBar, Context ctx, String name){
        //actionBar = getSupportActionBar();
        Drawable drawable = ctx.getResources().getDrawable(R.drawable.ic_launcher);
        setCustomActionBar(ctx, actionBar, "Pink Drive", name, drawable);
        Drawable dd = ctx.getResources().getDrawable(R.drawable.titlebar);
        actionBar.setBackgroundDrawable(dd);
    }
}
