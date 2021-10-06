package com.shreyash.github_api;

import com.shreyash.github_api.models.UsersApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface USERAPI {

    @GET("api/users")
    Call<UsersApiResponse> listUser(
            @Query("page") int page_num,
            @Query("per_page") String page_size);





}
