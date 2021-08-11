package com.resurrection.chatify.ui.persons;

import static com.resurrection.chatify.data.Constants.idCreater;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.resurrection.chatify.R;
import com.resurrection.chatify.data.db.entity.PersonEntity;
import com.resurrection.chatify.ui.base.ChatViewModel;

import java.util.List;

public class CreatePersonFragment extends Fragment {

    View mView;
    private Button cancel, save;
    ChatViewModel chatViewModel;
    EditText name, surname, phone;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    private void init() {
        cancel = mView.findViewById(R.id.buttonCancel);
        save = mView.findViewById(R.id.buttonSave);
        name = mView.findViewById(R.id.editNameR);
        surname = mView.findViewById(R.id.editSurname);
        phone = mView.findViewById(R.id.editPhone);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_create_person, container, false);
        init();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!name.getText().toString().isEmpty() && !surname.getText().toString().isEmpty() && !phone.getText().toString().isEmpty()) {
                    savePerson(name.getText().toString(), surname.getText().toString(), phone.getText().toString());
                    name.setText("");
                    surname.setText("");
                    phone.setText("");
                }
/*
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.manageFrameLayout, new ChoosePersonFragment()).addToBackStack(null).commit();
*/
                startActivity(new Intent(getContext(),ManagePersons.class));
                getActivity().finish();

            }
        });

        return mView;
    }

    private void savePerson(String name, String surname, String phone) {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        chatViewModel = ViewModelProviders.of(getActivity()).get(ChatViewModel.class);
        chatViewModel.getAllPerson().observe(getActivity(), new Observer<List<PersonEntity>>() {
            @Override
            public void onChanged(List<PersonEntity> personEntities) {
            }
        });
        chatViewModel.insertPerson(new PersonEntity(idCreater(), name, surname, phone,false));

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        databaseReference.child("users").child(firebaseUser.getUid()).child("persons").setValue(name);


    }


}