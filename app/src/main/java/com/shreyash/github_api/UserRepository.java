package com.shreyash.github_api;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.shreyash.github_api.models.APIUsers;
import com.shreyash.github_api.models.UsersApiResponse;
import com.shreyash.github_api.room.UsersDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    MutableLiveData<UsersApiResponse> usersApiResponseMutableLiveData;
    MutableLiveData<String> error;
    LiveData<List<APIUsers>> apiUsersLiveData;
    USERAPI USERAPI;



    UsersDatabase usersDatabase;
    Application application;

    public UserRepository(Application application) {
        this.application = application;
        usersApiResponseMutableLiveData = new MutableLiveData<>();

        USERAPI = ConfigRetrofit.configRetrofit(USERAPI.class);
        usersDatabase = UsersDatabase.getDatabase(application);
        error = new MutableLiveData<>();

        apiUsersLiveData = usersDatabase.daoUser().getUsers();

    }

    public LiveData<List<APIUsers>> getApiUsersLiveData() {
        return apiUsersLiveData;
    }

    public MutableLiveData<String> getError() {
        return error;
    }

    public LiveData<List<APIUsers>>  getGitHubApiResponseMutableLiveData(int page) {
        USERAPI.listUser(page, "5").enqueue(new Callback<UsersApiResponse>() {
            @Override
            public void onResponse(Call<UsersApiResponse> call, Response<UsersApiResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getData() != null && page<=response.body().getPage()) {
                        if (response.body().getData().size() > 0 ) {
                            if (page == 1 ) {
                                usersDatabase.daoUser().delete();
                                //usersApiResponseMutableLiveData.setValue(response.body());
                            }
                            usersDatabase.daoUser().addUsers(response.body().getData());
                        } else
                            error.setValue("No Result Found");
                    } else {

                        error.setValue("Something went wrong");
                    }
                } else error.setValue("Something went wrong");
            }

            @Override
            public void onFailure(Call<UsersApiResponse> call, Throwable t) {
                error.setValue("connection error");
            }
        });
        return apiUsersLiveData;
    }

    public MutableLiveData<UsersApiResponse> getUsersApiResponseMutableLiveData() {
        return usersApiResponseMutableLiveData;
    }


}
