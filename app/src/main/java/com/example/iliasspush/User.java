package com.example.iliasspush;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


import java.util.List;

@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(id = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;


@Ignore
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
