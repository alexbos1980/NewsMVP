package com.bunda.uanewsmvp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NewsData {
    @SerializedName("articles")
    public List<NewsData> allNews;

    @SerializedName("source")
    @Expose
    private SourceData source;
    @SerializedName("author")
    @Expose
    private Object author;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("urlToImage")
    @Expose
    private String urlToImage;
    @SerializedName("publishedAt")
    @Expose
    private Date publishedAt;
    @SerializedName("content")
    @Expose
    private String content;

    public NewsData (SourceData source, Object author, String title, String description, String url, String urlToImage, Date publishedAt, String content) {
        super();
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    public SourceData getSource() {
        return source;
    }

    public void setSource(SourceData source) {
        this.source = source;
    }

    public NewsData withSource(SourceData source) {
        this.source = source;
        return this;
    }

    public Object getAuthor() {
        return author;
    }

    public void setAuthor(Object author) {
        this.author = author;
    }

    public NewsData withAuthor(Object author) {
        this.author = author;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public NewsData withTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NewsData withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public NewsData withUrl(String url) {
        this.url = url;
        return this;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public NewsData withUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
        return this;
    }

    public String getPublishedAt(){
        Date publishedTime = publishedAt;

        SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        String newTime = dateFormatter.format(publishedTime);

        return newTime;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public NewsData withPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
        return this;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NewsData withContent(String content) {
        this.content = content;
        return this;
    }
}
