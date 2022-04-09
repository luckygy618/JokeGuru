package com.guoyucao.jokeguru.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {JokeEntity.class}, version = 1)
public abstract class JokeDatabase extends RoomDatabase {
    public abstract JokeDAO jokeDao();
}
