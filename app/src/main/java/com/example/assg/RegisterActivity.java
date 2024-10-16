package com.example.assg;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText staffName = findViewById(R.id.editStaffName);
        EditText staffId = findViewById(R.id.editStaffId);
        EditText subjectName = findViewById(R.id.editSubjectName);
        EditText subjectCode = findViewById(R.id.editSubjectCode);
        EditText password = findViewById(R.id.editPassword);

        findViewById(R.id.buttonSubmit).setOnClickListener(v -> {
            if (validateInput(staffName, staffId, subjectName, subjectCode, password)) {
                // Store details in database or shared preferences
                // Example: storeUserData();
                Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.buttonBack).setOnClickListener(v -> finish());
    }

    private boolean validateInput(EditText staffName, EditText staffId, EditText subjectName,
                                  EditText subjectCode, EditText password) {
        // Add validation logic for username and password
        return true; // Return true if valid
    }
}
