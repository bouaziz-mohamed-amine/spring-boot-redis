package com.example.demo.repository;


import com.example.demo.PostOccurrence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostOccurrenceRepository {

    public static final String HASH_KEY = "PostOccurrence";

    @Autowired
    private RedisTemplate template;

    public PostOccurrence save(PostOccurrence post){
        template.opsForHash().put(HASH_KEY,post.id,post);
        return post;
    }

    public List<PostOccurrence> findAll(){
        return template.opsForHash().values(HASH_KEY);
    }

    public PostOccurrence findPostById(int id){
        return (PostOccurrence) template.opsForHash().get(HASH_KEY,id);
    }

}
