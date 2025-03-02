package so.dohyunk58.springbootstudy.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import so.dohyunk58.springbootstudy.domain.User;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TokenProvider {

    // application.properties에서 설정한 정보를 가져온다.
    private final JwtProperties jwtProperties;

    public String generateToken(User user, Duration expiredAt) {
        Date now = new Date();
        return makeToken(new Date(now.getTime() + expiredAt.toMillis()), user);
    }

    // JWT 토큰 생성
    private String makeToken(Date expiry, User user) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // 헤더 typ : JWT
                .setIssuer(jwtProperties.getIssuer()) // 내용 iss(발급자)
                .setIssuedAt(now) // iat(발급일시) : 현재시간
                .setExpiration(expiry) // exp(만료일시)
                .setSubject(user.getEmail()) // sub(토큰제목)
                .claim("id", user.getId()) // 클레임(유저id)
                // 서명, 비밀값을 암호화함
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    // JWT 토큰 유효성 검증
    public boolean validToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey()) // 비밀값으로 보호화
                    .parseClaimsJws(token);

            return true;
        } catch (Exception e) { // 복호화가 안되면 유효하지 않은 토큰
            return false;
        }
    }

    // 토큰 기반 인증 정보를 가져오기
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token); // 클레임을 가져온다
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(new org.springframework.security.core.userdetails.User(claims.getSubject
                (), "", authorities), token, authorities);
    }

    // 토큰 기반 유저 ID 가져오기
    public Long getUserId(String token) {
        Claims claims = getClaims(token);
        return claims.get("id", Long.class);
    }

    private Claims getClaims(String token) {
        return Jwts.parser() // 클레임 조회
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }
}
