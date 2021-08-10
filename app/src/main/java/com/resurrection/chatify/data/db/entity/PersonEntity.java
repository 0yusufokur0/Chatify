package com.resurrection.chatify.data.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "person")
public class PersonEntity {

    @PrimaryKey
    private long id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "surname")
    private String surname;

    @ColumnInfo(name = "phone")
    private String phone;

    @ColumnInfo(name = "chatState")
    private boolean chatState;

    public PersonEntity(long id, String name, String surname, String phone, boolean chatState) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.chatState = chatState;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isChatState() {
        return chatState;
    }

    public void setChatState(boolean chatState) {
        this.chatState = chatState;
    }
}
