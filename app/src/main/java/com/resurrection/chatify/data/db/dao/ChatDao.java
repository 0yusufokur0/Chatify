package com.resurrection.chatify.data.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.resurrection.chatify.data.db.entity.ChatEntity;
import com.resurrection.chatify.data.db.entity.PersonEntity;

import java.util.List;

@Dao
public interface ChatDao {

    @Insert
    void InsertPerson(PersonEntity personEntity);

    @Update
    void UpdatePerson(PersonEntity personEntity);

    @Delete
    void DeletePerson(PersonEntity personEntity);

    @Query("SELECT * FROM person ORDER BY id")
    LiveData<List<PersonEntity>> getAllPerson();

    @Query("SELECT * FROM person ORDER BY id DESC LIMIT 1")
    PersonEntity getLastPerson();

    @Insert
    void InsertChat(ChatEntity chatEntity);

    @Update
    void UpdateChat(ChatEntity chatEntity);

    @Delete
    void DeleteChat(ChatEntity chatEntity);

    @Query("SELECT * FROM chat ORDER BY id")
    LiveData<List<ChatEntity>> getAllChat();

    @Query("SELECT * FROM person WHERE id = :asd")
    PersonEntity getPerson(Long asd);

}
