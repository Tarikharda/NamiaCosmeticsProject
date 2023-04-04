
package com.example.namiacosmeticsproject.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.namiacosmeticsproject.R;

public class LoginActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView txtForget , txtSignUp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar = findViewById(R.id.toolbar_login);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_black);
        getSupportActionBar().setTitle("");

        txtForget = findViewById(R.id.txt_forget);
        txtSignUp = findViewById(R.id.txt_sign_up);

        txtForget.setOnClickListener(v -> {
            Toast.makeText(this, "I forget my password 😫", Toast.LENGTH_SHORT).show();
        });

        txtSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(this , SignUpActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}