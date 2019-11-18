package com.codecool.stockapp.model.repository;

import com.codecool.stockapp.model.entity.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
