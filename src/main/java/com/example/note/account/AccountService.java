package com.example.note.account;

import com.example.note.account.dto.LoginDto;
import com.example.note.account.dto.SignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public void signUP(SignUpDto signUpDto) {
        Account account = new Account();
        account.setUserId(signUpDto.getUserId());
        account.setUserName(signUpDto.getUserName());
        account.setPassword(signUpDto.getPassword());
        accountRepository.save(account);
    }

    public Account login(LoginDto loginDto) {
        return accountRepository.findByUserIdAndPassword(loginDto.getUserId(), loginDto.getPassword());
    }
}
    