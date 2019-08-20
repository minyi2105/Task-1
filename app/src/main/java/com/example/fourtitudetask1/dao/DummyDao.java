package com.example.fourtitudetask1.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fourtitudetask1.model.Dummy;

import java.util.List;

@Dao
public interface DummyDao {
    //check if data created has the same ID with another record, should it be remained or replaced
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Dummy dummy);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllDummies(List<Dummy> dummies);

    @Query("SELECT * FROM dummy_table ORDER BY id ASC")
    List<Dummy> getAllDummies();

    @Query("SELECT * FROM dummy_table WHERE id =:id")
    Dummy getDummy(int id);

    @Update
    void update(Dummy dummy);

    @Delete
    void delete(Dummy dummy);
}

