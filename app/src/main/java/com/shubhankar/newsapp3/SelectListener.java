package com.shubhankar.newsapp3;

import com.shubhankar.newsapp3.Models.NewsHeadline;

//listener when a user clicked to the  news
public interface SelectListener {
    void OnNewsClicked(NewsHeadline headlines);
}
