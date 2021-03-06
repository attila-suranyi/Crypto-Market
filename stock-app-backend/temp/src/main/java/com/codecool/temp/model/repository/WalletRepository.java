package com.codecool.temp.model.repository;

import com.codecool.temp.model.entity.StockAppUser;
import com.codecool.temp.model.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    List<Wallet> getWalletsByStockAppUser(StockAppUser user);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Wallet w SET w.availableAmount = :availableAmount, w.inOrder = :inOrder, w.totalAmount = :totalAmount, w.usdValue = :usdValue WHERE w.symbol = :symbol")
    void updateWallet(@Param("availableAmount") double availableAmount, @Param("inOrder") double inOrder, @Param("totalAmount") double totalAmount, @Param("usdValue") double usdValue, @Param("symbol") String symbol);
}
