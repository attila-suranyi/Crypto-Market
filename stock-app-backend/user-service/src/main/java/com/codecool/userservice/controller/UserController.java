package com.codecool.userservice.controller;

import com.codecool.cryptomarketjsonclasses.model.StockAppUser;
import com.codecool.userservice.model.UpdateWalletInfo;
import com.codecool.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    @GetMapping("/{name}")
    public StockAppUser getUser(@PathVariable String name) {
        return userRepository.findByUserName(name).orElseThrow(() -> new UsernameNotFoundException("Username: " + name + " not found"));
    }

    @GetMapping("/balance")
    public double getBalance(@RequestParam Long userId) {
        return userRepository.getBalance(userId);
    }

    @PutMapping
    public void modifyBalance(@RequestParam Long userId, @RequestParam Double balance) {
        userRepository.updateBalance(balance, userId);
    }

    @PostMapping
    public ResponseEntity<Boolean> registerUser(@RequestBody StockAppUser newUser) {
        userRepository.save(newUser);
        return ResponseEntity.ok(true);
    }
}
