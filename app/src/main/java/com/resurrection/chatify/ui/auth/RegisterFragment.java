package com.resurrection.chatify.ui.auth;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.datepicker.MaterialTextInputPicker;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.resurrection.chatify.R;


public class RegisterFragment extends Fragment {
    View mView;
    private Button loginBtn, registerBtn, loginWithGoogle, loginWithFacebook;
    private EditText editEmail, editPassword,editName,editPasswordRepat;
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;



    private void init() {
        editName = mView.findViewById(R.id.editNameR);
        editEmail = mView.findViewById(R.id.editEmailR);
        editPassword = mView.findViewById(R.id.editPasswordR);
        loginBtn = mView.findViewById(R.id.loginBtnFromRegister);
        registerBtn = mView.findViewById(R.id.registerBtnFromRegister);
        loginWithGoogle = mView.findViewById(R.id.registerWithGoogle);
        loginWithFacebook = mView.findViewById(R.id.registerWithFacebook);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_register, container, false);
        init();
        setBackPressAction();
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        registerBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
       /*         if (editEmail.getText().toString().isEmpty()|| editName.getText().toString().isEmpty()||editPassword.getText().toString().isEmpty()||editPasswordRepat.getText().toString().isEmpty())
                {
                    Toast.makeText(getContext(), "Lütfen Boşlukları doğru bir şekilde doldurunuz", Toast.LENGTH_SHORT).show();
                }else {*/
                    firebaseAuth.createUserWithEmailAndPassword(editEmail.getText().toString(),editPassword.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            databaseReference.child("users").child(firebaseAuth.getUid()).child("user_info").child("name").setValue(editName.getText().toString());
                            databaseReference.child("users").child(firebaseAuth.getUid()).child("user_info").child("email").setValue(editEmail.getText().toString());
                            databaseReference.child("users").child(firebaseAuth.getUid()).child("user_info").child("password").setValue(editPassword.getText().toString());
                            Toast.makeText(getContext(), "kayıt tamamlandı", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "kayıt oluşturulamadı", Toast.LENGTH_SHORT).show();
                        }
                    });
               /* }*/


            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.authFrameLayout, new LoginFragment()).addToBackStack(null).commit();

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