package com.codecool.stockapp.model.entity.transaction;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OpenTransaction {

    private String symbol;

    private Long currencyId;

    private Double price;

    private Double currentPrice;

    private Double amount;

    private Double total;

    private String date;

    private TransactionType transactionType;
}
