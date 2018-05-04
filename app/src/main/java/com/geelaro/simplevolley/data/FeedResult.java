package com.geelaro.simplevolley.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by LEE on 2018/5/4.
 */

public class FeedResult {
    @SerializedName("feed")
    private List<FeedItems> feedList;


    public void setFeedList(List<FeedItems> feedList) {
        this.feedList = feedList;
    }

    public List<FeedItems> getFeedList() {
        return feedList;
    }

}
