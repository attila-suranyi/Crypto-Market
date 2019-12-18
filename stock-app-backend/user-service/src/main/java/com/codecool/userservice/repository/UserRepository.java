package com.codecool.userservice.repository;

import com.codecool.cryptomarketjsonclasses.model.StockAppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;


public interface UserRepository extends JpaRepository<StockAppUser, Long> {
    StockAppUser findById(long id);

    Optional<StockAppUser> findByUserName(String userName);

    @Transactional
    @Query("update StockAppUser a set a.balance = :balance where a.id = :id")
    @Modifying(clearAutomatically = true)
    void updateBalance(@Param("balance") Double balance, @Param("id") Long id);

    @Query("select balance from StockAppUser s where s.id = :id")
    double getBalance(@Param("id") Long id);
}
