package so.dohyunk58.springbootstudy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import so.dohyunk58.springbootstudy.domain.Article;
import so.dohyunk58.springbootstudy.dto.AddArticleRequest;
import so.dohyunk58.springbootstudy.dto.UpdateArticleRequest;
import so.dohyunk58.springbootstudy.repository.BlogRepository;

import java.util.List;

@RequiredArgsConstructor // final or @NotNull인 필드의 생성자 추가
@Service // 빈 등록, 컴포넌트 스캔을 위한 것
public class BlogService {
    private final BlogRepository blogRepository;

    // 블로그 글 Create 메서드 = save()
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    // 모든 글을 조회하는 findAll()
    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    // 글 하나를 조회하는 findById()
    public Article findById(long id) {
        return blogRepository.findById(id)
                // id가 없는 경우 예외 처리
                .orElseThrow(() -> new IllegalArgumentException("not found: "+id));
    }

    // 글 삭제 delete(id)
    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    // 글 수정 update(id, request)
    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }
}