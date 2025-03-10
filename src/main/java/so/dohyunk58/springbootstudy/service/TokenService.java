package so.dohyunk58.springbootstudy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import so.dohyunk58.springbootstudy.config.jwt.TokenProvider;
import so.dohyunk58.springbootstudy.domain.User;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createNewAccessToken(String refreshToken) {
        if(!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }

        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findById(userId);

        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}
