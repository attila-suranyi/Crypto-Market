package com.codecool.stockapp.model.repository;

import com.codecool.stockapp.model.entity.User;
import com.codecool.stockapp.model.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Wallet getWalletByUser(User user);
}
