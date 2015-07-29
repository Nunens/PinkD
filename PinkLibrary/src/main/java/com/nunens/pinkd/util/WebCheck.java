package com.nunens.pinkd.util;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.util.Log;

public class WebCheck {
    public static final String TAG = "WebCheck";
    static ConnectivityManager connectivity;

    private static boolean wifiConnected, mobileConnected;

    static NetworkInfo wifiInfo;
    static NetworkInfo mobileInfo;

    public static void startWirelessSettings(Context ctx) {
        Intent i = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
        ctx.startActivity(i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    public static void checkNetworkAvailability(Context ctx) {
        // ARE WE CONNECTED TO THE NET
        long start = System.currentTimeMillis();
        if (connectivity == null) {
            connectivity = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        wifiInfo = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        mobileInfo = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        long end = System.currentTimeMillis();
        Log.d(TAG, "###### Network check completed in " + (end - start) + " ms");
    }

    public static boolean checkMobileNetwork(Context ctx){
        checkNetworkAvailability(ctx);
        if (mobileInfo != null) {
            if (mobileInfo.isAvailable()) {
                Log.w(TAG, "###### MOBILE_NETWORK AVAILABLE on check");
                if (mobileInfo.isConnected()) {
                    Log.w(TAG, "###### MOBILE_NETWORK CONNECTED on check");
                    mobileConnected = true;
                } else {
                    Log.d(TAG, "@@@ MOBILE_NETWORK NOT CONNECTED on check");
                    mobileConnected = false;
                }
            } else {
                Log.d(TAG, "@@@ MOBILE_NETWORK NOT AVAILABLE on check");
                mobileConnected = false;
            }
        }
        return mobileConnected;
    }

    public static boolean checkWifi(Context ctx){
        checkNetworkAvailability(ctx);
        if (wifiInfo.isAvailable()) {
            Log.w(TAG, "###### WIFI AVAILABLE on check");
            if (wifiInfo.isConnected()) {
                Log.w(TAG, "###### WIFI CONNECTED on check");
                wifiConnected = true;
            } else {
                Log.e(TAG, "@@@ WIFI NOT CONNECTED on check");
                wifiConnected = false;
            }
        } else {
            Log.e(TAG, "###### WIFI NOT AVAILABLE on check");
            wifiConnected = false;
        }
        return wifiConnected;
    }
}
