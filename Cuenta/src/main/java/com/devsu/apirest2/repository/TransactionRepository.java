package com.devsu.apirest2.repository;

import com.devsu.apirest2.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByDateBetweenAndAccount_AccountNumber(ZonedDateTime start, ZonedDateTime end, Long accountNumber);

    List<Transaction> findByAccount_Customer_CustomerIdAndDateBetweenOrderByDateAsc(Long customerId, ZonedDateTime startDate, ZonedDateTime endDate);
}