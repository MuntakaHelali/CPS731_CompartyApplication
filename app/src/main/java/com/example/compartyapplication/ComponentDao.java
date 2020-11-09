package com.example.compartyapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ComponentDao
{
    @Insert
    void insert (Component component);

    @Update
    void update (Component component);

    @Delete
    void delete (Component component);

    @Query("SELECT * FROM component_table ORDER BY price DESC")
    LiveData<List<Component>> getAllComponents();

}
