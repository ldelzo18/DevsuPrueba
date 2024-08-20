package com.devsu.apirest2.repository;

import com.devsu.apirest2.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findAccountByAccountNumber(Long accountNumber);
}
