package com.example.demo.service;


import com.example.demo.Post;
import com.example.demo.PostOccurrence;
import com.example.demo.WordOccurrence;
import com.example.demo.repository.PostOccurrenceRepository;
import com.example.demo.repository.WordOccurrenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WordService {

    @Autowired
    WordOccurrenceRepository wordOccurrenceRepository;
    @Autowired
    PostOccurrenceRepository postRepository;
    public WordOccurrence getWordOccurence(String q, Post[] posts) {

        String targetWord = q;
        int wordCount=0;
        for (Post post : posts) {
            String paragraph = post.getBody();
            String[] words = paragraph.split("\\s+");
            for (String word : words) {
                if (word.equals(targetWord)) {
                    wordCount++;
                }
            }
        }
        WordOccurrence wordOccurrence=new WordOccurrence(targetWord,wordCount);
        return wordOccurrenceRepository.save(wordOccurrence);
    }

    public PostOccurrence sumOfAllOccurrences(int id) {

        PostOccurrence postOccurrence = postRepository.findPostById(id);
        if(postOccurrence != null) {
            return  postOccurrence ;
        }
        int wordCount=0;
        Post post = this.getPost(id);
        String[] words = post.getBody().split("\\s+");
        Post[] posts=this.getPosts();
        for (String word : words){
            wordCount+=getWordOccurence(word,posts).occurences;
        }
        postOccurrence = new PostOccurrence(id,wordCount);
        return postRepository.save(postOccurrence);
    }


    public Post[]  getPosts(){
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://jsonplaceholder.typicode.com/posts";
        Post[] posts = restTemplate.getForObject(apiUrl, Post[].class);
        return posts;
    }

    public Post  getPost(int id){
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://jsonplaceholder.typicode.com/posts/"+id;
        Post post = restTemplate.getForObject(apiUrl, Post.class);
        return post;
    }

    public WordOccurrence getTotalOfWord(String q) {
        WordOccurrence wordOccurrence=wordOccurrenceRepository.findWordByWord(q);
        if( wordOccurrence!=null){
            return wordOccurrence;
        }
        Post[] posts=this.getPosts();
        return this.getWordOccurence(q,posts);
    }
}
