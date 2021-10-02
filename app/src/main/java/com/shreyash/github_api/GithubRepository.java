package com.shreyash.github_api;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.shreyash.github_api.models.GitHubApiResponse;
import com.shreyash.github_api.models.GithubRepoResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubRepository {
    MutableLiveData<GitHubApiResponse> gitHubApiResponseMutableLiveData;
    MutableLiveData<String> error;
    MutableLiveData<List<GithubRepoResponse>> listMutableLiveData;
    GithubAPI githubAPI;


    Application application;

    public GithubRepository(Application application) {
        this.application = application;
        gitHubApiResponseMutableLiveData = new MutableLiveData<>();
        listMutableLiveData = new MutableLiveData<>();
        githubAPI = ConfigRetrofit.configRetrofit(GithubAPI.class);

        error = new MutableLiveData<>();

    }

    public MutableLiveData<String> getError() {
        return error;
    }

    public MutableLiveData<GitHubApiResponse> getGitHubApiResponseMutableLiveData(String user) {
        githubAPI.userList("https://api.github.com/search/users?q=" + user).enqueue(new Callback<GitHubApiResponse>() {
            @Override
            public void onResponse(Call<GitHubApiResponse> call, Response<GitHubApiResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getItems() != null) {
                        if (response.body().getItems().size() > 0)
                            gitHubApiResponseMutableLiveData.setValue(response.body());
                        else
                            error.setValue("No Result Found");
                    } else {
                        if (response.body().message != null)
                            error.setValue(response.body().message);
                        else
                            error.setValue("Something went wrong");
                    }
                } else error.setValue("Something went wrong");
            }

            @Override
            public void onFailure(Call<GitHubApiResponse> call, Throwable t) {
                error.setValue("Something went wrong");
            }
        });
        return gitHubApiResponseMutableLiveData;
    }

    public MutableLiveData<GitHubApiResponse> getGitHubApiResponseMutableLiveData() {
        return gitHubApiResponseMutableLiveData;
    }

    public MutableLiveData<List<GithubRepoResponse>> getListMutableLiveData() {
        return listMutableLiveData;
    }

    public MutableLiveData<List<GithubRepoResponse>> getListMutableLiveData(String user) {

        githubAPI.viewRepos(user).enqueue(new Callback<List<GithubRepoResponse>>() {
            @Override
            public void onResponse(Call<List<GithubRepoResponse>> call, Response<List<GithubRepoResponse>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null)
                        listMutableLiveData.setValue(response.body());
                    else
                        error.setValue("Something went wrong");
                } else error.setValue("Something went wrong");
            }

            @Override
            public void onFailure(Call<List<GithubRepoResponse>> call, Throwable t) {
                error.setValue("Something went wrong");
            }
        });
        return listMutableLiveData;
    }
}
