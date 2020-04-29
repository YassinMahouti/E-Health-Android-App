package com.example.iliasspush;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;

import database.dao.UserDAO;
import database.dao.UserDatabase;
import android.content.Context;

public class UserMod{
    private static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    } ;

    //final UserDatabase userDb = Room.databaseBuilder(this, UserDatabase.class,"userDb").addMigrations(MIGRATION_1_2).build();

}
