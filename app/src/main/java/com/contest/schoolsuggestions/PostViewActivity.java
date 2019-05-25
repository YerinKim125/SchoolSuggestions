package com.contest.schoolsuggestions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.contest.schoolsuggestions.model.IssueInfo;
import com.contest.schoolsuggestions.model.PostInfo;
import com.contest.schoolsuggestions.model.UserInfo;

public class PostViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_view);

        UserInfo userInfo = (UserInfo) getIntent().getSerializableExtra("userInfo");
        IssueInfo issueInfo = (IssueInfo) getIntent().getSerializableExtra("issueInfo");
        PostInfo postInfo = (PostInfo) getIntent().getSerializableExtra("postInfo");

        TextView issueTextView = findViewById(R.id.issueText_postView);
        TextView titleTextView = findViewById(R.id.titleText_postView);
        TextView contentTextView = findViewById(R.id.contentText_postView);
        TextView agreeTextView = findViewById(R.id.agreeTextView_postView);
        TextView disagreeTextView = findViewById(R.id.disagreeTextView_postView);
        TextView feedbackTextView = findViewById(R.id.feedBackText_postView);
        EditText feedbackEditText = findViewById(R.id.feedBackEditText_postView);

        issueTextView.setText(issueInfo.getTitle());
        titleTextView.setText(postInfo.getContent());
        contentTextView.setText(postInfo.getContent());
        agreeTextView.setText(String.valueOf(postInfo.getAgree()));
        disagreeTextView.setText(String.valueOf(postInfo.getDisagree()));
        if (postInfo.getFeedback() != null && !postInfo.getFeedback().equals("")) {
            feedbackTextView.setText(postInfo.getFeedback());
            feedbackEditText.setText(postInfo.getFeedback());
        }

        Button submitBtn = findViewById(R.id.submitBtn_postView);
        if (userInfo.getStudentInfo() == null || userInfo.getStudentInfo().equals("")) {
            feedbackTextView.setVisibility(View.GONE);
            feedbackEditText.setVisibility(View.VISIBLE);
            submitBtn.setVisibility(View.VISIBLE);
        }
    }
}
