
package com.example.namiacosmeticsproject.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.namiacosmeticsproject.R;

public class LoginActivity extends AppCompatActivity {

    TextView txtForget , txtSignUp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtForget = findViewById(R.id.txt_forget);
        txtSignUp = findViewById(R.id.txt_sign_up);

        txtForget.setOnClickListener(v -> {
            Toast.makeText(this, "I forget my password 😫", Toast.LENGTH_SHORT).show();
        });

        txtSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(this , SignUpActivity.class);
            startActivity(intent);
        });
    }
}