package com.devsu.apirest2.util.mapper;

import com.devsu.apirest2.dto.transaction.TransactionDTO;
import com.devsu.apirest2.model.Account;
import com.devsu.apirest2.model.Transaction;
import com.devsu.apirest2.util.enums.EnumTransactionType;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TransactionMapper {
    public static TransactionDTO transactionToTransactionDTO(Transaction transaction){
        return TransactionDTO.builder().transactionId(transaction.getTransactionId())
                .accountNumber(transaction.getAccount().getAccountNumber())
                .transactionType(EnumTransactionType.fromString(transaction.getTransactionType()))
                .initialBalance(transaction.getInitialBalance())
                .value(transaction.getValue())
                .date(transaction.getDate().withZoneSameInstant(ZoneId.of("America/Lima")))
                .availableBalance(transaction.getBalance())
                .build();
    }

    public static Transaction transactionDTOToTransaction(TransactionDTO transactionDTO, Account account){
        return Transaction.builder().date(ZonedDateTime.now(ZoneId.of("UTC")))
                .transactionType(transactionDTO.getTransactionType().name())
                .value(transactionDTO.getValue())
                .initialBalance(transactionDTO.getInitialBalance())
                .balance(transactionDTO.getAvailableBalance())
                .account(account)
                .build();
    }
}
