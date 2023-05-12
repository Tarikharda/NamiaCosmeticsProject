package com.example.namiacosmeticsproject.Admin;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.namiacosmeticsproject.Classes.ProductClass;
import com.example.namiacosmeticsproject.Data.LocalDataBase;
import com.example.namiacosmeticsproject.R;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
//import com.stripe.android.paymentsheet.PaymentSheetResult;

import java.math.BigDecimal;
import java.util.ArrayList;

public class AddressChecker extends AppCompatActivity {

    Toolbar toolbar;
    TextInputLayout fullNameview, emailview, postalCodeview, phoneview, cityview, addressview;
    CountryCodePicker countryview;
    Button confirmBtn;
    String number = "+212643129153", fullName, email, postalCode, phone, city, address, country;
    LocalDataBase db = new LocalDataBase(this);
    Bundle data;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        data = getIntent().getExtras();
        initViews();
        confirmBtn.setOnClickListener(V -> {
            if (validateAddress() & validateCity() & validateEmail() & validatePhone() & validateFullName() & validatePostalCode()) {

                fullName = fullNameview.getEditText().getText().toString().trim();
                email = emailview.getEditText().getText().toString().trim();
                postalCode = postalCodeview.getEditText().getText().toString().trim();
                phone = phoneview.getEditText().getText().toString().trim();
                address = addressview.getEditText().getText().toString().trim();
                city = cityview.getEditText().getText().toString().trim();
                country = String.format("%s (%s)", countryview.getSelectedCountryName(), countryview.getSelectedCountryNameCode());
                if (country.equals("Morocco (MA)")) {
                    if (data == null) {
                        whatsapp();
                    } else {
                        whatsapp02();
                    }

                } else {
                    getPaymentMethode(15);
                }
            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_white);
        getSupportActionBar().setTitle("");

        Toast.makeText(this, String.format("%s %s", countryview.getSelectedCountryName(), countryview.getSelectedCountryNameCode()), Toast.LENGTH_LONG).show();

    }


    private void initViews() {
        toolbar = findViewById(R.id.toolbar_payment);
        fullNameview = findViewById(R.id.payment_full_name);
        emailview = findViewById(R.id.payment_email);
        countryview = findViewById(R.id.payment_country_picker);
        postalCodeview = findViewById(R.id.payment_postal_code);
        phoneview = findViewById(R.id.payment_phone);
        cityview = findViewById(R.id.payment_city);
        addressview = findViewById(R.id.payment_address);
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
        String value = fullNameview.getEditText().getText().toString().trim();

        if (value.isEmpty()) {
            fullNameview.setError("Field can not be empty");
            return false;
        } else {
            fullNameview.setError(null);
            fullNameview.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail() {
        String value = emailview.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (value.isEmpty()) {
            emailview.setError("Field can not be empty");
            return false;
        } else if (!value.matches(checkEmail)) {
            emailview.setError("Invalid Email !");
            return false;
        } else {
            emailview.setError(null);
            emailview.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePostalCode() {
        String value = postalCodeview.getEditText().getText().toString().trim();

        if (value.isEmpty()) {
            postalCodeview.setError("Field can not be empty");
            return false;
        } else {
            postalCodeview.setError(null);
            postalCodeview.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePhone() {
        String value = phoneview.getEditText().getText().toString().trim();

        if (value.isEmpty()) {
            phoneview.setError("Field can not be empty");
            return false;
        } else {
            phoneview.setError(null);
            phoneview.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateCity() {
        String value = cityview.getEditText().getText().toString().trim();

        if (value.isEmpty()) {
            cityview.setError("Field can not be empty");
            return false;
        } else {
            cityview.setError(null);
            cityview.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateAddress() {
        String value = addressview.getEditText().getText().toString().trim();

        if (value.isEmpty()) {
            addressview.setError("Field can not be empty");
            return false;
        } else {
            addressview.setError(null);
            addressview.setErrorEnabled(false);
            return true;
        }
    }


    public void getPaymentMethode(float price) {
        // Define a constant for the PayPal request code
        final int PAYPAL_REQUEST_CODE = 123;

// Create a PayPalConfiguration object and set the client ID, secret, and environment
        PayPalConfiguration config = new PayPalConfiguration()
                .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
                .clientId("ARFW8oeDHjh-bTyQxCDYCdw5mAV0fmoBr-GT-CRvzZpMwuhxfQznpxipU7vXofA7dbPx-WBGr4voBW4o");
//            .clientSecret("YOUR_CLIENT_SECRET_VALUE_HERE");

// Create a PayPalPayment object with the payment details
        PayPalPayment payment = new PayPalPayment(new BigDecimal("1.00"), "USD", "Payment description", PayPalPayment.PAYMENT_INTENT_SALE);

// Create the intent for the PayPal payment activity
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);

// Launch the PayPal payment activity
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);


//        // Create a new instance of ActivityResultLauncher
//        ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
//            if (result.getResultCode() == Activity.RESULT_OK) {
//                Intent data = result.getData();
//                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
//                if (confirmation != null) {
//                    String orderId = confirmation.getEnvironment();
//                    // handle successful payment
//                    Toast.makeText(this, "Payment Succes", Toast.LENGTH_SHORT).show();
//                }
//            } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
//                // handle canceled payment
//                Toast.makeText(AddressChecker.this, "Payment Canceled", Toast.LENGTH_SHORT).show();
//            } else if (result.getResultCode() == PaymentActivity.RESULT_EXTRAS_INVALID) {
//                // handle invalid payment
//                Toast.makeText(AddressChecker.this, "payment invalid", Toast.LENGTH_SHORT).show();
//            }
//        });
//    launcher.launch(intent);


    }


    public void whatsapp() {
        ArrayList<ProductClass> productesdetails = db.getAllProducts();
        String message = "PRODUCTS INFO :\n";
        for (ProductClass product :
                productesdetails) {
            message += "   Product Id : " + product.getProductId() + "\n   Product Titel : " + product.getProductName() + "\n   Product Count : " + product.getProductCount() + "\n";
        }
        message += "\nADDRESS INFO :\n";
        message += "   FullName : " + fullName + "\n   Email : " + email + "\n   Phone : " + phone + "\n   City :" + city + "\n   Address :" + address + "\n   PostalCode :" + postalCode;
        String url = "https://api.whatsapp.com/send?phone=" + number + "&text=" + message;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }


    public void whatsapp02() {
        String message = "";
        message += "\nADDRESS INFO :\n";
        message += "   Product Id : " + data.getInt("id") + "\n   Product Titel : " + data.getString("title") + "\n   Product Count : " + 1 + "\n";
        message += "   FullName : " + fullName + "\n   Email : " + email + "\n   Phone : " + phone + "\n   City :" + city + "\n   Address :" + address + "\n   PostalCode :" + postalCode;
        String url = "https://api.whatsapp.com/send?phone=" + number + "&text=" + message;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

}