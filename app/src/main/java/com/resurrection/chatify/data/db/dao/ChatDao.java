package com.resurrection.chatify.data.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.resurrection.chatify.data.db.entity.MessageEntity;
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
    void InsertMessage(MessageEntity messageEntity);

    @Update
    void UpdateMessage(MessageEntity messageEntity);

    @Delete
    void DeleteMessage(MessageEntity messageEntity);

    @Query("SELECT * FROM message ORDER BY id")
    LiveData<List<MessageEntity>> getAllMessage();

    @Query("SELECT * FROM person WHERE id = :asd")
    PersonEntity getPerson(Long asd);

}
