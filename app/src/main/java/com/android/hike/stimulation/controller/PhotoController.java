package com.android.hike.stimulation.controller;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.android.hike.stimulation.AppConstants;
import com.android.hike.stimulation.R;
import com.android.hike.stimulation.ResponseCallback;
import com.android.hike.stimulation.dto.PhotoResponse;
import com.android.hike.stimulation.pojo.Photo;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.List;

public class PhotoController {

    Application mContext;
    ResponseCallback callback;

    public  ArrayList<Photo> mData = new ArrayList<>();

   public  PhotoController(Application context)
    {
        mContext = context;

    }
    public void RegisterCallback(ResponseCallback Callback)
    {
        this.callback = Callback ;
    }
    public void RequestService(String query,final int pagecount)
    {
            String url = AppConstants.URL_SEARCH +query.toString() ;
            String PageCount = "&page="+pagecount;
            String FinalUrl = url+PageCount;
            StringRequest strReq = new StringRequest(com.android.volley.Request.Method.GET, FinalUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    ResponseService(response,pagecount);
                    //callback.onSuccessResponse(mData);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Simulate",error.getMessage());

                }
            });

        RequestController.getInstance(mContext).addToRequestQueue(strReq, "json_obj_req");

    }


    public void ResponseService(String Result, int count) {
        PhotoResponse response = new PhotoResponse();
        response.fromJson(Result);
        mData = response.getResults();
        callback.onSuccessResponse(mData);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(AppConstants.PAGE_PREF_KEY,count );
        editor.commit();

    }
}
