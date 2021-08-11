package com.resurrection.chatify.ui.auth;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.resurrection.chatify.MainActivity;
import com.resurrection.chatify.R;


public class LoginFragment extends Fragment {
    View mView;
    private Button loginBtn, registerBtn, loginWithGoogle, loginWithFacebook;
    private EditText editEmail, editPassword;
    private FirebaseAuth firebaseAuth;
    private void init() {
        editEmail = mView.findViewById(R.id.editEmailL);
        editPassword = mView.findViewById(R.id.editPasswordL);
        loginBtn = mView.findViewById(R.id.loginBtnFromLogin);
        registerBtn = mView.findViewById(R.id.registerBtnFromLogin);
        loginWithGoogle = mView.findViewById(R.id.registerWithGoogle);
        loginWithFacebook = mView.findViewById(R.id.registerWithFacebook);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_login, container, false);
        init();
        setBackPressAction();
        firebaseAuth = FirebaseAuth.getInstance();
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signInWithEmailAndPassword(editEmail.getText().toString(),editPassword.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(getActivity(), MainActivity.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.authFrameLayout, new RegisterFragment()).addToBackStack(null).commit();

            }
        });
        return mView;
    }

    private void setBackPressAction() {
        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                getActivity().finish();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getActivity(), onBackPressedCallback);

    }

}