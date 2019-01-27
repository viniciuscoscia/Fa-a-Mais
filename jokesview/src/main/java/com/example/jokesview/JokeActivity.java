package com.example.jokesview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.jokesview.R;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE = "jokeIntent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        String joke = getIntent().getStringExtra(JOKE);

        ((TextView) findViewById(R.id.tv_joke)).setText(joke);
    }
}
