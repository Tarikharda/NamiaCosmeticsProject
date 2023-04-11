package com.example.namiacosmeticsproject.Fragments.ForgetPasswordFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.namiacosmeticsproject.Admin.VerifyPhoneNumber;
import com.example.namiacosmeticsproject.R;

public class ForgetPasswordFragment2 extends Fragment {

    Button viaSms , viaEmail;
    ImageView backBtn;
    TextView tvPhone , tvEmail;
    FragmentTransaction fragmentTransaction;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forget_password2, container, false);

        viaSms = view.findViewById(R.id.forgetPass_2_via_phone_number);
        viaEmail = view.findViewById(R.id.forgetPass_2_via_email);
        backBtn = view.findViewById(R.id.forgetPass_2_back_btn);
        tvPhone = view.findViewById(R.id.forgetPass_2_phone_number);
        tvEmail= view.findViewById(R.id.forgetPass_2_email);

        viaSms.setOnClickListener(v -> {
            Intent phone = new Intent(getActivity() , VerifyPhoneNumber.class);
            phone.putExtra("methode" , tvPhone.getText());
            startActivity(phone);
        });

        viaEmail.setOnClickListener(v -> {
            Intent email = new Intent(getActivity() , VerifyPhoneNumber.class);
            email.putExtra("methode" , tvEmail.getText());
            startActivity(email);
        });

        backBtn.setOnClickListener(v -> {
            onBackPressed();

            fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.forget_password_container , new ForgetPasswordFragment1());
            fragmentTransaction.commit();
        });
        // Inflate the layout for this fragment
        return view;
    }

    private void onBackPressed() {
        // Handle back button event here
        // For example, pop the current fragment from the back stack
        // Get the FragmentManager
        FragmentManager fragmentManager = getParentFragmentManager();

        // Remove the current fragment
        fragmentManager.beginTransaction().remove(this).commit();

        // Remove the fragment from the back stack
        fragmentManager.popBackStack();
    }
}