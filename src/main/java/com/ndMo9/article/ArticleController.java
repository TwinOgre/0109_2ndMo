package com.ndMo9.article;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Article> articleList = this.articleService.getList();
        model.addAttribute("articleList", articleList);
        return "article_list";
    }

    //  @PreAuthorize()
    @GetMapping("/create")
    public String create(ArticleForm articleForm) {

        return "article_form";
    }

    //  @PreAuthorize()
    @PostMapping("/create")
    public String create(@Valid ArticleForm articleForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "article_form";
        }
        this.articleService.create(articleForm.getSubject(), articleForm.getContent());

        return "redirect:/article/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Article article = this.articleService.getArticle(id);
        model.addAttribute("article", article);
        return "article_detail";
    }

    //  @PreAuthorize()
    @GetMapping("/modify/{id}")
    public String modify(Model model, @PathVariable("id") Integer id) {
        Article article = this.articleService.getArticle(id);
        model.addAttribute("article", article);
        return "article_modify";
    }

    //  @PreAuthorize()
    @PostMapping("/modify/{id}")
    public String modify(Model model, @PathVariable("id") Integer id, @Valid ArticleForm articleForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "article_modify";
        }
        Article article = this.articleService.getArticle(id);

        this.articleService.modify(article, articleForm.getSubject(), articleForm.getContent());
        return String.format("redirect:/article/detail/%s", id);
    }

    //  @PreAuthorize()
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        this.articleService.delete(id);
        return "redirect:/article/list";
    }
}
