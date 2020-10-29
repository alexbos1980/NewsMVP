package com.bunda.uanewsmvp.network;

import com.arellomobile.mvp.MvpView;
import com.bunda.uanewsmvp.models.NewsData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi{
    @GET("top-headlines")
    Call<NewsData> getTopData(
            @Query("country")String countryName,
            @Query("pageSize") int pageSize,
            @Query("apiKey") String apiKey);
}
