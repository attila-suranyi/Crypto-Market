package com.codecool.apigateway.service;

import com.codecool.cryptomarketjsonclasses.model.StockAppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserCaller {

    @Autowired
    private RestTemplate restTemplate;

    public StockAppUser getUser(String name){
        return restTemplate.getForEntity("http://user-service/user/" + name,StockAppUser.class).getBody();
    }

    public void saveUser(StockAppUser stockAppUser) {
        restTemplate.postForObject("http://user-service/user/", new HttpEntity<>(stockAppUser), Void.class);
    }
}
