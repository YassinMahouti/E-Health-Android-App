package com.example.iliasspush;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private String name;
    private int id;

    List<User>users;

    /////////////User constructor////////////////
    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    //////////////////Getters en Setters//////////////

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
