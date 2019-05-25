package com.contest.schoolsuggestions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class PostRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_register);

        findViewById(R.id.submitBtn_postRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO post db
                Intent intentPostList = new Intent(PostRegisterActivity.this, PostListListActivity.class);
                intentPostList.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intentPostList.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intentPostList);
            }
        });
    }
}
