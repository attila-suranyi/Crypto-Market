package com.codecool.stockapp.model.entity.transaction;

import com.codecool.stockapp.model.entity.User;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String symbol;

    @Column(nullable = false)
    private Long currencyId;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Double total;

    @Column(nullable = false)
    private String date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType transactionType;

    @Column(nullable = false)
    private boolean closedTransaction;

    @ToString.Exclude
    @ManyToOne
    private User user;
}
