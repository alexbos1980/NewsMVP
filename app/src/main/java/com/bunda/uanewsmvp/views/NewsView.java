package com.bunda.uanewsmvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.bunda.uanewsmvp.models.NewsData;

import java.util.List;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface NewsView extends MvpView {
    void showError(int textResource);
    void startLoadNews();
    void endLoadNews();
    void showLoadingNewsList(List<NewsData> newsDataList);
}
