package com.shubhankar.newsapp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.shubhankar.newsapp3.Models.NewsApiResponse;
import com.shubhankar.newsapp3.Models.NewsHeadline;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener , View. OnClickListener
{
    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog dialog;
    Button b1,b2,b3,b4,b5,b6,b7;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView=findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override

            //query for search

            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("fetching news articles of"+query);
                dialog.show();

                RequestManager manager= new RequestManager(MainActivity.this);
                manager.getNewsHeadlines(listner,"general",query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        dialog=new ProgressDialog(this);
        dialog.setTitle("fetching news articles.....");
        dialog.show();

        b1=findViewById(R.id.btn_1);
        b1.setOnClickListener(this);
        b2=findViewById(R.id.btn_2);
        b2.setOnClickListener(this);
        b3=findViewById(R.id.btn_3);
        b3.setOnClickListener(this);
        b4=findViewById(R.id.btn_4);
        b4.setOnClickListener(this);
        b5=findViewById(R.id.btn_5);
        b5.setOnClickListener(this);
        b6=findViewById(R.id.btn_6);
        b6.setOnClickListener(this);
        b7=findViewById(R.id.btn_7);
        b7.setOnClickListener(this);



        RequestManager manager= new RequestManager(this);
        manager.getNewsHeadlines(listner,"general",null);
        //which category of news we want to see
    }

    private final OnFetchDataListner<NewsApiResponse> listner=new OnFetchDataListner<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadline> list, String message) {

            if(list.isEmpty())
            {
                Toast.makeText(MainActivity.this, "No data found", Toast.LENGTH_SHORT).show();
            }
            else{
                showNews(list);//when the news will fetch then loading dialog will stop
                dialog.dismiss();
            }

        }

        @Override
        public void OnError(String message) {

            Toast.makeText(MainActivity.this,"An Error occured!!!",  Toast.LENGTH_SHORT).show();
        }
    };

    private void showNews(List<NewsHeadline> list) {
        recyclerView=findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        adapter=new CustomAdapter(this,list,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnNewsClicked(NewsHeadline headlines) {
        //when a user clicked on the news then it will take user to
        //diff activity which has details of the news
        startActivity(new Intent(MainActivity.this,DetailsActivity.class)
                .putExtra("data",headlines));
    }


    //on click of the categories it
    @Override
    public void onClick(View v) {

        Button button=(Button) v;
        String category= button.getText().toString();
        dialog.setTitle("fetching news articles"+category);
        dialog.show();
        RequestManager manager= new RequestManager(this);
        manager.getNewsHeadlines(listner,category,null);
    }
}
//inorder to manage our request we have to add retrofit library in dependency