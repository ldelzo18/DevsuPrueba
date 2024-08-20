package com.devsu.apirest2.controller;

import com.devsu.apirest2.dto.account.AccountDTO;
import com.devsu.apirest2.dto.account.AccountPatchDTO;
import com.devsu.apirest2.dto.account.AccountUpdateDTO;
import com.devsu.apirest2.service.AccountService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    private AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping("/")
    public ResponseEntity<List<AccountDTO>> getAllAccounts(){
        LOGGER.info("Receive a get all accounts petition");
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountDTO> getCustomerById(@PathVariable(value="accountNumber") Long accountNumber){
        LOGGER.info("Receive a get account petition for account number {}", accountNumber);
        return new ResponseEntity<>(accountService.getAccountByAccountNumber(accountNumber), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<AccountDTO> saveAccount(@Valid @RequestBody AccountDTO accountDTO){
        LOGGER.info("Receive a save account petition");
        return new ResponseEntity<>(accountService.createAccount(accountDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<AccountDTO> deleteAccountByAccountNumber(@PathVariable(value="accountNumber") Long accountNumber){
        LOGGER.info("Receive a delete account petition for account number {}", accountNumber);
        return new ResponseEntity<>(accountService.deleteAccount(accountNumber), HttpStatus.OK);
    }

    @PutMapping("/{accountNumber}")
    public ResponseEntity<AccountDTO> updateCustomer(@PathVariable(value="accountNumber") Long accountNumber,
                                                      @Valid @RequestBody AccountUpdateDTO accountUpdateDTO){
        LOGGER.info("Receive a update account petition for account number {}", accountNumber);
        return new ResponseEntity<>(accountService.updateAccount(accountNumber, accountUpdateDTO), HttpStatus.OK);
    }

    @PatchMapping("/{accountNumber}")
    public ResponseEntity<AccountDTO> partialUpdateCustomer(@PathVariable(value="accountNumber") Long accountNumber,
                                                             @RequestBody AccountPatchDTO accountPatchDTO){
        LOGGER.info("Receive a partial update account petition for account number {}", accountNumber);
        return new ResponseEntity<>(accountService.partialUpdateAccount(accountNumber, accountPatchDTO), HttpStatus.OK);
    }

}
