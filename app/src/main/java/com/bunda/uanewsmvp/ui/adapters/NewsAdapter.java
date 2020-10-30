package com.bunda.uanewsmvp.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bunda.uanewsmvp.R;
import com.bunda.uanewsmvp.data.models.NewsData;
import com.bunda.uanewsmvp.utillity.LoadImage;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> implements Filterable {
    private static final String TAG = "NewsAdapter ";
    private OnItemClickListener itemClickListener;
    private List<NewsData> newsDataList = new ArrayList<>();
    private List<NewsData> sortNewsItemList = new ArrayList<>();

    public void setOnClickListener(OnItemClickListener onClickListener) {
        itemClickListener = onClickListener;
    }

    public void setShowNewsData(List<NewsData> newsList){ //For realise search function need create two News list
        newsDataList.clear();
        sortNewsItemList.clear();
        newsDataList.addAll(newsList);
        sortNewsItemList.addAll(newsList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.bind(sortNewsItemList.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return sortNewsItemList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) { // Filtering char in NewsTitle
                String query = charSequence.toString();
                List<NewsData> filtered = new ArrayList<>();

                if (query.isEmpty()){
                    filtered = newsDataList;
                } else {
                    for (NewsData newsTitle : newsDataList){
                        if (newsTitle.getTitle().toLowerCase().contains(query.toLowerCase())){
                            filtered.add(newsTitle);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.count = filtered.size();
                results.values = filtered;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                sortNewsItemList = (ArrayList<NewsData>)filterResults.values; // Create sorted list and notify adapter
                notifyDataSetChanged();
            }
        };
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        private TextView newsTitle;
        private TextView newsSource;
        private TextView newsDescription;
        private TextView publishedAt;
        private ImageView imageButtonShowNews;
        private ImageView imageNews;
        private LoadImage loadImage = new LoadImage();

        public NewsViewHolder(@NonNull View itemView) { // Initialise view ia
            super(itemView);
            newsTitle = itemView.findViewById(R.id.tv_title_news);
            newsSource = itemView.findViewById(R.id.tv_source_news);
            newsDescription = itemView.findViewById(R.id.tv_description_news);
            publishedAt = itemView.findViewById(R.id.tv_date_time_news);
            imageButtonShowNews = itemView.findViewById(R.id.ibt_open_news);
            imageNews = itemView.findViewById(R.id.iv_image_news);
        }

        public void bind(NewsData newsData, OnItemClickListener itemClickListener) { //Bind data to cellNews
            if(newsData.getUrlToImage() != null) {
                    loadImage.loadImageInternet(itemView, newsData.getUrlToImage(), imageNews);
                } else imageNews.setVisibility(View.GONE);

            newsTitle.setText(newsData.getTitle());
            newsSource.setText(newsData.getSource().getName());
            newsDescription.setText(newsData.getDescription());
            publishedAt.setText(newsData.getPublishedAt());

            imageButtonShowNews.setOnClickListener(view -> {
               itemClickListener.showNewsOnClick(newsData);
            });

        }
    }
}
