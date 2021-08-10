package com.resurrection.chatify.ui.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.resurrection.chatify.data.db.entity.MessageEntity;
import com.resurrection.chatify.data.db.entity.PersonEntity;
import com.resurrection.chatify.data.repository.ChatRepository;

import java.util.List;

public class ChatViewModel extends AndroidViewModel {

    private ChatRepository chatRepository;
    LiveData<List<PersonEntity>> personData;
    LiveData<List<MessageEntity>> messageData;


    public ChatViewModel(@NonNull Application application) {
        super(application);
        chatRepository = new ChatRepository(application);
        personData = chatRepository.getAllPerson();
        messageData = chatRepository.getAllMessage();

    }

    public void insertPerson(PersonEntity personEntity){
        chatRepository.insertPerson(personEntity);
    }
    public void updatePerson(PersonEntity personEntity){
        chatRepository.updatePerson(personEntity);
    }
    public void deletePerson(PersonEntity personEntity){
        chatRepository.deletePerson(personEntity);
    }
    public LiveData<List<PersonEntity>> getAllPerson(){
        return personData;
    }

    public PersonEntity getPerson(Long id){
        return chatRepository.getPerson(id);
    }

    public void insertMessage(MessageEntity messageEntity){
        chatRepository.insertMessage(messageEntity);
    }

    public void updateMessage(MessageEntity messageEntity){
        chatRepository.updateMessage(messageEntity);
    }

    public void deleteMessage(MessageEntity messageEntity){
        chatRepository.deleteMessage(messageEntity);
    }
    public LiveData<List<MessageEntity>> getAllMessage(){
        return messageData;
    }


}
