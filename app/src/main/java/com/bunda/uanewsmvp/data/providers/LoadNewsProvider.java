package com.bunda.uanewsmvp.data.providers;

import com.bunda.uanewsmvp.R;
import com.bunda.uanewsmvp.data.models.NewsData;
import com.bunda.uanewsmvp.data.network.NetworkService;
import com.bunda.uanewsmvp.data.network.NewsApi;
import com.bunda.uanewsmvp.ui.presenters.NewsPresenters;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadNewsProvider {
    private NewsPresenters newsPresenters;
    private String apiKey = "3a860d7395524c7b9519d1f00c5fd5d0";
    private String countryCode = "ua";
    private int pageShowNumber = 50;

    public LoadNewsProvider(NewsPresenters newsPresenters) {
        this.newsPresenters = newsPresenters;
    }

    public void loadNewsUaTopHeadline(){
        NewsApi newsApi = NetworkService.getApi();

        Call<NewsData> callNews = newsApi.getTopData(countryCode, pageShowNumber, apiKey);

        callNews.enqueue(new Callback<NewsData>() {
            @Override
            public void onResponse(Call<NewsData> call, Response<NewsData> response) {
                if(response.body()!=null){
                    newsPresenters.showLoadedNews(response.body().allNews);
                }
            }

            @Override
            public void onFailure(Call<NewsData> call, Throwable t) {
                    newsPresenters.errorLoadedNews(R.string.error_message_loads_news);
            }
        });
    }
}
