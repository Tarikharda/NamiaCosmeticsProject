package com.example.namiacosmeticsproject.Test;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.namiacosmeticsproject.Classes.User;
import com.example.namiacosmeticsproject.Data.ProductsService;
import com.example.namiacosmeticsproject.Data.SessionManager;
import com.example.namiacosmeticsproject.R;

import java.util.HashMap;

public class Example01 extends AppCompatActivity {
    Button btn_loginTest,btn_logOutTest;
    EditText ed_emailTest, ed_passwordTest;

    @SuppressLint({"MissingInflatedId", "CutPasteId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example01);

        btn_loginTest = findViewById(R.id.btn_loginTest);
        btn_logOutTest = findViewById(R.id.btn_logOutTest);
        ed_emailTest = findViewById(R.id.ed_emailTest);
        ed_passwordTest = findViewById(R.id.ed_passwordTest);
        SessionManager sessionManager = new SessionManager(Example01.this);

        btn_loginTest.setOnClickListener(v -> {
            String email = ed_emailTest.getText().toString();
            String password = ed_passwordTest.getText().toString();
            Log.d("DEBUG", "Email: " + email + ", Password: " + password);
            ProductsService productsService = new ProductsService(this);
            productsService.getUser(email, password, new ProductsService.UserInfo() {
                @Override
                public void getUser(User user) {
                    HashMap<String, String> userInfo = sessionManager.getUserDetails();
                    Object test = userInfo.entrySet().toArray()[0];
                    Toast.makeText(Example01.this, test + "", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(String errorMessage) {
                    Toast.makeText(Example01.this, errorMessage, Toast.LENGTH_LONG).show();
                }
            });
        });
        btn_logOutTest.setOnClickListener(v->{
          sessionManager.logoutSession();
        });
    }
}