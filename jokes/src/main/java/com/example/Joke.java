package com.example;

public class Joke {
    private final static String[] jokes = {
        "Joke #1",
        "Joke #2",
        "Joke #3",
        "Joke #4"
    };
    public String getJoke(){
        return jokes[(int)(Math.random() * jokes.length)];
    }
}
