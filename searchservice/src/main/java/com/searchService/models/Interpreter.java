package com.searchService.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Interpreter {
    private String id;
    private String name;
    private List<String> genres;
    private int popularity;

    public void setGenres(String[] genres) {
        List<String> temp = new ArrayList<>();
        for(String genre: genres) {
            temp.add(genre);
        }
        this.genres = temp;
    }
}
