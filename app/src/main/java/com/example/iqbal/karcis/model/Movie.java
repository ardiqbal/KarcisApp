package com.example.iqbal.karcis.model;

public class Movie {
    private String name;
    private int image;
    private String overview;
    private Boolean type;

    public Movie(String name, int image, String overview, Boolean type) {
        this.name = name;
        this.image = image;
        this.overview = overview;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }
}
