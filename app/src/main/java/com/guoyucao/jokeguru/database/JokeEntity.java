package com.guoyucao.jokeguru.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.guoyucao.jokeguru.Joke;

@Entity
public class JokeEntity {
    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "joke_id")
    String jokeId;

    @ColumnInfo(name = "joke_tag")
    String jokeTag;

    @ColumnInfo(name = "joke_content")
    String jokeContent;


    public JokeEntity(String id, String tag, String content) {
        this.jokeId = id;
        this.jokeTag = tag;
        this.jokeContent = content;
    }

    public JokeEntity() {
    }
    public Joke getJoke(){
        return new Joke(jokeId,jokeTag,jokeContent);
    }
}
