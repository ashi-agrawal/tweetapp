package com.codepath.apps.mysimpletweets.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codepath.apps.mysimpletweets.R;
import com.codepath.apps.mysimpletweets.TweetsArrayAdapter;
import com.codepath.apps.mysimpletweets.models.Tweet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashiagrawal on 6/28/16.
 */
public class TweetsListFragment extends Fragment {

    private TweetsArrayAdapter aTweets;
    private ArrayList<Tweet> tweetsArray;
    private ListView lvTweets;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tweetsArray = new ArrayList<Tweet>();
        aTweets = new TweetsArrayAdapter(getActivity(), tweetsArray);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tweets_list, parent, false);
        lvTweets = (ListView) v.findViewById(R.id.lvTweets);
        lvTweets.setAdapter(aTweets);
        return v;
    }

    public void addAll(List<Tweet> tweets){
        aTweets.addAll(tweets);

    }

    public void addTweetToStart(Tweet tweet){
        tweetsArray.add(0, tweet);
        aTweets.notifyDataSetChanged();
        lvTweets.setSelection(0);
    }
}
