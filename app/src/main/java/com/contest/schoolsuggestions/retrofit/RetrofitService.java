package com.contest.schoolsuggestions.retrofit;

import com.contest.schoolsuggestions.model.IssueInfoTO;
import com.contest.schoolsuggestions.model.LoginTO;
import com.contest.schoolsuggestions.model.RegisterUserTO;
import com.contest.schoolsuggestions.model.UserInfo;
import com.contest.schoolsuggestions.model.WriteIssueTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetrofitService {
    @Headers({"Content-Type:application/json"})

    @POST("/users")
    Call<UserInfo> registerUser(@Body RegisterUserTO registerUserTO);

    @POST("/users/login")
    Call<UserInfo> login(@Body LoginTO loginTO);

    @GET("/issues")
    Call<IssueInfoTO> getIssue();

    @POST("/issues")
    Call<IssueInfoTO> writeIssue(@Body WriteIssueTO writeIssueTO);
}
