package com.codepath.apps.mysimpletweets.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.codepath.apps.mysimpletweets.TwitterApplication;
import com.codepath.apps.mysimpletweets.TwitterClient;
import com.codepath.apps.mysimpletweets.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class UserTimelineFragment extends TweetsListFragment {

        private TwitterClient client;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            client = TwitterApplication.getRestClient();
            populateTimeline();
        }

        public static UserTimelineFragment newInstance (String screen_name){
            UserTimelineFragment userTimeline = new UserTimelineFragment();
            Bundle args = new Bundle();
            args.putString("screenName", screen_name);
            userTimeline.setArguments(args);
            return userTimeline;
        }

        public void populateTimeline() {
            String screen_name = getArguments().getString("screenName");
            client.getUserTimeline(screen_name, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                    addAll(Tweet.fromJSONArray(response));
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                }
            });
        }

}
