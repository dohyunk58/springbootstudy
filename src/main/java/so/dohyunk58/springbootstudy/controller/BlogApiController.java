package so.dohyunk58.springbootstudy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import so.dohyunk58.springbootstudy.domain.Article;
import so.dohyunk58.springbootstudy.dto.AddArticleRequest;
import so.dohyunk58.springbootstudy.dto.ArticleResponse;
import so.dohyunk58.springbootstudy.dto.UpdateArticleRequest;
import so.dohyunk58.springbootstudy.service.BlogService;

import java.util.List;

@RequiredArgsConstructor
@RestController // HTTP Response Body에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러 애너테이션
public class BlogApiController {
    private final BlogService blogService;

    // HTTP 메서드가 POST일 때, 전달받은 URL이 동일하면 아래 메서드와 매핑
    @PostMapping("/api/articles")
    // @RequestBody로 요청 본문 값 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request); // blogService에 request를 저장하고 반환한 값을 savedArticle에 저장함

        // 요청한 자원이 성공적으로 생성되었으며 저장된 블로그 글 정보를 응답 객체에 담아 전송
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle); // blogService에 저장했던 Article 객체를 담은 savedArticle을 전송
    }

    // HTTP 메서드가 GET일 때 ArticleResponse의 List를 전부 조회해 반환한다.
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(articles);
    }

    // id로 글 하나를 GET 요청할 때 Article의 요소를 반환한다.
    @GetMapping("/api/articles/{id}")
    // url에서 id 값 추출
    public ResponseEntity<ArticleResponse> findAricle(@PathVariable long id) {
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    // id로 DELETE 요청시 delete 메소드 호출
    @DeleteMapping("api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    // id로 원하는 글의 title과 content를 수정하는 update() 메소드 호출
    @PutMapping("api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id,
                     @RequestBody UpdateArticleRequest request) {
        Article updatedArticle = blogService.update(id, request);

        return ResponseEntity.ok()
                .body(updatedArticle);
    }
}
