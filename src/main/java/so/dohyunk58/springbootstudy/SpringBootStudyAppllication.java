package so.dohyunk58.springbootstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringBootStudyAppllication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootStudyAppllication.class, args);
    }
}
