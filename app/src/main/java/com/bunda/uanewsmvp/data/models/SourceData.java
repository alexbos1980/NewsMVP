package com.bunda.uanewsmvp.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SourceData {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;

    public SourceData() {
    }

    public SourceData(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SourceData withId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SourceData withName(String name) {
        this.name = name;
        return this;
    }
}
