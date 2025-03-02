package so.dohyunk58.springbootstudy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import so.dohyunk58.springbootstudy.domain.User;
import so.dohyunk58.springbootstudy.dto.AddUserRequest;
import so.dohyunk58.springbootstudy.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    // 비밀번호 암호화에 사용
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto) {
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                // 비밀번호 암호화
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }
}