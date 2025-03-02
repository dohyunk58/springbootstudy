package so.dohyunk58.springbootstudy.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties("jwt") // 설정의 jwt 값과 매핑됨
public class JwtProperties {
    private String issuer;
    private String secretKey;
}