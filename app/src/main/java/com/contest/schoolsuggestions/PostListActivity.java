package com.contest.schoolsuggestions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.contest.schoolsuggestions.model.UserInfo;

public class PostListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);

        UserInfo userInfo = (UserInfo) getIntent().getSerializableExtra("userInfo");
    }
}
