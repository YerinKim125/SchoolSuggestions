package com.contest.schoolsuggestions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.contest.schoolsuggestions.model.IssueInfo;
import com.contest.schoolsuggestions.model.UserInfo;
import com.contest.schoolsuggestions.model.WritePostTO;
import com.contest.schoolsuggestions.retrofit.RetrofitManager;

public class PostRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_register);

        final IssueInfo issueInfo = (IssueInfo) getIntent().getSerializableExtra("issueInfo");
        if (issueInfo.getId() > 0L) {
            TextView issueTextView = findViewById(R.id.issueText_postRegister);
            issueTextView.setText(issueInfo.getTitle());
        }

        findViewById(R.id.submitBtn_postRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfo userInfo = (UserInfo) getIntent().getSerializableExtra("userInfo");
                String postTitle = ((EditText) findViewById(R.id.titleEditText_postRegister)).getText().toString();
                String postContent = ((EditText) findViewById(R.id.contentEditText_postRegister)).getText().toString();
                RetrofitManager.getInstance().writePost(issueInfo.getId(), new WritePostTO(userInfo.getId(), postTitle, postContent));
                Intent intentPostList = new Intent(PostRegisterActivity.this, PostListActivity.class);
                intentPostList.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intentPostList.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intentPostList);
            }
        });
    }
}
