package com.devsu.apirest2.service;

import com.devsu.apirest2.dto.account.AccountDTO;
import com.devsu.apirest2.dto.account.AccountPatchDTO;
import com.devsu.apirest2.dto.account.AccountUpdateDTO;
import com.devsu.apirest2.model.Account;

import java.util.List;

public interface AccountService {

    List<AccountDTO> getAllAccounts();
    AccountDTO getAccountByAccountNumber(Long accountNumber);
    AccountDTO createAccount(AccountDTO accountDTO);
    AccountDTO deleteAccount(Long accountNumber);
    AccountDTO updateAccount(Long accountNumber, AccountUpdateDTO accountUpdateDTO);
    AccountDTO partialUpdateAccount(Long accountNumber, AccountPatchDTO accountPatchDTO);
    Account findAccountIfExists(Long accountNumber);
}
