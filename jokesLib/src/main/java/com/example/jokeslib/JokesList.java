package com.example.jokeslib;

import java.util.ArrayList;
import java.util.Random;

public class JokesList {
    private static ArrayList<String> jokesList = new ArrayList<>();

    private JokesList(){}

    public static String getJoke() {
        if(jokesList == null) {
            jokesList = new ArrayList<String>();
            jokesList.add("Joke1");
            jokesList.add("Joke2");
            jokesList.add("Joke3");
            jokesList.add("Joke4");
        }

        int index = new Random().nextInt(jokesList.size());

        return jokesList.get(index);
    }
}
