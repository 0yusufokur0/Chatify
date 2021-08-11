package com.resurrection.chatify.ui.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.resurrection.chatify.R;

public class Auth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        Auth.this.getSupportFragmentManager().beginTransaction().replace(R.id.authFrameLayout, new RegisterFragment()).addToBackStack(null).commit();


    }
}