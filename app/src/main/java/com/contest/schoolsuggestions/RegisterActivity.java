package com.contest.schoolsuggestions;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final TextView studentTextView = findViewById(R.id.studentText_register);
        final TextView teacherTextView = findViewById(R.id.teacherText_register);
        final LinearLayout studentInfoLayout = findViewById(R.id.studentInfoLayout_register);
        final TextView codeTextView = findViewById(R.id.codeText_register);

        studentTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentTextView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
                teacherTextView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorGray));
                studentInfoLayout.setVisibility(View.VISIBLE);
                codeTextView.setVisibility(View.GONE);
            }
        });
        teacherTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentTextView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorGray));
                teacherTextView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
                studentInfoLayout.setVisibility(View.GONE);
                codeTextView.setVisibility(View.VISIBLE);
            }
        });
    }
}
