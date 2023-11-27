package com.example.demo;

import lombok.Data;



@Data
public class Post {


private long id;
private String title;
private String body;
private long userId;



    public Post(long id, String title,String body,Long userId) {
        this.id = id;
        this.title = title;
        this.body= body;
        this.userId= userId;
    }

    public Post() {
    }
}
