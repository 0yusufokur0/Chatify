package com.resurrection.chatify.data.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "chat")
public class ChatEntity {

    @PrimaryKey
    private long id;

    @ColumnInfo(name = "personId")
    private Long personId;

    @ColumnInfo(name = "message")
    private String message;

    @ColumnInfo(name = "state")
    private boolean messageState;

    @ColumnInfo(name = "date")
    private String date;


    public ChatEntity(long id, Long personId, String message, boolean messageState, String date) {
        this.id = id;
        this.personId = personId;
        this.message = message;
        this.messageState = messageState;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isMessageState() {
        return messageState;
    }

    public void setMessageState(boolean messageState) {
        this.messageState = messageState;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
