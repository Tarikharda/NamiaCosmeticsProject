package com.example.namiacosmeticsproject.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.namiacosmeticsproject.R;

public class SignUpActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView txtLogin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        toolbar = findViewById(R.id.toolbar_signup);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_white);
        getSupportActionBar().setTitle("");

        txtLogin = findViewById(R.id.txt_login);

        txtLogin.setOnClickListener(v -> {
            Intent intent = new Intent(this , LoginActivity.class);
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

//    private boolean validatePassword() {
//        String value = password.getEditText().getText().toString().trim();
//        String checkPassword =  "^" +
//                "(?=.*[a-zA-Z])" +           // for let the password contains any letter
//                "(?=.*[0-9])" +              // for let the password contains one digits
//                "(?=.*[@#$%^&*+=])" +        // for let the password contains at least one special character
//                "(?=\\S+$)" +                // for let the password contains no white spaces
//                ".{6,}" +                    // for let the password contains at least 6 character
//                "$";
//
//        if (value.isEmpty()) {
//            email.setError("Field can not be empty");
//            return false;
//        } else if (!value.matches(checkPassword)){
//            email.setError("Password should contains at least 6 characters with numbers and symbols!");
//            return false;
//        }else {
//            email.setError(null);
//            email.setErrorEnabled(false);
//            return true;
//        }
//    }

}