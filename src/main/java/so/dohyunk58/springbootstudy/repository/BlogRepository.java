package so.dohyunk58.springbootstudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import so.dohyunk58.springbootstudy.domain.Article;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
