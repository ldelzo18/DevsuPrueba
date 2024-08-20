package com.devsu.apirest2.service;

import com.devsu.apirest2.dto.transaction.TransactionDTO;
import com.devsu.apirest2.model.Transaction;

import java.util.List;

public interface TransactionService {
    List<TransactionDTO> getAllTransactions();
    TransactionDTO getTransactionById(Long transactionId);
    TransactionDTO createTransaction(TransactionDTO transactionDTO);

    Transaction findIfTransactionExists(Long transactionId);

}
