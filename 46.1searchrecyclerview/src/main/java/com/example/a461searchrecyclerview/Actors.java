package com.example.a461searchrecyclerview;

/**
 * Created by comp on 3/26/2017.
 */

public class Actors {

    private String names;
    private int actors_id;

    public Actors(String names, int actors_id) {
        this.names = names;
        this.actors_id = actors_id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public int getActors_id() {
        return actors_id;
    }

    public void setActors_id(int actors_id) {
        this.actors_id = actors_id;
    }
}
