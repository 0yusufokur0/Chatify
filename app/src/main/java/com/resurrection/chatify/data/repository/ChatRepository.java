package com.resurrection.chatify.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.resurrection.chatify.data.ChatDatabase;
import com.resurrection.chatify.data.db.dao.ChatDao;
import com.resurrection.chatify.data.db.entity.ChatEntity;
import com.resurrection.chatify.data.db.entity.MessageEntity;
import com.resurrection.chatify.data.db.entity.PersonEntity;

import java.util.List;

public class ChatRepository {

    private ChatDao chatDao;
    private LiveData<List<PersonEntity>> personData;
    private LiveData<List<ChatEntity>> chatData;
    private LiveData<List<MessageEntity>> messageData;
    private PersonEntity personEntity;

    public ChatRepository(Application application) {
        ChatDatabase chatDatabase = ChatDatabase.getInstance(application);
        chatDao = chatDatabase.chatDao();
        personData = chatDao.getAllPerson();
        chatData = chatDao.getAllChat();
        messageData = chatDao.getAllMessage();
    }

    public void insertPerson(PersonEntity personEntity) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                chatDao.InsertPerson(personEntity);
            }
        });
        thread.start();
    }

    public void updatePerson(PersonEntity personEntity) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                chatDao.UpdatePerson(personEntity);
            }
        });
        thread.start();
    }

    public void deletePerson(PersonEntity personEntity) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                chatDao.DeletePerson(personEntity);
            }
        });
        thread.start();
    }
    


    public LiveData<List<PersonEntity>> getAllPerson() {
        return personData;
    }


    public PersonEntity getPerson(Long id) {
/*        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
            }
        });
        thread.start();*/
        return chatDao.getPerson(id);
    }
    
    
    


    public void insertChat(ChatEntity chatEntity) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                chatDao.InsertChat(chatEntity);
            }
        });
        thread.start();
    }

    public void updateChat(ChatEntity chatEntity) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                chatDao.UpdateChat(chatEntity);
            }
        });
        thread.start();
    }
    public void deleteChat(ChatEntity chatEntity) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                chatDao.DeleteChat(chatEntity);
            }
        });
        thread.start();
    }



    public LiveData<List<ChatEntity>> getAllChat() {
        return chatData;
    }

    public void insertMessage(MessageEntity messageEntity) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                chatDao.InsertMessage(messageEntity);
            }
        });
        thread.start();
    }
    public void updateMessage(MessageEntity messageEntity) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                chatDao.UpdateMessage(messageEntity);
            }
        });
        thread.start();
    }

    public void deleteMessage(MessageEntity messageEntity) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                chatDao.DeleteMessage(messageEntity);
            }
        });
        thread.start();
    }


        public LiveData<List<MessageEntity>> getAllMessage() {
        return messageData;
    }





}
