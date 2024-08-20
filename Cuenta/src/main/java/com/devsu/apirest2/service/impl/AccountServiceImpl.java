package com.devsu.apirest2.service.impl;

import com.devsu.apirest2.service.CustomerService;
import com.devsu.apirest2.dto.account.AccountDTO;
import com.devsu.apirest2.dto.account.AccountPatchDTO;
import com.devsu.apirest2.dto.account.AccountUpdateDTO;
import com.devsu.apirest2.exception.DuplicateException;
import com.devsu.apirest2.exception.ObjectNotPresentException;
import com.devsu.apirest2.model.Account;
import com.devsu.apirest2.model.Customer;
import com.devsu.apirest2.repository.AccountRepository;
import com.devsu.apirest2.service.AccountService;
import com.devsu.apirest2.util.ErrorMessages;
import com.devsu.apirest2.util.mapper.AccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    private AccountRepository accountRepository;

    private CustomerService customerService;

    public AccountServiceImpl(AccountRepository accountRepository, CustomerService customerService){
        this.accountRepository = accountRepository;
        this.customerService = customerService;
    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        LOGGER.info("Starting get all accounts request");
        try{
            List<Account> accountList = accountRepository.findAll();
            return accountList.stream().map(AccountMapper::accountToAccountDTO).toList();
        } catch (Exception ex){
            LOGGER.error("Exception while processing get all accounts, {}", ex.getMessage());
            return Collections.emptyList();
        } finally {
            LOGGER.info("Finish processing get all accounts request");
        }
    }

    @Override
    public AccountDTO getAccountByAccountNumber(Long accountNumber) {
        LOGGER.info("Starting get account by account number request");
        try{
            Account account = findAccountIfExists(accountNumber);
            return AccountMapper.accountToAccountDTO(account);
        } finally {
            LOGGER.info("Finish processing get account by account number request");
        }
    }

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        LOGGER.info("Starting create account request");
        try{
            accountRepository.findAccountByAccountNumber(accountDTO.getAccountNumber()).ifPresent(account -> {
                        throw new DuplicateException(ErrorMessages.ACCOUNT_NUMBER_EXISTS) ;
                        });
            Customer customer = customerService.findCustomerIfExists(accountDTO.getCustomerId());
            return AccountMapper.accountToAccountDTO(
                    accountRepository.save(AccountMapper.accountDTOToAccount(accountDTO, customer))
            );
        } finally {
            LOGGER.info("Finish processing create account request");
        }
    }

    @Override
    public AccountDTO deleteAccount(Long accountNumber) {
        LOGGER.info("Starting delete account request");
        try{
            Account account = findAccountIfExists(accountNumber);
            accountRepository.delete(account);
            return AccountMapper.accountToAccountDTO(account);
        } finally {
            LOGGER.info("Finish processing delete account request");
        }
    }

    @Override
    public AccountDTO updateAccount(Long accountNumber, AccountUpdateDTO accountUpdateDTO) {
        LOGGER.info("Starting update account request");
        try{
            findAccountIfExists(accountNumber);
            Customer customer = customerService.findCustomerIfExists(accountUpdateDTO.getCustomerId());
            AccountDTO accountDTO = AccountMapper.accountUpdateDTOToAccountDTO(accountUpdateDTO, accountNumber);
            accountRepository.save(AccountMapper.accountDTOToAccount(accountDTO, customer));
            return accountDTO;
        } finally {
            LOGGER.info("Finish update delete account request");
        }
    }

    @Override
    public AccountDTO partialUpdateAccount(Long accountNumber, AccountPatchDTO accountPatchDTO) {
        LOGGER.info("Starting partial update account request");
        try{
            Account account = findAccountIfExists(accountNumber);
            if (accountPatchDTO.getCustomerId() != null){
                account.setCustomer(customerService.findCustomerIfExists(accountPatchDTO.getCustomerId()));
            }
            accountRepository.save(AccountMapper.partialUpdateAccountFromPatchAccount(accountPatchDTO, account));
            return AccountMapper.accountToAccountDTO(account);
        } finally {
            LOGGER.info("Finish partial update delete account request");
        }
    }

    @Override
    public Account findAccountIfExists(Long accountNumber) {
        LOGGER.info("Starting find account");
        try{
            return accountRepository.findAccountByAccountNumber(accountNumber).orElseThrow(
                    () -> new ObjectNotPresentException(ErrorMessages.ACCOUNT_NUMBER_NOT_FOUND)
            );
        } finally {
            LOGGER.info("Finish find account");
        }

    }

}
