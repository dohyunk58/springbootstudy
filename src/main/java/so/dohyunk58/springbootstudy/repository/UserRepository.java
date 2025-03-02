package so.dohyunk58.springbootstudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import so.dohyunk58.springbootstudy.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // 고유한 값인 email로 사용자 정보를 가져온다.
}
