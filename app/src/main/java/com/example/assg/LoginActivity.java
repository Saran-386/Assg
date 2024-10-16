package com.example.assg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText staffId, password;
    private Button loginButton, backButton;

    // SharedPreferences file name
    private static final String SHARED_PREFS = "staffLoginPrefs";
    private static final String STAFF_ID = "staffId";
    private static final String PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize input fields
        staffId = findViewById(R.id.staffId);
        password = findViewById(R.id.password);

        // Initialize login and back buttons
        loginButton = findViewById(R.id.loginButton);
        backButton = findViewById(R.id.backButton);

        // Add some default credentials for testing (you can remove this after testing)
        storeDefaultCredentials();

        // Login button logic
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String staffIdInput = staffId.getText().toString().trim();
                String passwordInput = password.getText().toString().trim();

                // In LoginActivity.java, inside onClick of loginButton

                if (validateLogin(staffIdInput, passwordInput)) {
                    Intent intent = new Intent(LoginActivity.this, LoginSuccessActivity.class);
                    startActivity(intent);
                } else {
                    // Show error message
                    Toast.makeText(LoginActivity.this, "Invalid login credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Back button logic
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // End the current activity and go back to the previous screen
            }
        });
    }

    // Method to validate the login
    private boolean validateLogin(String staffIdInput, String passwordInput) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        // Retrieve stored staff ID and password
        String storedStaffId = sharedPreferences.getString(STAFF_ID, "");
        String storedPassword = sharedPreferences.getString(PASSWORD, "");

        // Check if the input matches the stored credentials
        return storedStaffId.equals(staffIdInput) && storedPassword.equals(passwordInput);
    }

    // Method to store default credentials (for testing purposes)
    private void storeDefaultCredentials() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Hard-code default credentials
        editor.putString(STAFF_ID, "staff123");
        editor.putString(PASSWORD, "password123");

        // Apply changes
        editor.apply();
    }
}
