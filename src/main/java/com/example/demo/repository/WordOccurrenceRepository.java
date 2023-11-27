package com.example.demo.repository;

import com.example.demo.WordOccurrence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WordOccurrenceRepository{

    public static final String HASH_KEY = "WordOccurrence";

    @Autowired
    private RedisTemplate template;

    public WordOccurrence save(WordOccurrence word){
        template.opsForHash().put(HASH_KEY,word.word,word);
        return word;
    }

    public List<WordOccurrence> findAll(){
        return template.opsForHash().values(HASH_KEY);
    }

    public WordOccurrence findWordByWord(String word){
        return (WordOccurrence) template.opsForHash().get(HASH_KEY,word);
    }

}
