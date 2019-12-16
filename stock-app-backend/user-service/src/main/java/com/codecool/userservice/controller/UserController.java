package com.codecool.userservice.controller;

import com.codecool.userservice.model.StockAppUser;
import com.codecool.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public StockAppUser getUser(@RequestParam Long userId) {
        return userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
    }

    @PutMapping
    public ResponseEntity<Boolean> modifyBalance(@RequestParam Long userId, @RequestParam Double balance) {
        userRepository.updateBalance(balance, userId);
        return ResponseEntity.ok(true);
    }

    @PostMapping
    public ResponseEntity<Boolean> registerUser(@RequestBody StockAppUser newUser) {
        userRepository.save(newUser);
        return ResponseEntity.ok(true);
    }
}