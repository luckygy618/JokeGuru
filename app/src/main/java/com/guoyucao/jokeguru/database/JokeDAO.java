package com.guoyucao.jokeguru.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.guoyucao.jokeguru.database.JokeEntity;

import java.util.List;

@Dao
public interface JokeDAO {

    @Insert
    void addJokeToDB(JokeEntity newDonation);

    @Delete
    void deleteJoke(JokeEntity toDeleteDonation);

    @Query("SELECT * FROM JokeEntity")
    List<JokeEntity> getAll();

    @Query("DELETE FROM JokeEntity")
    void deleteAll();



}
