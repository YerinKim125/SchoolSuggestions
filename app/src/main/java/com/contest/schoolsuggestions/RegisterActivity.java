package com.contest.schoolsuggestions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.contest.schoolsuggestions.model.RegisterUserTO;
import com.contest.schoolsuggestions.model.UserInfo;
import com.contest.schoolsuggestions.retrofit.RetrofitManager;

public class RegisterActivity extends AppCompatActivity implements RetrofitManager.SuccessRegisterListener {

    private boolean isStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final TextView studentTextView = findViewById(R.id.studentText_register);
        final TextView teacherTextView = findViewById(R.id.teacherText_register);
        final LinearLayout studentInfoLayout = findViewById(R.id.studentInfoLayout_register);
        final TextView codeTextView = findViewById(R.id.codeEditText_register);

        isStudent = true;
        studentTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentTextView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
                teacherTextView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorGray));
                studentInfoLayout.setVisibility(View.VISIBLE);
                codeTextView.setVisibility(View.GONE);
                isStudent = true;
            }
        });
        teacherTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentTextView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorGray));
                teacherTextView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
                studentInfoLayout.setVisibility(View.GONE);
                codeTextView.setVisibility(View.VISIBLE);
                isStudent = false;
            }
        });

        RetrofitManager.getInstance().setOnSuccessRegisterListener(this);

        Button registerUserBtn = findViewById(R.id.submitButton_register);
        registerUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ((EditText) findViewById(R.id.emailEditText_register)).getText().toString();
                String password = ((EditText) findViewById(R.id.passwordEditText_register)).getText().toString();
                String name = ((EditText) findViewById(R.id.nameEditText_register)).getText().toString();
                if (isStudent) {
                    int studentGrade = Integer.parseInt(((EditText) findViewById(R.id.gradeEditText_register)).getText().toString());
                    int studentClass = Integer.parseInt(((EditText) findViewById(R.id.classEditText_register)).getText().toString());
                    int studentNum = Integer.parseInt(((EditText) findViewById(R.id.studentNumEditText_register)).getText().toString());
                    String studentInfo = String.valueOf(studentGrade * 10000 + studentClass * 100 + studentNum);
                    RetrofitManager.getInstance().registerUser(new RegisterUserTO(email, password, name, studentInfo));
                } else {
                    String code = ((EditText) findViewById(R.id.codeEditText_register)).getText().toString();
                    if (code.equals(getString(R.string.teacher_code))) {
                        RetrofitManager.getInstance().registerUser(new RegisterUserTO(email, password, name, null));
                    } else {
                        Toast.makeText(GlobalApplication.getGlobalContext(), R.string.code_incorrect_message, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        findViewById(R.id.cancelButton_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(RegisterActivity.this, MainActivity.class);
                intentMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intentMain.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intentMain);
            }
        });
    }

    @Override
    public void onSuccessRegister(UserInfo userInfo) {
        Intent intentPostList = new Intent(RegisterActivity.this, PostListListActivity.class);
        intentPostList.putExtra("userInfo", userInfo);
        startActivity(intentPostList);
    }

    @Override
    protected void onDestroy() {
        RetrofitManager.getInstance().removeSuccessRegisterListener();
        super.onDestroy();
    }
}
