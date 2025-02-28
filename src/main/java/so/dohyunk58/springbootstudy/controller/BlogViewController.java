package so.dohyunk58.springbootstudy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import so.dohyunk58.springbootstudy.domain.Article;
import so.dohyunk58.springbootstudy.dto.ArticleListViewResponse;
import so.dohyunk58.springbootstudy.dto.ArticleViewResponse;
import so.dohyunk58.springbootstudy.service.BlogService;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {
    private final BlogService blogService;
    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();

        model.addAttribute("articles", articles);

        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));
        return "article";
    }

    // 생성, 수정 화면을 위한 뷰 컨틀롤러
    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        //  id가 없는 경우 글을 생성한다
        if (id == null) {
            model.addAttribute("article", new ArticleViewResponse());
        } else { // id가 있는 경우 id의 글을 수정한다
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }

        return "newArticle";
    }
}
