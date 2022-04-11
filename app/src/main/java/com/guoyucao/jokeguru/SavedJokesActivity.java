package com.guoyucao.jokeguru;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guoyucao.jokeguru.database.DatabaseManager;
import com.guoyucao.jokeguru.database.JokeEntity;

import java.util.ArrayList;
import java.util.List;

public class SavedJokesActivity extends AppCompatActivity implements DatabaseManager.DatabaseListener {
    RecyclerView recyclerView;
    JokeAdapter jokeAdapter;
    DatabaseManager dbManager;

    ItemTouchHelper itemTouchHelper;
    ArrayList<Joke> savedJokes;
    JokeAdapter.ItemListener jokeListener = new JokeAdapter.ItemListener() {
        @Override
        public void onItemClicked(Joke item, int position) {
            Intent myIntent = new Intent(SavedJokesActivity.this, JokeDetail.class);// messaging object
            myIntent.putExtra("id", item.getId());
            myIntent.putExtra("tag", item.getCategory());
            myIntent.putExtra("content", item.getJoke());
            startActivity(myIntent);
        }
    };
    ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
            new ItemTouchHelper.SimpleCallback(
                    0, ItemTouchHelper.LEFT) {

                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                    //Remove swiped item from list and notify the RecyclerView
                    int position = viewHolder.getAdapterPosition();
                    JokeEntity je = new JokeEntity(jokeAdapter.getJokeList().get(position));
                    dbManager.deleteJoke(je);
                    savedJokes = new ArrayList<>();
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_list);
        savedJokes = new ArrayList<>();
        recyclerView = findViewById(R.id.jokeList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbManager = ((MyApp) getApplication()).getDbManager();
        dbManager.getDb(this);
        dbManager.listener = this;
        dbManager.getAllJokes();

        itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        // jokeAdapter = new JokeAdapter(this,savedJokes);
        //  recyclerView.setAdapter(jokeAdapter);
        //  jokeAdapter.setItemListener(jokeListener);

        /*
       jokeAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
         public void onChanged() {
                super.onChanged();
             ArrayList<JokeEntity> jokeEntities = new ArrayList<>();
             for (Joke j : savedJokes) {
                 JokeEntity newJoke = new JokeEntity(j);
                 jokeEntities.add(newJoke);
             }
             setRows(jokeEntities);
            }
        });
*/

    }

    void setRows(List<JokeEntity> list) {
        ArrayList<Joke> jokes = new ArrayList<>();
        for (JokeEntity j : list) {
            jokes.add(j.getJoke());
        }
        savedJokes = jokes;
        jokeAdapter = new JokeAdapter(this, savedJokes);
        recyclerView.setAdapter(jokeAdapter);
        jokeAdapter.setItemListener(jokeListener);
        Toast.makeText(getApplicationContext(), getString(R.string.loaded), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDeleteDone(List<JokeEntity> list) {
        ArrayList<Joke> jokes = new ArrayList<>();
        for (JokeEntity j : list) {
            jokes.add(j.getJoke());
        }
        savedJokes = jokes;
        setRows(list);
        Toast.makeText(getApplicationContext(), getString(R.string.deleteOneString) + "!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onListReady(List<JokeEntity> list) {
        setRows(list);
    }


    @Override
    public void onDeleteAllDone() {
       // Toast.makeText(getApplicationContext(), getString(R.string.deleteString) + "!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAddDone() {
     //   Toast.makeText(getApplicationContext(), getString(R.string.added) + "!", Toast.LENGTH_SHORT).show();
    }

}
