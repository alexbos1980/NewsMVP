package com.bunda.uanewsmvp.ui.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.bunda.uanewsmvp.R;
import com.bunda.uanewsmvp.data.models.NewsData;
import com.bunda.uanewsmvp.data.providers.LoadNewsProvider;
import com.bunda.uanewsmvp.ui.views.NewsView;

import java.util.List;

@InjectViewState
public class NewsPresenters extends MvpPresenter<NewsView> {
    public void loadNewsUaTopHeadLine(){
        new LoadNewsProvider(this).loadNewsUaTopHeadline();
        getViewState().startLoadNews();
    }

    public void showLoadedNews(List<NewsData> allNews) {
        getViewState().endLoadNews();
        if(allNews.size()>0){
          getViewState().showLoadingNewsList(allNews);
        } else{
          getViewState().showError(R.string.error_message_loads_news);
        }
    }

    public void errorLoadedNews(int message) {
        getViewState().showError(message);
    }
}
