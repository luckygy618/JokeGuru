package com.guoyucao.jokeguru;

import android.app.Application;

import com.guoyucao.jokeguru.api.DataService;
import com.guoyucao.jokeguru.api.JsonService;
import com.guoyucao.jokeguru.database.DatabaseManager;

import java.util.ArrayList;

public class MyApp extends Application {

    private final DataService dataService = new DataService();
    private final JsonService jsonService = new JsonService();
    ArrayList<Joke> jokes;
    ArrayList<Joke> savedJokes;
    DatabaseManager dbManager = new DatabaseManager();

    public MyApp() {
        jokes = new ArrayList<>();
        savedJokes = new ArrayList<>();
    }

    public DatabaseManager getDbManager() {
        return dbManager;
    }

    @Override
    public void onCreate() {
        super.onCreate();
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
