package com.example.namiacosmeticsproject.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.namiacosmeticsproject.Data.SessionManager;
import com.example.namiacosmeticsproject.User.DashboardActivity;
import com.example.namiacosmeticsproject.R;
import com.example.namiacosmeticsproject.User.ProductDetails;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView profile_title;
    EditText profile_full_name, profile_email;
    ImageView iv_logOut;
    CircleImageView userImage;
    Object userId;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar = findViewById(R.id.toolbar_profile);
        userImage = findViewById(R.id.profile_img);
        SessionManager sessionManager = new SessionManager(this);

        HashMap<String, String> userInfo = sessionManager.getUserDetails();
        userId = userInfo.get("id");
        Object title = userInfo.get("userName");
        Object userName = userInfo.get("userName");
        Object userEmail = userInfo.get("userEmail");
        String userImgUrl = userInfo.get("userImgUrl");

        profile_title = findViewById(R.id.profile_title);
        profile_full_name = findViewById(R.id.profile_full_name);
        profile_email = findViewById(R.id.profile_email);
        iv_logOut = findViewById(R.id.iv_logOut);

        profile_title.setText(title + "");
        profile_full_name.setText(userName + "");
        profile_email.setText(userEmail + "");
        Picasso.get().load(userImgUrl).into(userImage);

        iv_logOut.setOnClickListener(v -> {
            sessionManager.logoutSession();
            Intent intent = new Intent(ProfileActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();
        });

        userImage.setOnClickListener(v -> {
            Toast.makeText(this, "Profile Image", Toast.LENGTH_SHORT).show();
        });

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_white);
        getSupportActionBar().setTitle("");
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,DashboardActivity.class);
        intent.putExtra("userId",Integer.parseInt(userId.toString()));
        this.startActivity(intent);
        super.onBackPressed();
    }
}