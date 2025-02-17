package so.dohyunk58.springbootstudy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import so.dohyunk58.springbootstudy.domain.Article;
import so.dohyunk58.springbootstudy.dto.AddArticleRequest;
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
}