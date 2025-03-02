package so.dohyunk58.springbootstudy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import so.dohyunk58.springbootstudy.domain.User;
import so.dohyunk58.springbootstudy.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public User loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException((email)));
    }
}
