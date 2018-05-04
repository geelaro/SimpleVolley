package com.geelaro.simplevolley.adapter;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.geelaro.simplevolley.AppController;
import com.geelaro.simplevolley.R;
import com.geelaro.simplevolley.data.FeedItems;

import java.util.List;

import static com.android.volley.VolleyLog.TAG;

/**
 * Created by LEE on 2018/5/4.
 */

public class FeedListAdapter extends BaseAdapter {
    private List<FeedItems> data;
    private Context context;
    private ImageLoader imageLoader;


    public FeedListAdapter(Context context, List<FeedItems> feedItems) {
        this.context = context;
        data = feedItems;
        Log.d(TAG, "FeedListAdapter: ");
    }

    public void setData(List<FeedItems> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (imageLoader == null) {
            imageLoader = AppController.getInstance().getImageLoader();
        }
        //

        ItemViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ItemViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.feed_items, null);
            viewHolder.profilePic = (NetworkImageView) convertView.findViewById(R.id.profilePic);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.timeStamp = (TextView) convertView.findViewById(R.id.timestamp);
            viewHolder.statusTxt = (TextView) convertView.findViewById(R.id.txt_status);
            viewHolder.txtUrl = (TextView) convertView.findViewById(R.id.txt_url);
            viewHolder.feedImage = (NetworkImageView) convertView.findViewById(R.id.feed_image);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ItemViewHolder) convertView.getTag();
        }
        //
        FeedItems feedItems = data.get(position);

        viewHolder.name.setText(feedItems.getName());
        viewHolder.timeStamp.setText(feedItems.getTimeStamp());

        // Chcek for empty status message
        if (!TextUtils.isEmpty(feedItems.getStatus())) {
            viewHolder.statusTxt.setText(feedItems.getStatus());
            viewHolder.statusTxt.setVisibility(View.VISIBLE);
        } else {
            // status is empty, remove from view
            viewHolder.statusTxt.setVisibility(View.GONE);
        }

        // Checking for null feed url
        if (feedItems.getUrl() != null) {
            viewHolder.txtUrl.setText(Html.fromHtml("<a href=\"" + feedItems.getUrl() + "\">"
                    + feedItems.getUrl() + "</a> "));

            // Making url clickable
            viewHolder.txtUrl.setMovementMethod(LinkMovementMethod.getInstance());
            viewHolder.txtUrl.setVisibility(View.VISIBLE);
        } else {
            // url is null, remove from the view
            viewHolder.txtUrl.setVisibility(View.GONE);
        }

        // user profile pic
        viewHolder.profilePic.setImageUrl(feedItems.getProfilePic(), imageLoader);

        // Feed image
        if (feedItems.getImage() != null) {
            viewHolder.feedImage.setImageUrl(feedItems.getImage(), imageLoader);
            viewHolder.feedImage.setVisibility(View.VISIBLE);

        } else {
            viewHolder.feedImage.setVisibility(View.GONE);
        }

        return convertView;
    }

    static class ItemViewHolder {
        NetworkImageView profilePic;
        TextView name;
        TextView timeStamp;
        TextView txtUrl;
        TextView statusTxt;
        NetworkImageView feedImage;
    }


}
