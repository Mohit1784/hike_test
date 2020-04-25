package com.android.hike.stimulation;

import java.util.List;

public  interface ResponseCallback {

        void onSuccessResponse(String result);

        void onErrorResponse(String Error);

        void onSuccessResponse(List result);


}
