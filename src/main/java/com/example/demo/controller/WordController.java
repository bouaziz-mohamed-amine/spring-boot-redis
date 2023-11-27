package com.example.demo.controller;


import com.example.demo.PostOccurrence;
import com.example.demo.WordOccurrence;
import com.example.demo.service.WordService;
import com.example.demo.repository.PostOccurrenceRepository;
import com.example.demo.repository.WordOccurrenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "rest/api/occurence/")
public class WordController {

    @Autowired
    WordService wordService;
    @Autowired
    WordOccurrenceRepository wordRepository;

    @Autowired
    PostOccurrenceRepository postRepository;


    @GetMapping("word")
    public WordOccurrence getTotalOfWord(@RequestParam String q){
       return wordService.getTotalOfWord(q);
    }

    @GetMapping()
    public List<WordOccurrence> getlist(){
        return wordRepository.findAll();
    }

    @GetMapping("all")
    public List<PostOccurrence> getlistPosts(){
        return postRepository.findAll();
    }

    @GetMapping("post")
    public PostOccurrence getTotalOfWordByPost(@RequestParam int id){
       return wordService.sumOfAllOccurrences(id);
    }
}
