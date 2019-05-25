package com.contest.schoolsuggestions.retrofit;

import com.contest.schoolsuggestions.model.IssueInfoTO;
import com.contest.schoolsuggestions.model.LoginTO;
import com.contest.schoolsuggestions.model.PostInfoTO;
import com.contest.schoolsuggestions.model.RegisterUserTO;
import com.contest.schoolsuggestions.model.UserInfo;
import com.contest.schoolsuggestions.model.WriteIssueTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

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

    @GET("/issues/{id}")
    Call<List<PostInfoTO>> getPostList(@Query("id") Long issueId);
}
