package com.codecool.stockapp.model.entity.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OpenTransaction {

    private String symbol;

    private Long currencyId;

    private Double price;

    private Double amount;

    private Double total;

    private String date;

    private Double currentPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType transactionType;
}
