package com.resurrection.chatify.data.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "message")
public class MessageEntity {

    @PrimaryKey
    private long id;

    @ColumnInfo(name = "chatId")
    private long chatId;

    @ColumnInfo(name = "message")
    private String messages;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "isSend")
    private boolean isSend;

    @ColumnInfo(name = "isMe")
    private boolean isMe;

    public MessageEntity(long id, long chatId, String messages, String date, boolean isSend, boolean isMe) {
        this.id = id;
        this.chatId = chatId;
        this.messages = messages;
        this.date = date;
        this.isSend = isSend;
        this.isMe = isMe;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isSend() {
        return isSend;
    }

    public void setSend(boolean send) {
        isSend = send;
    }

    public boolean isMe() {
        return isMe;
    }

    public void setMe(boolean me) {
        isMe = me;
    }
}
