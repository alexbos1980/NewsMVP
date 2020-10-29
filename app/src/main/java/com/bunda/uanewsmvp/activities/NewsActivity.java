package com.bunda.uanewsmvp.activities;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bunda.uanewsmvp.R;
import com.bunda.uanewsmvp.adapters.NewsAdapter;
import com.bunda.uanewsmvp.adapters.OnItemClickListener;
import com.bunda.uanewsmvp.models.NewsData;
import com.bunda.uanewsmvp.presenters.NewsPresenters;
import com.bunda.uanewsmvp.views.NewsView;

import java.util.List;

public class NewsActivity extends MvpAppCompatActivity implements NewsView, OnItemClickListener {
    private static final String TAG = "NewsActivity: ";
    private RecyclerView recyclerViewNews;
    private ProgressBar progressBarWidget;
    private TextView textViewNoNews;
    private Toolbar toolbar;
    private NewsAdapter newsAdapter;
    private androidx.appcompat.widget.SearchView searchView;

    @InjectPresenter
    NewsPresenters presenters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        // Initialise view and recyclerView, adapters
        initNewsActivity();
        // Start load data from server by LoadNewsProvider
        presenters.loadNewsUaTopHeadLine();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Set searching on SearchView
        startSearchFieldListener();
    }

    @Override
    public void showNewsOnClick(NewsData newsData) { //Open news in browser by click ImageButton
        if(!newsData.getUrl().isEmpty()){
            Uri uriNews = Uri.parse(newsData.getUrl());
            startBrowserActivity(uriNews);
        } else showError(R.string.error_message_miss_url);
    }

    @Override
    public void showError(int textResource) { // Look error message if something wrong
        recyclerViewNews.setVisibility(View.GONE);
        searchView.setVisibility(View.GONE);
        toolbar.setVisibility(View.GONE);
        textViewNoNews.setVisibility(View.VISIBLE);
        progressBarWidget.setVisibility(View.GONE);

        textViewNoNews.setText(textResource);
    }

    @Override
    public void startLoadNews() { // Show run progressbar when data loads from server
        recyclerViewNews.setVisibility(View.GONE);
        toolbar.setVisibility(View.GONE);
        searchView.setVisibility(View.GONE);
        textViewNoNews.setVisibility(View.GONE);
        progressBarWidget.setVisibility(View.VISIBLE);
    }

    @Override
    public void endLoadNews() { // Stop progressbar when data success loads
        progressBarWidget.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingNewsList(List<NewsData> newsDataList) { // Show loading news in RV
        toolbar.setVisibility(View.VISIBLE);
        searchView.setVisibility(View.VISIBLE);
        recyclerViewNews.setVisibility(View.VISIBLE);
        textViewNoNews.setVisibility(View.GONE);
        progressBarWidget.setVisibility(View.GONE);

        newsAdapter.setShowNewsData(newsDataList);
    }

    private void initNewsActivity(){ //Collection init view, RV, adapters
        searchView = findViewById(R.id.sv_news_search);
        recyclerViewNews = findViewById(R.id.rv_news_list);
        progressBarWidget = findViewById(R.id.pb_load_news_data);
        textViewNoNews = findViewById(R.id.tv_no_news_loads);
        toolbar = findViewById(R.id.toolbar);

        newsAdapter = new NewsAdapter();
        newsAdapter.setOnClickListener(this);

        recyclerViewNews.setAdapter(newsAdapter);
        recyclerViewNews.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerViewNews.setHasFixedSize(true);
    }

    private void startBrowserActivity(Uri uri){ // Send Uri for open in installing internet browser
        Intent intentLoads = new Intent(Intent.ACTION_VIEW, uri);
        if (intentLoads.resolveActivity(getPackageManager()) != null){
            startActivity(intentLoads);
        }
        else {
            Toast.makeText(this, getResources().getString(R.string.error_message_miss_application), Toast.LENGTH_LONG).show();
        }
    }

    private void startSearchFieldListener(){ // Send input char in adapter for filtering and show result
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            Log.i(TAG, "onQueryTextSubmit: " + query);
            newsAdapter.getFilter().filter(query);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            Log.i(TAG, "onQueryTextChange: " + newText);
            newsAdapter.getFilter().filter(newText);
            return false;
        }
    });
  }
}