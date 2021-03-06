package com.codecool.transactionservice.service;


import com.codecool.cryptomarketjsonclasses.model.Wallet;
import com.codecool.cryptomarketjsonclasses.model.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class WalletCaller {

    @Autowired
    private RestTemplate restTemplate;

    public Wallet getWallet(Long userId, String symbol) {
        return restTemplate.getForEntity("http://wallet-service/wallet/symbol?userId=" + userId + "&symbol=" + symbol, Wallet.class).getBody();
    }

    public Wallet[] getWalletList(Long userId) {
        return restTemplate.getForEntity("http://wallet-service/wallet?userId=" + userId, Wallet[].class).getBody();
    }

    public void updateWallet(Transaction transaction) {
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.ACCEPT, "application/json");

        restTemplate.exchange(
                "http://wallet-service/wallet",
                HttpMethod.PUT,
                new HttpEntity<>(transaction, header),
                Void.class
        ).getBody();
    }

    public void setWallet(Transaction transaction) {
        HttpEntity<Transaction> request = new HttpEntity<>(transaction);
        restTemplate.exchange(
                "http://wallet-service/wallet",
                HttpMethod.POST,
                request,
                Void.class
        );
    }
}
