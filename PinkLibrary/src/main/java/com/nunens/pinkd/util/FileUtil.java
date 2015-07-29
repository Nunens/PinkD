package com.nunens.pinkd.util;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by admin on 2015/01/20.
 */
public class FileUtil {
    static Context context;
    static String TAG = FileUtil.class.getSimpleName();

    public static boolean saveCache(String c, String fileName, Context ctx) {
        String content = c;
        File file;
        context = ctx;
        FileOutputStream outputStream;
        try {
            file = new File(context.getCacheDir(), fileName);

            outputStream = new FileOutputStream(file);
            outputStream.write(content.getBytes());
            outputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String readFile(String fileName, Context ctx) {
        context = ctx;
        BufferedReader input = null;
        File file = null;
        try {
            file = new File(context.getFilesDir(), fileName);

            input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = input.readLine()) != null) {
                buffer.append(line);
            }

            Log.d(TAG, buffer.toString());
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    public static boolean saveFile(String c, String fileName, Context ctx) {
        Gson gson = new Gson();
        Log.d(TAG, fileName+": "+gson.toJson(c));
        String content = c;
        context = ctx;
        FileOutputStream outputStream = null;
        try {
            outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(content.getBytes());
            outputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
