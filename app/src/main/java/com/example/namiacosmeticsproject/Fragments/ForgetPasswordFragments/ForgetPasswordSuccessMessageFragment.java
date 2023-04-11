package com.example.namiacosmeticsproject.Fragments.ForgetPasswordFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.namiacosmeticsproject.Admin.LoginActivity;
import com.example.namiacosmeticsproject.R;

public class ForgetPasswordSuccessMessageFragment extends Fragment {

    Button loginBtn;
    ImageView backBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forget_password_success_message, container, false);

        loginBtn = view.findViewById(R.id.forgetPass_go_to_login);
        backBtn = view.findViewById(R.id.forgetPass_success_back_btn);

        loginBtn.setOnClickListener(v -> {
            startActivity(new Intent(getActivity() , LoginActivity.class));
        });

        backBtn.setOnClickListener(v -> {
            getActivity().finish();
            getActivity().finish();
        });


        // Inflate the layout for this fragment
        return view;
    }
}