package com.devsu.apirest2.dto.transaction;

import com.devsu.apirest2.util.enums.EnumTransactionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.ZonedDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private Long transactionId;
    @NotNull(message = "Account number is required.")
    private Long accountNumber;
    @NotNull(message = "Transaction type is required.")
    @Enumerated(EnumType.STRING)
    private EnumTransactionType transactionType;
    @NotNull(message = "Transaction value is required.")
    private Double value;
    private Double initialBalance;
    private Double availableBalance;
    private ZonedDateTime date;

}
