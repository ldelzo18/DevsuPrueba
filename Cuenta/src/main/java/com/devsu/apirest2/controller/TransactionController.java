package com.devsu.apirest2.controller;

import com.devsu.apirest2.dto.transaction.TransactionDTO;
import com.devsu.apirest2.service.TransactionService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class TransactionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);
    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping("/")
    public ResponseEntity<List<TransactionDTO>> getAllTransactions(){
        LOGGER.info("Receive a get all transactions petition");
        return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.OK);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable(value="transactionId") Long transactionId){
        LOGGER.info("Receive a get transaction petition for transaction id {}", transactionId);
        return new ResponseEntity<>(transactionService.getTransactionById(transactionId), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<TransactionDTO> saveTransaction(@Valid @RequestBody TransactionDTO transactionDTO){
        LOGGER.info("Receive a save transaction petition");
        return new ResponseEntity<>(transactionService.createTransaction(transactionDTO), HttpStatus.OK);
    }
}
