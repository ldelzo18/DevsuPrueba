package com.devsu.apirest2.dto.account;

import com.devsu.apirest2.util.enums.EnumAccountType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AccountDTO {
    @NotNull(message = "Account number is required.")
    private Long accountNumber;

    @NotNull(message = "Account type is required.")
    @Enumerated(EnumType.STRING)
    private EnumAccountType accountType;

    @NotNull(message = "Initial balance is required.")
    private Double initialBalance;

    @NotNull(message = "Account status is required.")
    private Boolean status;

    @NotNull(message = "Customer ID is required.")
    private Long customerId;
}
