package com.codeup.demo;

public class Post {
    private String title;
    private String body;


    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setBody(String body){
        this.title = body;
    }

    public String getTitle(){
        return this.title;
    }

    public String getBody(){
        return this.body;
    }



}