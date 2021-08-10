package com.resurrection.chatify.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.resurrection.chatify.data.db.dao.ChatDao;
import com.resurrection.chatify.data.db.entity.ChatEntity;
import com.resurrection.chatify.data.db.entity.Converters;
import com.resurrection.chatify.data.db.entity.MessageEntity;
import com.resurrection.chatify.data.db.entity.PersonEntity;

@Database(entities = {PersonEntity.class, ChatEntity.class, MessageEntity.class}, version = 1)
@TypeConverters({Converters.class})
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
