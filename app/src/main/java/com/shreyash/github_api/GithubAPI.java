package com.shreyash.github_api;

import com.shreyash.github_api.models.GitHubApiResponse;
import com.shreyash.github_api.models.GithubRepoResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface GithubAPI {

    @GET("search/{shreyash}")
    Call<GitHubApiResponse> listUser(@Path("shreyash") String user);

    @GET
    Call<GitHubApiResponse> userList(@Url String url);


    @GET("users/{user}/repos")
    Call<List<GithubRepoResponse>> viewRepos(@Path("user") String user);
}
