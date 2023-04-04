package com.shubhankar.newsapp3;

import com.shubhankar.newsapp3.Models.NewsHeadline;

import java.util.List;

public interface OnFetchDataListner<NewsApiResponse> {
    void onFetchData(List<NewsHeadline> list,String message);
    void OnError(String message);
}
