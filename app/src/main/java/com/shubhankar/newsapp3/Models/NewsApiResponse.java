package com.shubhankar.newsapp3.Models;

import java.io.Serializable;
import java.util.List;

public class NewsApiResponse implements Serializable {    //this is upper model class of api class which contain other
                                //models data in it like newsheadline and source data
    String status;
    int totalResults;
    List<NewsHeadline> articles;    //list of all headlines artical will store here

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<NewsHeadline> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsHeadline> articles) {
        this.articles = articles;
    }
}
