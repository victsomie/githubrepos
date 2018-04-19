package com.example.admin.githubrepos.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.admin.githubrepos.service.model.Project;

/**
 * Created by Admin on 15.3.18.
 */

@Database(entities = {Project.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    //===========================================

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "githubprojects_db")
                            .build();
        }
        return INSTANCE;
    }

    //===========================================



    public abstract ProjectDao projectDao();


}
