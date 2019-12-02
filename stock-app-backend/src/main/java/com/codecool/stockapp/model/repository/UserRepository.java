package com.codecool.stockapp.model.repository;


import com.codecool.stockapp.model.entity.StockAppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserRepository extends JpaRepository<StockAppUser, Long> {
    StockAppUser findById(long id);

    Optional<StockAppUser> findByUserName(String userName);

    @Query("update StockAppUser a set a.balance = :balance where a.id = :id")
    @Modifying(clearAutomatically = true)
    void updateBalance(@Param("balance") Double balance, @Param("id") Long id);
}
