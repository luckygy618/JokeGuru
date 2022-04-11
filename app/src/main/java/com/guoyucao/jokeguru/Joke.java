package com.guoyucao.jokeguru;

public class Joke {


    private String id;
    private String tag;
    private String joke;

    public Joke(String id, String category, String joke) {
        this.joke = joke;
        this.tag = category;
        this.id = id;
    }

    public Joke() {
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public String getCategory() {
        return tag;
    }

    public void setCategory(String category) {
        tag = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
