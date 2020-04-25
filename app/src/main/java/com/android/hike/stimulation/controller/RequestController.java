package com.android.hike.stimulation.controller;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

    public class RequestController {
        private static RequestController mInstance;
        private RequestQueue mRequestQueue;
        private static Context mCtx;
        public static final String TAG = RequestController.class
                .getSimpleName();

        private RequestController(Context context) {
            mCtx = context;
            mRequestQueue = getRequestQueue();


        }

        public static synchronized RequestController getInstance(Context context) {
            if (mInstance == null) {
                mInstance = new RequestController(context);
            }
            return mInstance;
        }

        public RequestQueue getRequestQueue() {
            if (mRequestQueue == null) {
               mRequestQueue = Volley.newRequestQueue(mCtx);
            }
            return mRequestQueue;
        }

        public <T> void addToRequestQueue(Request<T> req) {
            getRequestQueue().add(req);
        }

        public <T> void addToRequestQueue(Request<T> req, String tag) {
              req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
            getRequestQueue().add(req);
        }


    }


