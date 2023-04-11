package com.example.namiacosmeticsproject.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.namiacosmeticsproject.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

public class PaymentActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextInputLayout fullName , email , postalCode , phone , city , address;
    CountryCodePicker country;
    Button confirmBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        initViews();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_white);
        getSupportActionBar().setTitle("");

        Toast.makeText(this,country.getSelectedCountryName(), Toast.LENGTH_SHORT).show();

    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar_payment);
        fullName = findViewById(R.id.payment_full_name);
        email = findViewById(R.id.payment_email);
        country = findViewById(R.id.payment_country_picker);
        postalCode = findViewById(R.id.payment_postal_code);
        phone = findViewById(R.id.payment_phone);
        city = findViewById(R.id.payment_city);
        address = findViewById(R.id.payment_address);
        confirmBtn = findViewById(R.id.payment_confirm_btn);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean validateFullName() {
        String value = fullName.getEditText().getText().toString().trim();

        if (value.isEmpty()) {
            fullName.setError("Field can not be empty");
            return false;
        } else {
            fullName.setError(null);
            fullName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail() {
        String value = email.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (value.isEmpty()) {
            email.setError("Field can not be empty");
            return false;
        } else if (!value.matches(checkEmail)){
            email.setError("Invalid Email !");
            return false;
        }else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePostalCode() {
        String value = postalCode.getEditText().getText().toString().trim();

        if (value.isEmpty()) {
            postalCode.setError("Field can not be empty");
            return false;
        } else {
            postalCode.setError(null);
            postalCode.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePhone() {
        String value = phone.getEditText().getText().toString().trim();

        if (value.isEmpty()) {
            phone.setError("Field can not be empty");
            return false;
        } else {
            phone.setError(null);
            phone.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateCity() {
        String value = city.getEditText().getText().toString().trim();

        if (value.isEmpty()) {
            city.setError("Field can not be empty");
            return false;
        } else {
            city.setError(null);
            city.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateAddress() {
        String value = address.getEditText().getText().toString().trim();

        if (value.isEmpty()) {
            address.setError("Field can not be empty");
            return false;
        } else {
            address.setError(null);
            address.setErrorEnabled(false);
            return true;
        }
    }

}