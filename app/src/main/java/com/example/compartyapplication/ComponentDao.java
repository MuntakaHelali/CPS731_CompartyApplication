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

    @Query("DELETE FROM component_table WHERE productType = 'gpu'")
    void deleteAllComponents();

    @Query("SELECT * FROM component_table")
    LiveData<List<Component>> getAllComponents();

    @Query("SELECT * FROM component_table WHERE productType = 'cpu' ORDER BY price DESC")
    LiveData<List<Component>> getAllCPUs();

    @Query("SELECT * FROM component_table WHERE productType = 'gpu' ORDER BY price DESC")
    LiveData<List<Component>> getAllGPUs();

    @Query("SELECT * FROM component_table WHERE productType = 'ram' ORDER BY price DESC")
    LiveData<List<Component>> getAllRAMs();
}
