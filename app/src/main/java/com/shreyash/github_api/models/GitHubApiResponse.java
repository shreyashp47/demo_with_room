package com.shreyash.github_api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GitHubApiResponse {

    @SerializedName("total_count")
    @Expose
    private Integer totalCount;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("incomplete_results")
    @Expose
    private Boolean incompleteResults;
    @SerializedName("items")
    @Expose
    private List<GithubUsers> items = null;

    public Integer getTotalCount() {
        return totalCount;
    }

    public Boolean getIncompleteResults() {
        return incompleteResults;
    }

    public List<GithubUsers> getItems() {
        return items;
    }
}
