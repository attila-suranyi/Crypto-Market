package com.codecool.wallet.controller;


import com.codecool.wallet.model.Wallet;
import com.codecool.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletRepository walletRepository;

    @GetMapping
    public List<Wallet> getWallets(@RequestParam Long userId) {
        return walletRepository.getAllByStockAppUserId(userId);
    }

    @PutMapping
    public ResponseEntity<Boolean> modifyWallet(@RequestBody Wallet wallet) {
        walletRepository.updateWallet(
                wallet.getAvailableAmount(),
                wallet.getInOrder(),
                wallet.getTotalAmount(),
                wallet.getUsdValue(),
                wallet.getSymbol());
        return ResponseEntity.ok(true);
    }
}
