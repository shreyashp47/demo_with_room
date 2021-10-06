package com.shreyash.github_api.room;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.shreyash.github_api.models.APIUsers;

import java.util.List;


@Dao
public interface UserDao {


    @Insert(onConflict = REPLACE)
    void addUsers(List<APIUsers> bookingList);

    @Query("SELECT * FROM users   ")
    LiveData<List<APIUsers>> getUsers();

    @Query("DELETE FROM users  ")
    public void delete();
}
