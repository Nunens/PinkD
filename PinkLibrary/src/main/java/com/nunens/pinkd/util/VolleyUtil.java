package com.nunens.pinkd.util;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.nunens.pinkd.dto.RequestDTO;
import com.nunens.pinkd.dto.ResponseDTO;

import org.json.JSONObject;

/**
 * Created by Sipho on 2015/01/07.
 */
public class VolleyUtil {
    static JsonObjectRequest req;
    static RequestQueue que;
    static ResponseDTO resp;
    static RequestDTO requestDTO;
    static Gson gson;
    static VolleyListener listener;
    static String url;
    static String log = "VolleyUtil";

    public static void sendVolleyRequest(Context ctx, RequestDTO r, final VolleyListener listener){
        Log.d(log, "Starting volley request");
        que = Volley.newRequestQueue(ctx);
        resp = new ResponseDTO();
        gson = new Gson();
        try {
            url = "http://www.nunens.com/Pinkd/Servlet.php?JSON=" + gson.toJson(r);
            //url = "http://10.154.165.138/PinkDrive/Servlet.php?JSON=" + gson.toJson(r);
            Log.i(log, url);
        }catch(Exception e){
            Log.i(log, "error");
            e.printStackTrace();
        }
        req = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>(){

            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.i(log, jsonObject.toString());
                resp = gson.fromJson(jsonObject.toString(), ResponseDTO.class);
                Log.i(log, url);
                listener.onResponse(resp);
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                volleyError.printStackTrace();
                listener.onErrorResponse(volleyError);
            }
        });
        que.add(req);
    }

    public interface VolleyListener {
        public void onResponse(ResponseDTO dto);
        public void onErrorResponse(VolleyError error);
    }
}
