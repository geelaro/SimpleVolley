package com.geelaro.simplevolley.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by LEE on 2018/5/4.
 */

public class FeedItems {
    private int id;

    private String name;
    private String image;
    private String status;
    private String profilePic;
    private String timeStamp;
    private String url;

    public FeedItems(){}

    public FeedItems(int id,String name,String image,String status,String profilePic,String timeStamp,String url){
        this.id=id;
        this.name =name;
        this.image  =image;
        this.status = status;
        this.profilePic = profilePic;
        this.timeStamp = timeStamp;
        this.url = url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getUrl() {
        return url;
    }

}

