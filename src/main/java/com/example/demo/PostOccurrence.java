package com.example.demo;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
@RedisHash("PostOccurrence")
public class PostOccurrence implements Serializable {

    public int id;
    public int occurences;

    public PostOccurrence(int id, int occurences) {
        this.id = id;
        this.occurences = occurences;
    }

    public PostOccurrence() {
    }
}
