package com.resurrection.chatify.ui.persons;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.resurrection.chatify.R;
import com.resurrection.chatify.data.db.entity.ChatEntity;
import com.resurrection.chatify.data.db.entity.PersonEntity;
import com.resurrection.chatify.ui.base.ChatViewModel;
import com.resurrection.chatify.ui.main.messages.MessagesActivity;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ChoosePersonFragment extends Fragment {
    View mView;
    RecyclerView recyclerView;
    Context context;
    ChoosePersonAdapter choosePersonAdapter = new ChoosePersonAdapter();
    ChatViewModel chatViewModel;

        private void init(){
        recyclerView = mView.findViewById(R.id.choosePersonRecyclerView);
        context = getActivity();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_choose_person, container, false);
        init();
        getAndSetItems();
        setBackPressAction();

        choosePersonAdapter.setOnItemClickListener(new ChoosePersonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PersonEntity personEntity) {
                // ınsert message
                // eğer o kişi ile ilgili chat varsa yeni chat yapma
                chatViewModel.insertChat(new ChatEntity(idCreater(),personEntity.getId(),"",true,getDate()));
                Intent intent = new Intent(getActivity(), MessagesActivity.class);
                intent.putExtra("personId",personEntity.getId());
                startActivity(intent);
                getActivity().finish();
            }
        });

        return mView;
    }
    private void getAndSetItems() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(choosePersonAdapter);

        chatViewModel = ViewModelProviders.of(getActivity()).get(ChatViewModel.class);
        chatViewModel.getAllPerson().observe(getActivity(), new Observer<List<PersonEntity>>() {
            @Override
            public void onChanged(List<PersonEntity> personEntities) {
                choosePersonAdapter.setChoosePerson(personEntities);
            }
        });



    }
    private void setBackPressAction(){
        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                getActivity().finish();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getActivity(), onBackPressedCallback);

    }
    public long idCreater() {
        Date nowDateTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyykkmmss");
        String date = dateFormat.format(nowDateTime);
        System.out.println(date);
        return Long.valueOf(date);
    }
    public static String getDate() {
        java.sql.Date nowDateTime = new java.sql.Date(0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy-kk:mm");
        String date = dateFormat.format(nowDateTime);
        System.out.println(date);
        return date;
    }

}