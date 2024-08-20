package com.devsu.apirest2.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TRANSACTION")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long transactionId;

    @Column(name = "date", nullable = false, columnDefinition = "timestamptz")
    private ZonedDateTime date;

    @Column(name = "value", nullable = false)
    private Double value;

    @Column(name = "transaction_type", nullable = false)
    private String transactionType;

    @Column(name = "initial_balance", nullable = false)
    private Double initialBalance;

    @Column(name = "balance", nullable = false)
    private Double balance;
    @ManyToOne
    @JoinColumn(name = "account_number")
    private Account account;
}
