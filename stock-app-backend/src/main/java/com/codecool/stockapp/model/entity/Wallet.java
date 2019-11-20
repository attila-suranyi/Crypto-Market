package com.codecool.stockapp.model.entity;

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

    @OneToOne(mappedBy = "wallet", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private User user;
}
