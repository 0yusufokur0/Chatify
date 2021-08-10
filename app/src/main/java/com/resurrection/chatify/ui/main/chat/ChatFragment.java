package com.resurrection.chatify.ui.main.chat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.resurrection.chatify.R;
import com.resurrection.chatify.data.ChatDatabase;
import com.resurrection.chatify.data.db.dao.ChatDao;
import com.resurrection.chatify.data.db.entity.ChatEntity;
import com.resurrection.chatify.data.db.entity.PersonEntity;
import com.resurrection.chatify.ui.base.ChatViewModel;
import com.resurrection.chatify.ui.main.messages.MessagesActivity;
import com.resurrection.chatify.ui.persons.ChoosePersonAdapter;
import com.resurrection.chatify.ui.persons.ManagePersons;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ChatFragment extends Fragment {
    View mView;
    RecyclerView recyclerView;
    ImageButton chatImageBtn;
    ChatAdapter chatAdapter;
    ChatViewModel chatViewModel;

    private void init(){
        recyclerView = mView.findViewById(R.id.chatRecyclerView);
        chatImageBtn = mView.findViewById(R.id.chatImageBtn);
        chatAdapter = new ChatAdapter();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_chat, container, false);

        init();
        getAndSetItems();
        ChatDatabase chatDatabase = ChatDatabase.getInstance(getContext());
        ChatDao chatDao = chatDatabase.chatDao();
/*
        PersonEntity personEntity = chatDao.getPerson( "7082021193821");
*/
/*
        System.out.println(personEntity.getName());
*/

        chatImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ManagePersons.class));
/*
                      chatViewModel.insertPerson(new PersonEntity(idCreater(), "sdfsdf", "dfgdfg", "564645465"));
*/

            }
        });

        chatAdapter.setOnItemClickListener(new ChoosePersonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PersonEntity personEntity) {
                Intent intent = new Intent(getActivity(),MessagesActivity.class);
                intent.putExtra("personId",personEntity.getId());
                startActivity(intent);
            }
        });

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requestStoragePermission();

    }


    private void getAndSetItems() {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(chatAdapter);
        Long personId ,messageId;
        personId = idCreater();
        messageId = idCreater();

        chatViewModel = ViewModelProviders.of(getActivity()).get(ChatViewModel.class);
/*
        chatViewModel.insertPerson(new PersonEntity(personId,"deneme","test","sdfsdf"));
        chatViewModel.insertChat(new ChatEntity(messageId,personId,"hi guys",true,"6++6+"));
*/

        chatViewModel.getAllChat().observe(getActivity(), new Observer<List<ChatEntity>>() {
            @Override
            public void onChanged(List<ChatEntity> messageEntities) {
                chatAdapter.setChat(messageEntities);
            }
        });






    }

    public long idCreater() {
        Date nowDateTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyykkmmss");
        String date = dateFormat.format(nowDateTime);
        System.out.println(date);
        return Long.valueOf(date);
    }

    public boolean requestStoragePermission() {
        final int STORAGE_REQUEST_CODE = 101;
        String[] storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        boolean isPermissionGranted = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        if (!isPermissionGranted) {
            ActivityCompat.requestPermissions((Activity) getContext(), storagePermission, STORAGE_REQUEST_CODE);
            return false;
        } else {
            return true;
        }
    }
}






