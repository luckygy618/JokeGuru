package com.guoyucao.jokeguru.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface JokeDAO {

    @Insert
    void addJokeToDB(JokeEntity joke);

    @Delete
    void delete(JokeEntity joke);

    @Query("SELECT * FROM JokeEntity")
    List<JokeEntity> getAll();

    @Query("DELETE FROM JokeEntity")
    void deleteAll();

    @Query("DELETE FROM JokeEntity WHERE joke_id = :id")
    void deleteById(String id);

}
