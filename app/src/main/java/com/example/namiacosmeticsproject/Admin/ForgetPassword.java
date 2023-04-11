package com.example.namiacosmeticsproject.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.namiacosmeticsproject.Fragments.ForgetPasswordFragments.ForgetPasswordFragment1;
import com.example.namiacosmeticsproject.R;

public class ForgetPassword extends AppCompatActivity {

    FrameLayout fragmentContainer;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

//        fragmentContainer = findViewById(R.id.forget_password_container);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.forget_password_container , new ForgetPasswordFragment1());
        fragmentTransaction.commit();

    }
}