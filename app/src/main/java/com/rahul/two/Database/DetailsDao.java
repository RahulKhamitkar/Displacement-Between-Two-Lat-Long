package com.rahul.two.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DetailsDao {


    @Query("SELECT * FROM details")
    List<Details> getAllDetails();

    @Insert
    void insertAll(Details... details);
}
