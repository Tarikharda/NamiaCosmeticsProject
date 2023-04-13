
package com.example.namiacosmeticsproject.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.namiacosmeticsproject.Classes.User;
import com.example.namiacosmeticsproject.Data.ProductsService;
import com.example.namiacosmeticsproject.Data.SessionManager;
import com.example.namiacosmeticsproject.R;

import com.example.namiacosmeticsproject.Test.Example01;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView txtForget, txtSignUp;
    Button btn_login;
    TextInputLayout loginEmail, loginPassword;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar = findViewById(R.id.toolbar_login);
        btn_login = findViewById(R.id.btn_login);
        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_black);
        getSupportActionBar().setTitle("");

        txtForget = findViewById(R.id.txt_forget);
        txtSignUp = findViewById(R.id.txt_sign_up);

        txtForget.setOnClickListener(v -> {
            Toast.makeText(this, "I forget my password 😫", Toast.LENGTH_SHORT).show();
        });
        btn_login.setOnClickListener(v -> {
            String email = Objects.requireNonNull(loginEmail.getEditText().getText()).toString().trim();
            String password = Objects.requireNonNull(loginPassword.getEditText().getText()).toString().trim();
            SessionManager sessionManager = new SessionManager(this);
            ProductsService productsService = new ProductsService(this);

            productsService.getUser(email, password, new ProductsService.UserInfo() {
                @Override
                public void getUser(User user) {
//                    Toast.makeText(LoginActivity.this, user.getUserEmail(), Toast.LENGTH_SHORT).show();
                    sessionManager.createLoginSession(user);
                    Intent intent = new Intent(LoginActivity.this, Example01.class);
                    startActivity(intent);

                }

                @Override
                public void onError(String errorMessage) {
                    Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                }
            });
        });
        txtSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignUpActivity.class);
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