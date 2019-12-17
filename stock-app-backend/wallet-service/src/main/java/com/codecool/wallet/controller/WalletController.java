package com.codecool.wallet.controller;


import com.codecool.wallet.model.Transaction;
import com.codecool.wallet.model.Wallet;
import com.codecool.wallet.repository.WalletRepository;
import com.codecool.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private WalletService walletService;

    @GetMapping
    public List<Wallet> getWallets(@RequestParam Long userId) {
        return walletRepository.getAllByStockAppUserId(userId);
    }

    @PutMapping
    public ResponseEntity<Boolean> updateWallet(@RequestBody Transaction transaction) {
        walletService.updateWallet(transaction);
        return ResponseEntity.ok(true);
    }

    @PostMapping
    public ResponseEntity<Boolean> setWallet(@RequestBody Transaction transaction) {
        walletService.setWallet(transaction);
        return ResponseEntity.ok(true);
    }

}
