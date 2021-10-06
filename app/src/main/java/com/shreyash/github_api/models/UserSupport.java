package com.shreyash.github_api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserSupport {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("text")
    @Expose
    private String text;

    public String getUrl() {
        return url;
    }

    public String getText() {
        return text;
    }
}
