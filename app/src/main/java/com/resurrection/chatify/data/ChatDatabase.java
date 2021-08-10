package com.resurrection.chatify.data;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.resurrection.chatify.data.db.dao.ChatDao;
import com.resurrection.chatify.data.db.entity.MessageEntity;
import com.resurrection.chatify.data.db.entity.PersonEntity;

@Database(entities = {PersonEntity.class, MessageEntity.class}, version = 1)
public abstract class ChatDatabase extends RoomDatabase {
    private static ChatDatabase chatDatabase;

    public abstract ChatDao chatDao();

    public static synchronized ChatDatabase getInstance(Context context) {
        if (chatDatabase == null) {
            chatDatabase = Room.databaseBuilder(context.getApplicationContext(), ChatDatabase.class, "chat")
                    .fallbackToDestructiveMigration().allowMainThreadQueries()
                    .build();
        }
        return chatDatabase;
    }


}
