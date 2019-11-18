package com.codecool.stockapp.model.repository;

import com.codecool.stockapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long id);

    @Query("update User a set a.balance = :balance where a.id = :id")
    @Modifying(clearAutomatically = true)
    void updateBalance(@Param("balance") Double balance, @Param("id") Long id);
}
