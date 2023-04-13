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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.namiacosmeticsproject.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView txtLogin;
    Button btn_signUp;
    TextInputEditText signup_full_name,signup_email, signup_password;;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        txtLogin = findViewById(R.id.txt_login);
        toolbar = findViewById(R.id.toolbar);
        btn_signUp = findViewById(R.id.btn_signUp);
        signup_full_name = findViewById(R.id.signup_full_name);
        signup_email = findViewById(R.id.signup_email);
        signup_password = findViewById(R.id.signup_password);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_white);
        getSupportActionBar().setTitle("");

        btn_signUp.setOnClickListener(view -> {
            String fullName = signup_full_name.getText().toString().trim();
            String email = signup_email.getText().toString().trim();
            String password = signup_password.getText().toString().trim();

            if (fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please fill all editText!", Toast.LENGTH_LONG).show();
                return;
            }

            String url = "https://volleyhost.000webhostapp.com/postUsers.php";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("signup_full_name", fullName);
                    params.put("signup_email", email);
                    params.put("signup_password", password);
                    return params;
                }
            };
            // Add request to Volley queue
            Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
        });

        txtLogin.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
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
