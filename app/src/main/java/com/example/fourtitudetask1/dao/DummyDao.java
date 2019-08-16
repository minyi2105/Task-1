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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Dummy dummy);

    @Query("SELECT * FROM dummy_table ORDER BY id DESC")
    List<Dummy> getAllDummies();

    @Query("SELECT * FROM dummy_table WHERE id =:id")
    Dummy getDummy(int id);

    @Update
    void update(Dummy dummy);

    @Delete
    void delete(Dummy dummy);

//    @Query("DELETE FROM dummy_table")
//    void deleteAll();
//
//    @Query("SELECT * from dummy_table ORDER BY title ASC")
//    LiveData<List<Dummy>> getAllDummies();
//
//    @Query("UPDATE dummy_table SET subtitle = :subtitle, description = :description, image_url = :imageUrl WHERE title =:title")
//    void update(String title, String subtitle, String description, String imageUrl);
}

