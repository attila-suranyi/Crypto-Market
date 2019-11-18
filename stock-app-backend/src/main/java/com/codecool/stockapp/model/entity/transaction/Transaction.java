package com.codecool.stockapp.model.entity.transaction;

import com.codecool.stockapp.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String symbol;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private double total;

    @Column(nullable = false)
    private String date;

    @ManyToOne
    private User user;
}
