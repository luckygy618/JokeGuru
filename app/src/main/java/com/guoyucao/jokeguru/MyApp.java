package com.guoyucao.jokeguru;

import android.app.Application;

import com.guoyucao.jokeguru.api.DataService;
import com.guoyucao.jokeguru.api.JsonService;
import com.guoyucao.jokeguru.database.DatabaseManager;
import com.guoyucao.jokeguru.database.JokeEntity;

import java.util.ArrayList;

public class MyApp extends Application {

    ArrayList<Joke> jokes;
    ArrayList<Joke> savedJokes;
    Joke test = new Joke("This is a test joke 666.", "Programming", "001");

    DatabaseManager dbManager = new DatabaseManager();
    JokeEntity mainJokeEntity = new JokeEntity();
    private final DataService dataService = new DataService();
    private final JsonService jsonService = new JsonService();

    public MyApp() {
        jokes = new ArrayList<Joke>();
        savedJokes = new ArrayList<Joke>();
    }

    public DatabaseManager getDbManager() {
        return dbManager;
    }

    public void setDbManager(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public JokeEntity getMainJokeEntity() {
        return mainJokeEntity;
    }

    public void setMainJokeEntity(JokeEntity mainJokeEntity) {
        this.mainJokeEntity = mainJokeEntity;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public ArrayList<Joke> getSavedJokes() {
        return savedJokes;
    }

    public void setSavedJokes(ArrayList<Joke> savedJokes) {
        this.savedJokes = savedJokes;
    }

    public ArrayList<Joke> getJokes() {
        return jokes;
    }

    public void setJokes(ArrayList<Joke> jokes) {
        this.jokes = jokes;
    }

    public JsonService getJsonService() {
        return jsonService;
    }


    public DataService getDataService() {
        return dataService;
    }


}
