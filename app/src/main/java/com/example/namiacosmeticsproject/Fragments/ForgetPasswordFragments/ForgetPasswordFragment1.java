package com.example.namiacosmeticsproject.Fragments.ForgetPasswordFragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.namiacosmeticsproject.R;


public class ForgetPasswordFragment1 extends Fragment {

    Button nextBtn;
    ImageView backBtn;
    FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forget_password1, container, false);

        nextBtn = view.findViewById(R.id.forgetPass_1_next_btn);
        backBtn = view.findViewById(R.id.forgetPass_1_back_btn);

        nextBtn.setOnClickListener(v -> {
            fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.forget_password_container , new ForgetPasswordFragment2());
            fragmentTransaction.commit();
        });

        backBtn.setOnClickListener(v -> {
            getActivity().finish();
        });
        // Inflate the layout for this fragment
        return view;
    }

}