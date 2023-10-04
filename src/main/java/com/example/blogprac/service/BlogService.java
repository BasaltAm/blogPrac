package com.example.blogprac.service;

import com.example.blogprac.domain.Article;
import com.example.blogprac.domain.User;
import com.example.blogprac.dto.AddArticleRequest;
import com.example.blogprac.dto.UpdateArticleRequest;
import com.example.blogprac.repository.BlogRepository;
import com.example.blogprac.repository.SearchRepository;
import com.example.blogprac.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;
   private final UserRepository userRepository;

    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }
    public List<Article> findAll(){
        return blogRepository.findAll();
    }
    public Article findById(long id){
        return blogRepository.findById(id).orElseThrow(() -> new IllegalAccessError("not found:" +id));
    }
    public void delete(long id){
        blogRepository.deleteById(id);
    }
    @Transactional
    public Article update(long id, UpdateArticleRequest request){
        Article article = blogRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("not found:" +id));
        article.update(request.getTitle(), request.getContent());
        return article;
    }
}
