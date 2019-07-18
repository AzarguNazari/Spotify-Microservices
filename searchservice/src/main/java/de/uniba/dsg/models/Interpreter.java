package de.uniba.dsg.models;

import java.util.ArrayList;
import java.util.List;

public class Interpreter {
    private String id;
    private String name;
    private List<String> genres;
    private int popularity;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        List<String> temp = new ArrayList<>();
        for(String genre: genres) {
            temp.add(genre);
        }
        this.genres = temp;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
}
