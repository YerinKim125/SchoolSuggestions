package com.contest.schoolsuggestions.retrofit;

import android.util.Log;
import android.widget.Toast;

import com.contest.schoolsuggestions.GlobalApplication;
import com.contest.schoolsuggestions.R;
import com.contest.schoolsuggestions.model.IssueInfoTO;
import com.contest.schoolsuggestions.model.LoginTO;
import com.contest.schoolsuggestions.model.RegisterUserTO;
import com.contest.schoolsuggestions.model.UserInfo;
import com.contest.schoolsuggestions.model.WriteIssueTO;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    public interface SuccessRegisterListener {
        void onSuccessRegister(UserInfo userInfo);
    }

    public interface SuccessLoginListener {
        void onSuccessLogin(UserInfo userInfo);
    }

    public interface SuccessGetIssueListener {
        void onSuccessGetIssue(IssueInfoTO issueInfoTO);
    }

    private static String TAG = "Retrofit";
    //    final private String requestURL = "http://ec2-54-180-93-44.ap-northeast-2.compute.amazonaws.com:8080";
    final private String requestURL = "http://10.0.2.2:8080";
    private static RetrofitManager retrofitManager;
    private Retrofit retrofit;
    private RetrofitService service;
    private SuccessRegisterListener mSuccessRegisterListener;
    private SuccessLoginListener mSuccessLoginListener;
    private SuccessGetIssueListener mSuccessGetIssueListener;

    private RetrofitManager() {
        retrofit = new Retrofit.Builder().baseUrl(requestURL).addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create())).build();
        service = retrofit.create(RetrofitService.class);
    }

    public static RetrofitManager getInstance() {
        if (retrofitManager == null) {
            retrofitManager = new RetrofitManager();
        }
        return retrofitManager;
    }

    public void setOnSuccessRegisterListener(SuccessRegisterListener mSuccessRegisterListener) {
        this.mSuccessRegisterListener = mSuccessRegisterListener;
    }

    public void setOnSuccessLoginListener(SuccessLoginListener mSuccessLoginListener) {
        this.mSuccessLoginListener = mSuccessLoginListener;
    }

    public void setOnSuccessGetIssueListener(SuccessGetIssueListener mSuccessGetIssueListener) {
        this.mSuccessGetIssueListener = mSuccessGetIssueListener;
    }

    public void removeSuccessRegisterListener() {
        this.mSuccessRegisterListener = null;
    }

    public void removeSuccessLoginListener() {
        this.mSuccessLoginListener = null;
    }

    public void removeSuccessGetIssueListener() {
        this.mSuccessGetIssueListener = null;
    }

    private void showToast(int message) {
        Toast.makeText(GlobalApplication.getGlobalContext(), message, Toast.LENGTH_LONG).show();
    }

    private void logBadResponse(int errorCode, String errorMessage, String methodName) {
        Log.e(TAG, methodName + " Error Code: " + errorCode);
        Log.e(TAG, methodName + ": " + errorMessage);
    }

    private void logConnectionFailure(String errorMessage, String methodName) {
        showToast(R.string.connection_failure_message);
        Log.e(TAG, methodName + ": " + errorMessage);
    }

    public void registerUser(RegisterUserTO registerUserTO) {
        final String methodName = "registerUser";
        Call<UserInfo> req = service.registerUser(registerUserTO);
        req.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if (response.isSuccessful()) {
                    showToast(R.string.success_register_message);
                    if (mSuccessRegisterListener != null) {
                        mSuccessRegisterListener.onSuccessRegister(response.body());
                    }
                } else {
                    logBadResponse(response.code(), response.errorBody().toString(), methodName);
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                logConnectionFailure(t.getMessage(), methodName);
            }
        });
    }

    public void login(LoginTO loginTO) {
        final String methodName = "login";
        Call<UserInfo> req = service.login(loginTO);
        req.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if (response.isSuccessful()) {
                    showToast(R.string.success_login_message);
                    if (mSuccessLoginListener != null) {
                        mSuccessLoginListener.onSuccessLogin(response.body());
                    }
                } else {
                    showToast(R.string.fail_login_message);
                    logBadResponse(response.code(), response.errorBody().toString(), methodName);
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                logConnectionFailure(t.getMessage(), methodName);
            }
        });
    }

    public void getIssue() {
        final String methodName = "getIssue";
        Call<IssueInfoTO> req = service.getIssue();
        req.enqueue(new Callback<IssueInfoTO>() {
            @Override
            public void onResponse(Call<IssueInfoTO> call, Response<IssueInfoTO> response) {
                if (response.isSuccessful()) {
                    if (mSuccessGetIssueListener != null) {
                        mSuccessGetIssueListener.onSuccessGetIssue(response.body());
                    }
                } else {
                    showToast(R.string.fail_getIssue_message);
                    logBadResponse(response.code(), response.errorBody().toString(), methodName);
                }
            }

            @Override
            public void onFailure(Call<IssueInfoTO> call, Throwable t) {
                logConnectionFailure(t.getMessage(), methodName);
            }
        });
    }

    public void writeIssue(WriteIssueTO writeIssueTO) {
        final String methodName = "writeIssue";
        Call<IssueInfoTO> req = service.writeIssue(writeIssueTO);
        req.enqueue(new Callback<IssueInfoTO>() {
            @Override
            public void onResponse(Call<IssueInfoTO> call, Response<IssueInfoTO> response) {
                if (response.isSuccessful()) {
                    showToast(R.string.success_writeIssue_message);
                    if (mSuccessGetIssueListener != null) {
                        mSuccessGetIssueListener.onSuccessGetIssue(response.body());
                    }
                } else {
                    showToast(R.string.fail_writeIssue_message);
                    logBadResponse(response.code(), response.errorBody().toString(), methodName);
                }
            }

            @Override
            public void onFailure(Call<IssueInfoTO> call, Throwable t) {
                logConnectionFailure(t.getMessage(), methodName);
            }
        });
    }
}
