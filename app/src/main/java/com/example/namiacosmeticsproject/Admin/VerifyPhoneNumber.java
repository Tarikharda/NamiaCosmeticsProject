package com.example.namiacosmeticsproject.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.namiacosmeticsproject.Fragments.ForgetPasswordFragments.ForgetPasswordFragment2;
import com.example.namiacosmeticsproject.Fragments.ForgetPasswordFragments.ForgetPasswordSuccessMessageFragment;
import com.example.namiacosmeticsproject.R;

public class VerifyPhoneNumber extends AppCompatActivity {

    TextView showMethod;
    Button verificationBtn;
    ImageView backBtn;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone_number);

        showMethod = findViewById(R.id.methode_of_verification);
        verificationBtn = findViewById(R.id.verification_btn);
        backBtn = findViewById(R.id.verification_back_btn);

        Bundle data = getIntent().getExtras();

        showMethod.setText(data.getString("methode"));

        verificationBtn.setOnClickListener(v -> {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.verification_container , new ForgetPasswordSuccessMessageFragment());
            fragmentTransaction.commit();
        });

        backBtn.setOnClickListener(v -> {
            finish();
        });
    }
}