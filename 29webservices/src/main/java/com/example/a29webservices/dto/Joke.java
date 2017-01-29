package com.example.a29webservices.dto;

import java.util.ArrayList;

/**
 * Created by DELL on 29-01-2017.
 */

public class Joke {

    private String type;
    private ArrayList<JokeInfo> value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<JokeInfo> getValue() {
        return value;
    }

    public void setValue(ArrayList<JokeInfo> value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Joke{" +
                "type='" + type + '\'' +
                ", value=" + value +
                '}';
    }
}
