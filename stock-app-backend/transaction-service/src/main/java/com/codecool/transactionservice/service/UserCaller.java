package com.codecool.transactionservice.service;

import com.codecool.transactionservice.model.StockAppUser;
import com.codecool.transactionservice.model.UpdateWalletInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserCaller {

    @Autowired
    private RestTemplate restTemplate;

    public StockAppUser getUser(Long userId) {
        return restTemplate.getForEntity("http://user-service/user?userId=" + userId, StockAppUser.class).getBody();
    }

    public ResponseEntity updateBalance(double balance, long userId) {
        UpdateWalletInfo walletInfo = new UpdateWalletInfo(balance, userId);
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.ACCEPT, "application/json");

        return restTemplate.exchange(
                "http://user-service/user?userId=" + userId,
                HttpMethod.PUT,
                new HttpEntity<UpdateWalletInfo>(walletInfo, header),
                ResponseEntity.class
        ).getBody();
    }

    public Double getBalance(Long userId) {
        return restTemplate.getForEntity("http://user-service/user?userId=" + userId, Double.class).getBody();
    }
}

