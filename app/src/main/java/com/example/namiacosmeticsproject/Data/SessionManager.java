package com.example.namiacosmeticsproject.Data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.namiacosmeticsproject.Classes.User;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    private static final String SHARED_PREF_NAME = "session";
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_IMG_URL = "imgurl";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }


    public void createLoginSession(User user) {
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.putString(KEY_ID,user.getUserId()+"");
        editor.putString(KEY_USERNAME, user.getUserName());
        editor.putString(KEY_EMAIL, user.getUserEmail());
        editor.putString(KEY_PASSWORD, user.getUserEmail());
        editor.putString(KEY_IMG_URL, user.getUserImgUrl());
        editor.apply();
    }

    public void logoutSession() {
        editor.clear();
        editor.commit();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }


    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> userDetails = new HashMap<>();
        userDetails.put(KEY_ID, sharedPreferences.getString(KEY_ID, null));
        userDetails.put(KEY_USERNAME, sharedPreferences.getString(KEY_USERNAME, null));
        userDetails.put(KEY_EMAIL, sharedPreferences.getString(KEY_EMAIL, null));
        userDetails.put(KEY_PASSWORD, sharedPreferences.getString(KEY_PASSWORD, null));
        userDetails.put(KEY_IMG_URL, sharedPreferences.getString(KEY_IMG_URL, null));

        return userDetails;
    }
}
