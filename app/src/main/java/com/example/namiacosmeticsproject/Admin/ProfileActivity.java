package com.example.namiacosmeticsproject.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.namiacosmeticsproject.Data.SessionManager;
import com.example.namiacosmeticsproject.R;

import java.util.Collection;
import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView profile_title;
    EditText profile_full_name, profile_email;
    ImageView iv_logOut;


    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar = findViewById(R.id.toolbar_profile);
        SessionManager sessionManager = new SessionManager(this);
        HashMap<String, String> userInfo = sessionManager.getUserDetails();
        Object test1 = userInfo.entrySet().toArray()[0];
        Object test2 = userInfo.entrySet().toArray()[2];
        Object test3 = userInfo.entrySet().toArray()[3];

        profile_title = findViewById(R.id.profile_title);
        profile_full_name = findViewById(R.id.profile_full_name);
        profile_email = findViewById(R.id.profile_email);
        iv_logOut = findViewById(R.id.iv_logOut);

        profile_title.setText(test1 + "");
        profile_full_name.setText(test2 + "");
        profile_full_name.setText(test3 + "");
        iv_logOut.setOnClickListener(v -> {
            sessionManager.logoutSession();
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_white);
        getSupportActionBar().setTitle("");
    }
}