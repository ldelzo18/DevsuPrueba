package com.devsu.apirest2.dto.report;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO {

    private LocalDate date;
    private String client;
    private Long numberAccount;
    private String accountType;
    private double initialBalance;
    private double movementBalance;
    private String transactionType;
    private double availableBalance;
}
