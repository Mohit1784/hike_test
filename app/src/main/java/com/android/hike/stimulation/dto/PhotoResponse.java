package com.android.hike.stimulation.dto;

import com.android.hike.stimulation.pojo.Photo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PhotoResponse {

    private ArrayList<Photo> mData;
    public ArrayList<Photo> getResults() {
        return mData;
    }
    public PhotoResponse fromJson(String json) {
        try {
            JSONObject jsonObject=new JSONObject(json);
            JSONObject response = jsonObject.getJSONObject("photos");
            JSONArray jsonArray =  response.getJSONArray("photo");
            if (jsonArray != null) {
                mData = new ArrayList<Photo>();
                Photo obj;
                for (int i=0;i<jsonArray.length();i++) {
                    obj = new Photo();
                    obj.setTotalPagecount(response.getInt("pages"));
                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                    obj.setId(jsonObj.getString("id"));
                    obj.setOwner(jsonObj.getString("owner"));
                    obj.setSecret(jsonObj.getString("secret"));
                    obj.setServer(jsonObj.getString("server"));
                    obj.setTitle(jsonObj.getString("title"));
                    obj.setIspublic(jsonObj.getInt("ispublic"));
                    obj.setIsfriend(jsonObj.getInt("isfriend"));
                    obj.setIsfamily(jsonObj.getInt("isfamily"));
                    obj.setFarm(jsonObj.getInt("farm"));
                    obj.setPhotoUrl(CreateURI(obj));
                    mData.add(obj);

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    return this;
    }

    private String CreateURI(Photo obj)
    {
        String url = "https://farm" +obj.getFarm()+".static.flickr.com/"+obj.getServer()+"/"+obj.getId()+"_"+obj.getSecret()+".jpg" ;
        return url;

    }
}
