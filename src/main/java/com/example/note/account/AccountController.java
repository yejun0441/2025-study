package com.example.note.account;

import com.example.note.account.dto.LoginDto;
import com.example.note.account.dto.SignUpDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody SignUpDto signUpDto) {
        accountService.signUP(signUpDto);
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpServletRequest request) {
        Account account = accountService.login(loginDto);
        if (account == null) {
            return ResponseEntity.ok("로그인 정보가 일치하지 않습니다.");
        }
        HttpSession session = request.getSession(true);
        session.setAttribute("accountId", account.getAccountId());
        session.setAttribute("userName", account.getUserName());
        session.setAttribute("userId", account.getUserId());

        return ResponseEntity.ok("로그인 되었습니다.");
    }
    @GetMapping("/me")
    public ResponseEntity<?> me(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null) {return ResponseEntity.status(401). body("로그인 필요");}
        Object accountId = session.getAttribute("accountId");
        Object userName = session.getAttribute("userName");
        Object userId = session.getAttribute("userId");

        return ResponseEntity.ok(Map.of("accountId", accountId ,"userName", userName,"userId",userId));
    }
    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        jakarta.servlet.http.Cookie cookie = new Cookie("userName", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return ResponseEntity.ok("로그아웃 성공");
    }
}
