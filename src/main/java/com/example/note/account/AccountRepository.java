package com.example.note.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findByUserIdAndPassword(String userId, String password);

    Account findByAccountId(Long accountId);

    Account findByUserId(String userId);

    Account findAccountIdByUserId(String userId);

    Account findAccountIdByAccountId(Long accountId);
}
