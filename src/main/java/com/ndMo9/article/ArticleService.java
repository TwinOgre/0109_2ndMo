package com.ndMo9.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> getList() {
        return this.articleRepository.findAll();
    }

    public void create(String subject, String content) {
        Article article = new Article();
        article.setSubject(subject);
        article.setContent(content);
        article.setCreateDate(LocalDateTime.now());

        this.articleRepository.save(article);
    }

    public Article getArticle(Integer id) {
        Optional<Article> oa = this.articleRepository.findById(id);
        if(oa.isEmpty()){
            throw new RuntimeException();
        }

        return oa.get();
    }

    public void modify(Article article, String subject, String content) {
        article.setModifyDate(LocalDateTime.now());
        article.setSubject(subject);
        article.setContent(content);

        this.articleRepository.save(article);
    }

    public void delete(Integer id) {
        this.articleRepository.deleteById(id);
    }
}
