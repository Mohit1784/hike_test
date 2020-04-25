package com.android.hike.stimulation.pojo;

public class Photo {

    private String id;
    private String owner ;
    private String secret ;
    private String server ;
    private int farm ;
    private String title ;
    private int ispublic;
    private String photoUrl;

    public int getVisitedPageCount() {
        return VisitedPageCount;
    }

    public void setVisitedPageCount(int visitedPageCount) {
        VisitedPageCount = visitedPageCount;
    }

    public static int VisitedPageCount = 0;

    public int getTotalPagecount() {
        return TotalPagecount;
    }

    public void setTotalPagecount(int totalPagecount) {
        TotalPagecount = totalPagecount;
    }

    private int TotalPagecount = 1;

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public int isIspublic() {
        return ispublic;
    }

    public void setIspublic(int ispublic) {
        this.ispublic = ispublic;
    }

    private int isfriend;
    private int isfamily;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIspublic() {
        return ispublic;
    }

    public int getIsfriend() {
        return isfriend;
    }

    public void setIsfriend(int isfriend) {
        this.isfriend = isfriend;
    }

    public int getIsfamily() {
        return isfamily;
    }

    public void setIsfamily(int isfamily) {
        this.isfamily = isfamily;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getFarm() {
        return farm;
    }

    public void setFarm(int farm) {
        this.farm = farm;
    }
}
