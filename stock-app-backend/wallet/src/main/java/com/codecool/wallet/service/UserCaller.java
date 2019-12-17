package com.codecool.wallet.service;

import com.codecool.wallet.model.StockAppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserCaller {

    @Autowired
    private RestTemplate restTemplate;

    public StockAppUser getUser(Long userId) {
        return restTemplate.getForEntity("http://user-service/user?userId=" + userId, StockAppUser.class).getBody();
    }
}
