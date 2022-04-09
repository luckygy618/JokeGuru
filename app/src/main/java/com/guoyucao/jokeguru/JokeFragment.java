package com.guoyucao.jokeguru;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class JokeFragment extends Fragment {

    private String jokeStr;
    private String id;
    private String category;
    private TextView jokeText;
    private TextView jokeID;
    private TextView jokeCategory;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        jokeText = view.findViewById(R.id.joke_detail);
        jokeID=view.findViewById(R.id.joke_id);
        jokeCategory = view.findViewById(R.id.joke_category);
        jokeText.setText(jokeStr);
        jokeID.setText("ID: "+id);
        jokeCategory.setText("Tag: "+category);
        return view;
    }

    public JokeFragment(Joke jokeObj) {
        this.jokeStr = jokeObj.getJoke();
        this.id = jokeObj.getId();
        this.category=jokeObj.getCategory();

    }


    public JokeFragment() {
    }




}
