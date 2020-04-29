package com.example.iliasspush;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class UserMod{
    private static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    } ;

    //final UserDatabase userDb = Room.databaseBuilder(this, UserDatabase.class,"userDb").addMigrations(MIGRATION_1_2).build();

}
