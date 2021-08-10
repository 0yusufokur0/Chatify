package com.resurrection.chatify.ui.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.resurrection.chatify.data.db.entity.ChatEntity;
import com.resurrection.chatify.data.db.entity.MessageEntity;
import com.resurrection.chatify.data.db.entity.PersonEntity;
import com.resurrection.chatify.data.repository.ChatRepository;

import java.util.List;

public class ChatViewModel extends AndroidViewModel {

    private ChatRepository chatRepository;
    LiveData<List<PersonEntity>> personData;
    LiveData<List<ChatEntity>> chatData;
    LiveData<List<MessageEntity>> messageData;


    public ChatViewModel(@NonNull Application application) {
        super(application);
        chatRepository = new ChatRepository(application);
        personData = chatRepository.getAllPerson();
        chatData = chatRepository.getAllChat();
        messageData = chatRepository.getAllMessage();

    }

    public void insertPerson(PersonEntity personEntity){
        chatRepository.insertPerson(personEntity);
    }


    public void insertChat(ChatEntity chatEntity){
        chatRepository.insertChat(chatEntity);
    }



    public void insertMessage(MessageEntity messageEntity){
        chatRepository.insertMessage(messageEntity);
    }


    public PersonEntity getPerson(Long id){
        return chatRepository.getPerson(id);
    }


    public LiveData<List<MessageEntity>> getAllMessage(){
        return messageData;
    }
    public LiveData<List<PersonEntity>> getAllPerson(){
        return personData;
    }

    public LiveData<List<ChatEntity>> getAllChat() {
        return chatData;
    }

    public void updatePerson(PersonEntity personEntity){
        chatRepository.updatePerson(personEntity);
    }
    public void deletePerson(PersonEntity personEntity){
        chatRepository.deletePerson(personEntity);
    }

    public void updateChat(ChatEntity chatEntity){
        chatRepository.updateChat(chatEntity);
    }

    public void DeleteChat(ChatEntity chatEntity){
        chatRepository.deleteChat(chatEntity);
    }
/*
    public LiveData<List<ChatEntity>> getAllChat(){
        return chatData;
    }
*/



}
