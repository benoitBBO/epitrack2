package org.example.exposition.tmdb.dto;

public class GenreDto {
    public int id;
    public String name;

    public GenreDto() {
    }

    public GenreDto(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
