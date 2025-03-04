package so.dohyunk58.springbootstudy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import so.dohyunk58.springbootstudy.domain.RefreshToken;
import so.dohyunk58.springbootstudy.repository.RefreshTokenRepository;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        // 받은 토큰으로 리포지토리를 검색한다. 유효한 토큰을 주어야만 같은 리프레시 토큰을 받을 수 있다.
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(()-> new IllegalArgumentException("Unexpected token"));
    }
}
