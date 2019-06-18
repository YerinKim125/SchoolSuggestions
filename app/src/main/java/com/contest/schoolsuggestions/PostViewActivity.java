package com.contest.schoolsuggestions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.contest.schoolsuggestions.model.IssueInfo;
import com.contest.schoolsuggestions.model.PostInfo;
import com.contest.schoolsuggestions.model.PostInfoTO;
import com.contest.schoolsuggestions.model.UpdatePostTO;
import com.contest.schoolsuggestions.model.UserInfo;
import com.contest.schoolsuggestions.retrofit.RetrofitManager;

public class PostViewActivity extends AppCompatActivity implements RetrofitManager.SuccessUpdatePostListener {

    PostInfo postInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_view);

        RetrofitManager.getInstance().setOnSuccessUpdatePostListener(this);

        UserInfo userInfo = (UserInfo) getIntent().getSerializableExtra("userInfo");
        final IssueInfo issueInfo = (IssueInfo) getIntent().getSerializableExtra("issueInfo");
        postInfo = (PostInfo) getIntent().getSerializableExtra("postInfo");

        TextView issueTextView = findViewById(R.id.issueText_postView);
        TextView titleTextView = findViewById(R.id.titleText_postView);
        TextView contentTextView = findViewById(R.id.contentText_postView);
        TextView agreeTextView = findViewById(R.id.agreeTextView_postView);
        TextView disagreeTextView = findViewById(R.id.disagreeTextView_postView);
        TextView feedbackTextView = findViewById(R.id.feedBackText_postView);
        EditText feedbackEditText = findViewById(R.id.feedBackEditText_postView);

        issueTextView.setText(issueInfo.getTitle());
        titleTextView.setText(postInfo.getTitle());
        contentTextView.setText(postInfo.getContent());
        agreeTextView.setText(getString(R.string.agree_text_postView) + " : " + String.valueOf(postInfo.getAgree()));
        disagreeTextView.setText(getString(R.string.disagree_text_postView) + " : " + String.valueOf(postInfo.getDisagree()));
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

        agreeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdatePostTO updatePostTO = new UpdatePostTO(postInfo.getAgree() + 1, postInfo.getDisagree(), postInfo.getFeedback());
                RetrofitManager.getInstance().updatePost(issueInfo.getId(), postInfo.getId(), updatePostTO);
            }
        });

        disagreeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdatePostTO updatePostTO = new UpdatePostTO(postInfo.getAgree(), postInfo.getDisagree() + 1, postInfo.getFeedback());
                RetrofitManager.getInstance().updatePost(issueInfo.getId(), postInfo.getId(), updatePostTO);
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText feedbackEditText = findViewById(R.id.feedBackEditText_postView);
                UpdatePostTO updatePostTO = new UpdatePostTO(postInfo.getAgree(), postInfo.getDisagree(), feedbackEditText.getText().toString());
                RetrofitManager.getInstance().updatePost(issueInfo.getId(), postInfo.getId(), updatePostTO);
            }
        });
    }

    @Override
    public void onSuccessUpdatePost(PostInfoTO postInfoTO) {
        TextView agreeTextView = findViewById(R.id.agreeTextView_postView);
        TextView disagreeTextView = findViewById(R.id.disagreeTextView_postView);
        TextView feedbackTextView = findViewById(R.id.feedBackText_postView);
        EditText feedbackEditText = findViewById(R.id.feedBackEditText_postView);

        agreeTextView.setText(getString(R.string.agree_text_postView) + " : " + String.valueOf(postInfoTO.getAgree()));
        disagreeTextView.setText(getString(R.string.disagree_text_postView) + " : " + String.valueOf(postInfoTO.getDisagree()));
        if (postInfoTO.getFeedback() != null && !postInfoTO.getFeedback().equals("")) {
            feedbackTextView.setText(postInfoTO.getFeedback());
            feedbackEditText.setText(postInfoTO.getFeedback());
        }

        postInfo.setAgree(postInfoTO.getAgree());
        postInfo.setDisagree(postInfoTO.getDisagree());
        postInfo.setFeedback(postInfo.getFeedback());
    }

    @Override
    protected void onDestroy() {
        RetrofitManager.getInstance().removeSuccessUpdatePostListener();
        super.onDestroy();
    }
}
