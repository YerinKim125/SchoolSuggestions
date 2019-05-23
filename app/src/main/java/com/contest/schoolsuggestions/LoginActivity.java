package com.contest.schoolsuggestions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.contest.schoolsuggestions.model.LoginTO;
import com.contest.schoolsuggestions.model.UserInfo;
import com.contest.schoolsuggestions.retrofit.RetrofitManager;

public class LoginActivity extends AppCompatActivity implements RetrofitManager.SuccessLoginListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button cancelButton = findViewById(R.id.cancelButton_login);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(LoginActivity.this, MainActivity.class);
                intentMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intentMain.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intentMain);
            }
        });

        RetrofitManager.getInstance().setOnSuccessLoginListener(this);

        findViewById(R.id.submitButton_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ((EditText) findViewById(R.id.emailEditText_login)).getText().toString();
                String password = ((EditText) findViewById(R.id.passwordEditText_login)).getText().toString();

                RetrofitManager.getInstance().login(new LoginTO(email, password));
            }
        });
    }

    @Override
    public void onSuccessLogin(UserInfo userInfo) {
        //TODO
        Log.i(getClass().toString(), String.valueOf(userInfo.getId()) + " " + userInfo.getStudentInfo());
    }

    @Override
    protected void onDestroy() {
        RetrofitManager.getInstance().removeSuccessLoginListener();
        super.onDestroy();
    }
}
