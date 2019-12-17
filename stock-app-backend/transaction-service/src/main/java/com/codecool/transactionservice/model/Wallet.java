package com.codecool.transactionservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    //@JsonIgnore
    //@ToString.Exclude
    //@ManyToOne(fetch = FetchType.EAGER)
    private Long stockAppUserId;
}
