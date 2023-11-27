package com.example.demo;


import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("WordOccurrence")
public class WordOccurrence implements Serializable {

    public String word;
    public int occurences;

    public WordOccurrence(String word, int occurences) {
        this.word = word;
        this.occurences = occurences;
    }

    public WordOccurrence() {
    }
}
