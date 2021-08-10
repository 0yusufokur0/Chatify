package com.resurrection.chatify.data.repository;

import android.app.Application;
import android.os.AsyncTask;

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


    public LiveData<List<PersonEntity>> getAllPerson() {
        return personData;
    }


    public LiveData<List<ChatEntity>> getAllChat() {
        return chatData;
    }

    public LiveData<List<MessageEntity>> getAllMessage() {
        return messageData;
    }




    public void insertPerson(PersonEntity personEntity) {
        new InsertPersonAsyncPerson(chatDao).execute(personEntity);
    }
    public PersonEntity getPerson(Long id) {
        return chatDao.getPerson(id);
    }


    public void insertChat(ChatEntity chatEntity) {
        System.out.println("yeni chat eklendi");
        new InsertChatAysncTask(chatDao).execute(chatEntity);
    }

    public void insertMessage(MessageEntity messageEntity) {
        new InsertMessageAysncTask(chatDao).execute(messageEntity);

    }






    class InsertMessageAysncTask extends AsyncTask<MessageEntity,Void,Void>{
        ChatDao chatDao;

        public InsertMessageAysncTask(ChatDao chatDao) {
            this.chatDao = chatDao;
        }

        @Override
        protected Void doInBackground(MessageEntity... messageEntities) {
            chatDao.InsertMessage(messageEntities[0]);
            return null;
        }
    }

    class InsertChatAysncTask extends AsyncTask<ChatEntity,Void,Void>{
        ChatDao chatDao;

        public InsertChatAysncTask(ChatDao chatDao) {
            this.chatDao = chatDao;
        }


        @Override
        protected Void doInBackground(ChatEntity... chatEntities) {
            chatDao.InsertChat(chatEntities[0]);

            return null;
        }
    }


    class InsertPersonAsyncPerson extends AsyncTask<PersonEntity,Void,Void>{
        ChatDao chatDao;

        public InsertPersonAsyncPerson(ChatDao chatDao) {
            this.chatDao = chatDao;
        }

        @Override
        protected Void doInBackground(PersonEntity... personEntities) {
            chatDao.InsertPerson(personEntities[0]);
            return null;
        }
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







}
