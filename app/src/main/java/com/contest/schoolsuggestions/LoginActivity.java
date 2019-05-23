package com.contest.schoolsuggestions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

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
    }
}
