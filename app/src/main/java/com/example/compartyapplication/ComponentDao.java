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

    @Query("DELETE FROM component_table WHERE productType = 'cpu'")
    void deleteAllCPUS();

    @Query("DELETE FROM component_table WHERE productType = 'gpu'")
    void deleteAllComponents();

    @Query("DELETE FROM component_table WHERE productType = 'ram'")
    void deleteAllRam();

    @Query("DELETE FROM component_table WHERE productType = 'motherboard'")
    void deleteAllMotherboard();

    @Query("DELETE FROM component_table WHERE productType = 'case'")
    void deleteAllCases();

    @Query("DELETE FROM component_table WHERE productType = 'powerSupply'")
    void deleteAllPowerSupply();

    @Query("SELECT * FROM component_table")
    LiveData<List<Component>> getAllComponents();

    @Query("SELECT * FROM component_table WHERE productType = 'cpu' ORDER BY price DESC")
    LiveData<List<Component>> getAllCPUs();

    @Query("SELECT * FROM component_table WHERE productType = 'gpu' ORDER BY price DESC")
    LiveData<List<Component>> getAllGPUs();

    @Query("SELECT * FROM component_table WHERE productType = 'ram' ORDER BY price DESC")
    LiveData<List<Component>> getAllRAMs();

    @Query("SELECT * FROM component_table WHERE productType = 'motherboard' ORDER BY price DESC")
    LiveData<List<Component>> getAllMOTHERBOARDS();

    @Query("SELECT * FROM component_table WHERE productType = 'case' ORDER BY price DESC")
    LiveData<List<Component>> getAllCASES();

    @Query("SELECT * FROM component_table WHERE productType = 'powerSupply' ORDER BY price DESC")
    LiveData<List<Component>> getAllPOWERSUPPLY();
}
