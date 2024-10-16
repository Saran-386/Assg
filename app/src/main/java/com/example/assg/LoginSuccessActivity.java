package com.example.assg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginSuccessActivity extends AppCompatActivity {

    private Button generateReportButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);

        // Initialize the TextView and Button
        TextView successMessage = findViewById(R.id.successMessage);
        generateReportButton = findViewById(R.id.generateReportButton);

        // Set the login successful message
        successMessage.setText("Login Successful!");

        // Button to go to the Report Generation Activity
        generateReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginSuccessActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });
    }
}
