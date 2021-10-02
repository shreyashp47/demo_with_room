package com.shreyash.github_api;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.shreyash.github_api.models.GitHubApiResponse;
import com.shreyash.github_api.models.GithubRepoResponse;

import java.util.List;

public class GithubViewModel extends AndroidViewModel {
    MutableLiveData<GitHubApiResponse> gitHubApiResponseMutableLiveData;
    MutableLiveData<String> error;
    MutableLiveData<List<GithubRepoResponse>> listMutableLiveData;
    GithubRepository githubRepository;


    public GithubViewModel(Application application) {
        super(application);
        gitHubApiResponseMutableLiveData = new MutableLiveData<>();
        githubRepository = new GithubRepository(this.getApplication());
        error = githubRepository.getError();



    }


    public MutableLiveData<GitHubApiResponse> getGitHubApiResponseMutableLiveData(String user) {
        gitHubApiResponseMutableLiveData = githubRepository.getGitHubApiResponseMutableLiveData(user);
        return gitHubApiResponseMutableLiveData;
    }

    public MutableLiveData<GitHubApiResponse> getGitHubApiResponseMutableLiveData() {
        gitHubApiResponseMutableLiveData= githubRepository.getGitHubApiResponseMutableLiveData();
        return gitHubApiResponseMutableLiveData;
    }
    public MutableLiveData<String> getError() {
        error= githubRepository.getError();
        return error;
    }

    public MutableLiveData<List<GithubRepoResponse>> getListMutableLiveData(String user) {
        listMutableLiveData = githubRepository.getListMutableLiveData(user);
        return listMutableLiveData;
    }

    public MutableLiveData<List<GithubRepoResponse>> getListMutableLiveData() {
        listMutableLiveData= githubRepository.getListMutableLiveData();
        return listMutableLiveData;
    }
}
