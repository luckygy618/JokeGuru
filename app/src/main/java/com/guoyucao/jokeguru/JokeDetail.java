package com.guoyucao.jokeguru;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class JokeDetail extends AppCompatActivity {
    TextView id, tag, content;
    Button back, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_detail);
        id = findViewById(R.id.joke_detail_id);
        tag = findViewById(R.id.joke_detail_tag);
        content = findViewById(R.id.joke_detail_content);
        // back = findViewById(R.id.back);
        //  delete = findViewById(R.id.delete);

        String n = getIntent().getExtras().getString("id");
        String p = getIntent().getExtras().getString("tag");
        String d = getIntent().getExtras().getString("content");
        id.setText(n);
        tag.setText(p);
        content.setText(d);
    }
}