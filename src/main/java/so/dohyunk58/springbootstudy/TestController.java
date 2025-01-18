package so.dohyunk58.springbootstudy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test") // 다음 주소로 Get요청시 실행한다
    public String test() {
        return "Hello World";
    }
}
