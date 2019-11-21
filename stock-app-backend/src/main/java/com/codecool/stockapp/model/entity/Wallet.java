package com.codecool.stockapp.model.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Wallet {

    @Id
    @GeneratedValue
    private long id;

    private String symbol;
    private String name;
    private double totalAmount;
    private double availableAmount;
    private double inOrder;
    private double usdValue;

    @ToString.Exclude
    @OneToOne()
    private User user;
}
