package com.devsu.apirest2.service.impl;

import com.devsu.apirest2.dto.account.AccountPatchDTO;
import com.devsu.apirest2.dto.transaction.TransactionDTO;
import com.devsu.apirest2.exception.InsufficientFundsException;
import com.devsu.apirest2.exception.ObjectNotPresentException;
import com.devsu.apirest2.model.Account;
import com.devsu.apirest2.model.Transaction;
import com.devsu.apirest2.repository.TransactionRepository;
import com.devsu.apirest2.service.AccountService;
import com.devsu.apirest2.service.TransactionService;
import com.devsu.apirest2.util.ErrorMessages;
import com.devsu.apirest2.util.enums.EnumTransactionType;
import com.devsu.apirest2.util.mapper.TransactionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);

    private TransactionRepository transactionRepository;
    private AccountService accountService;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountService accountService){
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }

    @Override
    public List<TransactionDTO> getAllTransactions() {
        LOGGER.info("Starting get all transactions request");
        try{
            return transactionRepository.findAll().stream().map(TransactionMapper::transactionToTransactionDTO)
                    .toList();
        } catch (Exception ex){
            LOGGER.error("Exception while processing get all transactions, {}", ex.getMessage());
            return Collections.emptyList();
        } finally {
            LOGGER.info("Finish processing get all transactions request");
        }
    }

    @Override
    public TransactionDTO getTransactionById(Long transactionId) {
        LOGGER.info("Starting get transaction by transactionId request");
        try{
            return TransactionMapper.transactionToTransactionDTO(findIfTransactionExists(transactionId));
        } finally {
            LOGGER.info("Finish processing get transaction by transactionId request");
        }
    }

    @Override
    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        LOGGER.info("Starting create transaction request");
        try {
            Account account = accountService.findAccountIfExists(transactionDTO.getAccountNumber());
            Transaction transaction = validateTransactionBalance(transactionDTO, account);
            return updateAvailableBalance(account, transaction);
        } finally {
            LOGGER.info("Finish processing create transaction request");
        }
    }

    @Override
    public Transaction findIfTransactionExists(Long transactionId) {
        LOGGER.info("Starting find transaction");
        try {
            return transactionRepository.findById(transactionId).orElseThrow(
                    () -> new ObjectNotPresentException(ErrorMessages.TRANSACTION_ID_NOT_FOUND)
            );
        } finally {
            LOGGER.info("Finish processing find transaction");
        }

    }

    private Transaction validateTransactionBalance(TransactionDTO transactionDTO, Account account){
        LOGGER.info("Starting validate transaction balance");
        try{
            transactionDTO.setInitialBalance(account.getInitialBalance());
            if(transactionDTO.getTransactionType() == EnumTransactionType.DEBIT){
                LOGGER.info("Transaction is a debit one");
                handleDebitTransaction(transactionDTO, account);
            } else {
                LOGGER.info("Transaction is a credit one");
                handleCreditTransaction(transactionDTO, account);
            }
            return TransactionMapper.transactionDTOToTransaction(transactionDTO, account);
        } finally {
            LOGGER.info("Finish validating transaction balance");
        }
    }

    private void handleDebitTransaction(TransactionDTO transactionDTO, Account account){
        checkSufficientFunds(transactionDTO, account);
        transactionDTO.setAvailableBalance(account.getInitialBalance() - transactionDTO.getValue());
    }

    private void handleCreditTransaction(TransactionDTO transactionDTO, Account account){
        transactionDTO.setAvailableBalance(account.getInitialBalance() + transactionDTO.getValue());
    }

    private TransactionDTO updateAvailableBalance(Account account, Transaction transaction){
        LOGGER.info("Starting update transaction balance");
        try{
            account.setInitialBalance(transaction.getBalance());
            accountService.partialUpdateAccount(account.getAccountNumber(), AccountPatchDTO.builder()
                    .initialBalance(account.getInitialBalance()).build());
            transactionRepository.save(transaction);
            return TransactionMapper.transactionToTransactionDTO(transaction);
        } finally {
            LOGGER.info("Finish update transaction balance");
        }
    }

    private void checkSufficientFunds(TransactionDTO transactionDTO, Account account){
        LOGGER.info("Starting checking sufficient funds");
        try{
            if (account.getInitialBalance() < transactionDTO.getValue()){
                throw new InsufficientFundsException(
                        String.format(ErrorMessages.INSUFFICIENT_FUNDS, account.getInitialBalance()));
            }
        } finally {
            LOGGER.info("Finish checking sufficient funds");
        }
    }

}
