package com.geelaro.simplevolley;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.geelaro.simplevolley.adapter.FeedListAdapter;
import com.geelaro.simplevolley.data.FeedItems;
import com.geelaro.simplevolley.data.FeedResult;
import com.geelaro.simplevolley.utils.GsonRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LEE on 2018/5/4.
 */

public class FeedListActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    private ListView listView;
    private List<FeedItems> itemsList;
    private FeedListAdapter listAdapter;

    private static final String FEED_URL = "https://api.androidhive.info/feed/feed.json";

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_list);

        listView = (ListView) findViewById(R.id.list_feed);

        itemsList = new ArrayList<>();
        listAdapter = new FeedListAdapter(this, itemsList);
        listView.setAdapter(listAdapter);
        listView.setDivider(null);

        GsonRequest<FeedResult> gsonRequest = new GsonRequest<FeedResult>(FEED_URL, FeedResult.class,
                new Response.Listener<FeedResult>() {
                    @Override
                    public void onResponse(FeedResult response) {
                        itemsList = response.getFeedList();
                        listAdapter.setData(itemsList);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Error: " + error.getMessage());

                    }
                });
        //添加请求到队列中
        AppController.getInstance().addToQueueRequest(gsonRequest, TAG);
    }

}
