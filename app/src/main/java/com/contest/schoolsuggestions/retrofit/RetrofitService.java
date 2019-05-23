package com.contest.schoolsuggestions.retrofit;

import com.contest.schoolsuggestions.model.RegisterUserTO;
import com.contest.schoolsuggestions.model.UserInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetrofitService {
    @Headers({"Content-Type:application/json"})

    @POST("/users")
    Call<UserInfo> registerUser(@Body RegisterUserTO registerUserTO);
}
