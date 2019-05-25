package com.contest.schoolsuggestions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.contest.schoolsuggestions.model.IssueInfo;
import com.contest.schoolsuggestions.model.IssueInfoTO;
import com.contest.schoolsuggestions.model.PostInfoTO;
import com.contest.schoolsuggestions.model.PostListViewAdapter;
import com.contest.schoolsuggestions.model.UserInfo;
import com.contest.schoolsuggestions.model.WriteIssueTO;
import com.contest.schoolsuggestions.retrofit.RetrofitManager;

import java.util.List;

public class PostListActivity extends AppCompatActivity implements RetrofitManager.SuccessGetIssueListener, RetrofitManager.SuccessGetPostListListener, RetrofitManager.SuccessWritePostListener {

    private List<PostInfoTO> postInfoList;
    private int btnStatus = 0;
    private IssueInfo issueInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);

        RetrofitManager.getInstance().setOnSuccessGetIssueListener(this);
        RetrofitManager.getInstance().setOnSuccessGetPostListener(this);
        RetrofitManager.getInstance().setOnSuccessWritePostListener(this);

        final UserInfo userInfo = (UserInfo) getIntent().getSerializableExtra("userInfo");
        final Button registerBtn = findViewById(R.id.registerBtn_postList);
        if (userInfo.getStudentInfo() == null) {
            btnStatus = 1;
            registerBtn.setText(R.string.registerIssueBtn_post);
        }

        final EditText issueEditText = findViewById(R.id.issueEditText_postList);
        final TextView issueTextView = findViewById(R.id.issueText_postList);
        RetrofitManager.getInstance().getIssue();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) registerBtn.getLayoutParams();
                switch (btnStatus) {
                    case 1:
                        registerBtn.setText(R.string.submitBtnText_postRegister);
                        issueTextView.setVisibility(View.GONE);
                        issueEditText.setVisibility(View.VISIBLE);
                        layoutParams.addRule(RelativeLayout.BELOW, issueEditText.getId());
                        registerBtn.setLayoutParams(layoutParams);
                        btnStatus = 2;
                        break;
                    case 2:
                        registerBtn.setText(R.string.registerIssueBtn_post);
                        issueTextView.setVisibility(View.VISIBLE);
                        issueEditText.setVisibility(View.GONE);
                        layoutParams.addRule(RelativeLayout.BELOW, issueTextView.getId());
                        registerBtn.setLayoutParams(layoutParams);
                        btnStatus = 1;
                        RetrofitManager.getInstance().writeIssue(new WriteIssueTO(issueEditText.getText().toString()));
                        break;
                    default:
                        if (issueInfo.getId() > 0L) {
                            Intent intentPostRegister = new Intent(PostListActivity.this, PostRegisterActivity.class);
                            intentPostRegister.putExtra("issueInfo", issueInfo);
                            intentPostRegister.putExtra("userInfo", userInfo);
                            startActivity(intentPostRegister);
                        } else {
                            Toast.makeText(GlobalApplication.getGlobalContext(), R.string.issueTitleText_postList, Toast.LENGTH_LONG).show();
                        }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        RetrofitManager.getInstance().removeSuccessGetIssueListener();
        RetrofitManager.getInstance().removeSuccessGetPostListener();
        RetrofitManager.getInstance().removeSuccessWritePostListener();
        super.onDestroy();
    }

    @Override
    public void onSuccessGetIssue(IssueInfoTO issueInfoTO) {
        if (issueInfoTO != null && !issueInfoTO.getTitle().equals("")) {
            ((TextView) findViewById(R.id.issueText_postList)).setText(issueInfoTO.getTitle());
            issueInfo = new IssueInfo(issueInfoTO.getId(), issueInfoTO.getTitle());
            RetrofitManager.getInstance().getPostList(issueInfo.getId());
        }
    }

    @Override
    public void onSuccessGetPostList(List<PostInfoTO> postInfoTOList) {
        ListView postListView = findViewById(R.id.listView_post);
        this.postInfoList = postInfoTOList;
        postListView.setAdapter(new PostListViewAdapter(postInfoTOList));
    }

    @Override
    public void onSuccessWritePost(PostInfoTO postInfoTO) {
        postInfoTO.setAgree(0);
        postInfoTO.setDisagree(0);
        this.postInfoList.add(0, postInfoTO);
        ListView postListView = findViewById(R.id.listView_post);
        PostListViewAdapter adapter = (PostListViewAdapter) postListView.getAdapter();
        adapter.notifyDataSetChanged();
    }
}
