package com.guoyucao.jokeguru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.guoyucao.jokeguru.database.DatabaseManager;
import com.guoyucao.jokeguru.database.JokeEntity;

public class SavedJokesActivity extends AppCompatActivity  {
    RecyclerView recyclerView;
    JokeAdapter jokeAdapter;
    JokeEntity jokeEntity;
    DatabaseManager dbManager;

    JokeAdapter.ItemListener jokeListener = new JokeAdapter.ItemListener() {
        @Override
        public void onItemClicked(Joke item, int position) {
            Intent myIntent = new Intent(SavedJokesActivity.this, JokeDetail.class);// messaging object
            myIntent.putExtra("id",  item.getId());
            myIntent.putExtra("tag",  item.getCategory());
            myIntent.putExtra("content",  item.getJoke());
            startActivity(myIntent);
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_list);

        recyclerView = findViewById(R.id.jokeList);
        jokeAdapter = new JokeAdapter(this,((MyApp)getApplication()).getSavedJokes());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(jokeAdapter);

        jokeAdapter.setItemListener(jokeListener);

    }




}