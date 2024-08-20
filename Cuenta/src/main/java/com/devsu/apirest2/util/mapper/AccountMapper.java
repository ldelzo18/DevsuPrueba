package com.devsu.apirest2.util.mapper;

import com.devsu.apirest2.dto.account.AccountDTO;
import com.devsu.apirest2.dto.account.AccountPatchDTO;
import com.devsu.apirest2.dto.account.AccountUpdateDTO;
import com.devsu.apirest2.model.Account;
import com.devsu.apirest2.model.Customer;
import com.devsu.apirest2.util.enums.EnumAccountType;

public class AccountMapper {

    public static AccountDTO accountToAccountDTO(Account account){
        return AccountDTO.builder().accountNumber(account.getAccountNumber())
                .accountType(EnumAccountType.fromString(account.getAccountType()))
                .initialBalance(account.getInitialBalance())
                .status(account.getStatus())
                .customerId(account.getCustomer().getCustomerId())
                .build();
    }

    public static Account accountDTOToAccount(AccountDTO accountDTO, Customer customer){
        return Account.builder().accountNumber(accountDTO.getAccountNumber())
                .accountType(accountDTO.getAccountType().name())
                .initialBalance(accountDTO.getInitialBalance())
                .status(accountDTO.getStatus())
                .customer(customer)
                .build();
    }

    public static AccountDTO accountUpdateDTOToAccountDTO(AccountUpdateDTO accountUpdateDTO, Long accountNumber){
        return AccountDTO.builder().accountNumber(accountNumber)
                .accountType(accountUpdateDTO.getAccountType())
                .initialBalance(accountUpdateDTO.getInitialBalance())
                .status(accountUpdateDTO.getStatus())
                .customerId(accountUpdateDTO.getCustomerId())
                .build();
    }

    public static Account partialUpdateAccountFromPatchAccount(AccountPatchDTO accountPatchDTO, Account account){
        if (accountPatchDTO.getAccountType() != null){
            account.setAccountType(accountPatchDTO.getAccountType().name());
        }
        if (accountPatchDTO.getInitialBalance() != null){
            account.setInitialBalance(accountPatchDTO.getInitialBalance());
        }
        if (accountPatchDTO.getStatus() != null){
            account.setStatus(accountPatchDTO.getStatus());
        }
        return account;
    }

}
