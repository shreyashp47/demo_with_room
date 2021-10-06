package com.shreyash.github_api.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.shreyash.github_api.models.APIUsers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {APIUsers.class}, version = 3, exportSchema = false)
public abstract class UsersDatabase extends RoomDatabase {
    public abstract UserDao daoUser();



    private static volatile UsersDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static UsersDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UsersDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(),
                                    UsersDatabase.class, "user_database.db")
                                    .fallbackToDestructiveMigration()
                                    .allowMainThreadQueries()
                                    .build();
                }
            }
        }
        return INSTANCE;
    }
}
