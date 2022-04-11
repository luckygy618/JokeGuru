package com.guoyucao.jokeguru;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

import com.guoyucao.jokeguru.api.DataService;
import com.guoyucao.jokeguru.api.JsonService;
import com.guoyucao.jokeguru.database.DatabaseManager;
import com.guoyucao.jokeguru.database.JokeEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DataService.NetworkingListener, DatabaseManager.DatabaseListener {

    FragmentContainerView fragmentView;
    JokeFragment joke_fragment;
    FragmentManager fragManager;
    Button prev, next, save, newJokes;
    ProgressBar proBar;
    int position;
    int maxJokeSize;
    int currentSize;
    DataService dataManager;
    JsonService jsonService;
    JokeEntity myJokeEntity;
    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragManager = getSupportFragmentManager();
        fragmentView = findViewById(R.id.fragment_1);
        jsonService = ((MyApp) getApplication()).getJsonService();
        dataManager = ((MyApp) getApplication()).getDataService();
        dataManager.listener = this;
        position = 0;
        proBar = findViewById(R.id.progressBar);
        proBar.setMax(10);
        proBar.setProgress(position + 1);
        newJokes = findViewById(R.id.newJoke);
        prev = findViewById(R.id.prev);
        next = findViewById(R.id.next);
        save = findViewById(R.id.save);
        dbManager = ((MyApp) getApplication()).getDbManager();
        dbManager.getDb(this);
        dbManager.listener = this;


        newJokes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataManager.getAnyJokes();
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((MyApp) getApplication()).getJokes().size() == 0) {
                    return;
                }
                position--;
                if (position < 0) {
                    position = 9;
                }
                Joke joke = ((MyApp) getApplication()).getJokes().get(position);
                setFragmentView(joke);
                proBar.setProgress(position + 1);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((MyApp) getApplication()).getJokes().size() == 0) {
                    return;
                }
                position++;
                if (position > 9) {
                    position = 0;
                }
                Joke joke = ((MyApp) getApplication()).getJokes().get(position);
                setFragmentView(joke);
                proBar.setProgress(position + 1);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((MyApp) getApplication()).getJokes().size() == 0) {
                    return;
                }
                Joke currentJoke = ((MyApp) getApplication()).getJokes().get(position);
                JokeEntity joke = new JokeEntity(currentJoke.getId(), currentJoke.getCategory(), currentJoke.getJoke());
                dbManager.saveNewJoke(joke);
            }
        });


        JokeFragment tmp = new JokeFragment(new Joke("NA", "NA", "Click \"More\" button to get jokes."));
        fragManager.beginTransaction().replace(R.id.fragment_1, tmp).commit();
        // Log.d("onCreate: ","called onCreate function");
// android:launchMode="singleInstance"
        // dataManager.getAnyJokes();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume", "onResume is ca;;ed 6666666666");
        dbManager.getDb(this);
        dbManager.listener = this;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu1:
                //   dbManager.getAllJokes();
                Intent myIntent = new Intent(this, SavedJokesActivity.class);// messaging object
                startActivity(myIntent);
                break;
            case R.id.menu2:
                dbManager.deleteAll();
                break;
        }
        return true;
    }


    public void setFragmentView(Joke joke) {
        // JokeFragment frag1 = new JokeFragment(new Joke("This is a test joke 666.","Programming","001"));
        JokeFragment frag1 = new JokeFragment(joke);
        fragManager.beginTransaction().replace(R.id.fragment_1, frag1).commit();
    }

    @Override
    public void dataListener(String json) {
        position = 0;
        ArrayList<Joke> jokes = jsonService.getJokesFromJSON(json);
        ((MyApp) getApplication()).setJokes(jokes);
        setFragmentView(jokes.get(0));
        proBar.setProgress(position + 1);
    }


    @Override
    public void onListReady(List<JokeEntity> list) {

    }

    @Override
    public void onAddDone() {
        Toast.makeText(getApplicationContext(), getString(R.string.added), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDeleteDone(List<JokeEntity> list) {
        // Toast.makeText(getApplicationContext(), getString(R.string.deleteString), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDeleteAllDone() {
        Toast.makeText(getApplicationContext(), getString(R.string.deleteString), Toast.LENGTH_SHORT).show();
    }


}