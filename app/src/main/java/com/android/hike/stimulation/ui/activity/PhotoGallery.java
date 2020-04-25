package com.android.hike.stimulation.ui.activity;


import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.hike.stimulation.AppConstants;
import com.android.hike.stimulation.R;
import com.android.hike.stimulation.ResponseCallback;
import com.android.hike.stimulation.controller.PhotoController;
import com.android.hike.stimulation.pojo.Photo;
import com.android.hike.stimulation.ui.adapter.GalleryAdapter;


import java.util.ArrayList;
import java.util.List;

public class PhotoGallery extends AppCompatActivity implements ResponseCallback {

    ArrayList<Photo> mList = new ArrayList<Photo>();
    GalleryAdapter adapter;
    private int previousTotal = 0;
    private boolean loading = true;
    private int pastVisiblesItems;
    int visibleItemCount, totalItemCount;
    GridLayoutManager gridLayoutManager;
    RecyclerView recyclerView;
    PhotoController controller;
    int visitedpageCount = 1;


    public PhotoGallery getmContext() {
        return mContext;
    }

    public void setmContext(PhotoGallery mContext) {
        this.mContext = mContext;
    }

    PhotoGallery mContext;


    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            updateUI(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_gallery);
        Intent intent = getIntent();
        setmContext(this);
        final String Query = intent.getStringExtra(AppConstants.QUERY_STRING);
        recyclerView = (RecyclerView) findViewById(R.id.grid_recyclerView);
        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new GalleryAdapter(this, mList);
        controller = new PhotoController(getApplication());
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        visitedpageCount = prefs.getInt(AppConstants.PAGE_PREF_KEY, 0);
        controller.RegisterCallback(this);
        controller.RequestService(Query, 1);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    visibleItemCount = gridLayoutManager.getChildCount();
                    totalItemCount = gridLayoutManager.getItemCount();
                    pastVisiblesItems = gridLayoutManager.findFirstVisibleItemPosition();
                     if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                           Log.d("Mohit", "Last Item ");
                            controller.RequestService(Query, ++visitedpageCount);
                        }

                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        prefs.edit().clear().commit();

    }

    private void updateUI(Message o) {

        String response = o.obj.toString();
        if("OK".equalsIgnoreCase(response))
         adapter.notifyDataSetChanged();
    }

    @Override
    public void onSuccessResponse(String result) {

    }

    @Override
    public void onErrorResponse(String Error) {

    }

    @Override
    public void onSuccessResponse(List result) {
        mList.addAll(result);
        recyclerView.setAdapter(adapter);
        adapter.SetData(mList);
        Message msg = new Message();
        msg.obj = "OK";
        handler.sendMessage(msg);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
