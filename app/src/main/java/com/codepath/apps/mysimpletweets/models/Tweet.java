package com.codepath.apps.mysimpletweets.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ashiagrawal on 6/28/16.
 */
public class Tweet {

    private String body;
    private long id;
    private User user;
    private String createdAt;

    public String getBody() {
        return body;
    }

    public long getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public static ArrayList<Tweet> fromJSONArray(JSONArray json){
        ArrayList<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i < json.length(); i++){
            try {
                Tweet newTweet = fromJSON(json.getJSONObject(i));
                if (newTweet != null) tweets.add(newTweet);
            } catch (JSONException e){
                e.printStackTrace();
                continue;
            }
        }
        return tweets;
    }

    public static Tweet fromJSON(JSONObject jsonObject){
        Tweet tweet = new Tweet();
        try {
            tweet.body = jsonObject.getString("text");
            tweet.id = jsonObject.getLong("id");
            tweet.createdAt = jsonObject.getString("created_at");
            tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
        } catch (JSONException e){
            e.printStackTrace();
        }
        return tweet;
    }
}
