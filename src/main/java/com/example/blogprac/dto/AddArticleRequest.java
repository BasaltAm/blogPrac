package com.example.blogprac.dto;
import com.example.blogprac.domain.Article;
import com.example.blogprac.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddArticleRequest {
    private String title;
    private String content;
   public Article toEntity(){
       return Article.builder()
               .title(title)
               .content(content)
               .build();
   }
}
