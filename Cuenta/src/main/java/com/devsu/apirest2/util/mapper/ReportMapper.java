package com.devsu.apirest2.util.mapper;

import com.devsu.apirest2.dto.report.ReportDTO;
import com.devsu.apirest2.model.Transaction;

public class ReportMapper {

    public static ReportDTO transactionToReportDTO(Transaction transaction){
        return ReportDTO.builder().date(transaction.getDate().toLocalDate())
                .client(transaction.getAccount().getCustomer().getName())
                .numberAccount(transaction.getAccount().getAccountNumber())
                .accountType(transaction.getAccount().getAccountType())
                .initialBalance(transaction.getInitialBalance())
                .movementBalance(transaction.getValue())
                .transactionType(transaction.getTransactionType())
                .availableBalance(transaction.getBalance())
                .build();
    }
}
