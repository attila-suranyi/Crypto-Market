package com.codecool.temp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    private StockAppUser stockAppUser;
}
