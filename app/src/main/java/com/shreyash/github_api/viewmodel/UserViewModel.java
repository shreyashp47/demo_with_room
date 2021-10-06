package com.shreyash.github_api.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.shreyash.github_api.UserRepository;
import com.shreyash.github_api.models.APIUsers;
import com.shreyash.github_api.models.UsersApiResponse;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    MutableLiveData<UsersApiResponse> usersApiResponseMutableLiveData;
    MutableLiveData<String> error;
    LiveData<List<APIUsers>> apiUsersLiveData;
    UserRepository userRepository;


    public UserViewModel(Application application) {
        super(application);
        usersApiResponseMutableLiveData = new MutableLiveData<>();
        userRepository = new UserRepository(this.getApplication());
        error = userRepository.getError();

        apiUsersLiveData = userRepository.getApiUsersLiveData();

    }

    public LiveData<List<APIUsers>> getApiUsersLiveData(int page) {
        return apiUsersLiveData= userRepository.getGitHubApiResponseMutableLiveData(page);
    }

    public  LiveData<List<APIUsers>> getUsersApiResponse(int page) {
        apiUsersLiveData = userRepository.getGitHubApiResponseMutableLiveData(page);
        return apiUsersLiveData;
    }

    public MutableLiveData<UsersApiResponse> getUsersApiResponseMutableLiveData() {
        usersApiResponseMutableLiveData = userRepository.getUsersApiResponseMutableLiveData();
        return usersApiResponseMutableLiveData;
    }

    public MutableLiveData<String> getError() {
        error = userRepository.getError();
        return error;
    }


}
