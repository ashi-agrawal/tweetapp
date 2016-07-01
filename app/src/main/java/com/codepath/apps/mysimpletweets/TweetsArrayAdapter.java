package com.codepath.apps.mysimpletweets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.mysimpletweets.models.Tweet;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by ashiagrawal on 6/28/16.
 */

public class TweetsArrayAdapter extends ArrayAdapter<Tweet> {

    public TweetsArrayAdapter(Context context, List<Tweet> tweets) {
        super(context, android.R.layout.simple_list_item_1, tweets);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        if (convertView != null){
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tweet, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        Tweet tweet = getItem(position);
        holder.tvUsername.setText("@" + tweet.getUser().getScreenName());
        holder.tvName.setText(tweet.getUser().getName());
        holder.tvBody.setText(tweet.getBody());
        holder.ivProfImage.setImageResource(android.R.color.transparent);
        String formattedTime = TimeFormatter.getTimeDifference(tweet.getCreatedAt());
        holder.tvTime.setText(formattedTime);
        Glide.with(getContext()).load(tweet.getUser().getProfileImageUrl()).bitmapTransform(new RoundedCornersTransformation(this.getContext(), 10, 0)).into(holder.ivProfImage);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.ivProfImage) ImageView ivProfImage;
        @BindView(R.id.tvUsername) TextView tvUsername;
        @BindView(R.id.tvBody) TextView tvBody;
        @BindView(R.id.tvTime) TextView tvTime;
        @BindView(R.id.tvName) TextView tvName;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
