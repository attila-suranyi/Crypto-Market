package com.codecool.transactionservice.controller;

import com.codecool.transactionservice.service.UserCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    private UserCaller userCaller;

    @GetMapping("/balance")
    public double getBalance(@RequestParam Long userId) {
        return userCaller.getBalance(userId);
    }
}
