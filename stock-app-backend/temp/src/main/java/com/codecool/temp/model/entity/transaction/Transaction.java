package com.codecool.temp.model.entity.transaction;

import com.codecool.temp.model.entity.StockAppUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;

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

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    private StockAppUser stockAppUser;

    public void addUser(StockAppUser user) {
        this.setStockAppUser(user);
        if (user.getTransactionList() == null) {
            user.setTransactionList(new ArrayList<>());
        }
        user.getTransactionList().add(this);
    }
}
