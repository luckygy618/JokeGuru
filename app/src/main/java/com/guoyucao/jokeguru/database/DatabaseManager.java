package com.guoyucao.jokeguru.database;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.room.Room;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseManager {

    public interface DatabaseListener {
        void onListReady (List<JokeEntity> list);
        void onAddDone();
        void onDeleteDone();
    }

    public DatabaseListener listener;

    static JokeDatabase db;
    ExecutorService databaseExecutor = Executors.newFixedThreadPool(4);
    Handler mainThread_Handler = new Handler(Looper.getMainLooper());


    private static void buildDBInstance(Context context){
        db = Room.databaseBuilder(context,
                JokeDatabase.class, "joke_db").build();
    }


    public JokeDatabase getDb(Context context){
        if (db == null) {
            buildDBInstance(context);
        }
        return db;
    }


    public void saveNewJoke(JokeEntity newJoke){
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                db.jokeDao().addJokeToDB(newJoke);
                mainThread_Handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onAddDone();
                    }
                });
            }
        });
    }


    public  void getAllJokes(){
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                List<JokeEntity> list =  db.jokeDao().getAll();
                mainThread_Handler.post(new Runnable() { // go to main thread
                    @Override
                    public void run() {
                        listener.onListReady(list);
                    }
                });
            }
        });

    }

    public void deleteJoke(JokeEntity del){
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                db.jokeDao().deleteJoke(del);
                mainThread_Handler.post(new Runnable() { // go to main thread
                    @Override
                    public void run() {
                        listener.onDeleteDone();
                    }
                });
            }
        });

    }

    public void deleteAll(){
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                db.jokeDao().deleteAll();
                mainThread_Handler.post(new Runnable() { // go to main thread
                    @Override
                    public void run() {
                        listener.onDeleteDone();
                    }
                });
            }
        });

    }


}
