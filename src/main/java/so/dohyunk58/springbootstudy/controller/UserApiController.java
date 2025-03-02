package so.dohyunk58.springbootstudy.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import so.dohyunk58.springbootstudy.dto.AddUserRequest;
import so.dohyunk58.springbootstudy.service.UserService;

@RequiredArgsConstructor
@Controller
public class UserApiController {
    private final UserService userService;

    // 회원가입
    @PostMapping("/user")
    public String signup(AddUserRequest request) {
        userService.save(request    ); // 회원가입 메서드
        return "redirect:/login"; // 회원가입 후 /login으로 이동
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }
}
