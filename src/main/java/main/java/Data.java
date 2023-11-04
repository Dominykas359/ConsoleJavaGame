package main.java;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedList;

class Data {
    @JsonProperty("Heroes")
    private LinkedList<Hero> Heroes;
    @JsonProperty("Monsters")
    private LinkedList<Monster> Monsters;

    public LinkedList<Hero> getHeroes() {
        return Heroes;
    }

    public LinkedList<Monster> getMonsters() {
        return Monsters;
    }
}
