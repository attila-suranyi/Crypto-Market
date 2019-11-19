package com.codecool.stockapp.model.repository;

import com.codecool.stockapp.model.entity.User;
import com.codecool.stockapp.model.entity.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> getAllByUser(User user);

    List<Transaction> findAllByClosedTransactionFalse();

    void deleteById(Long id);

}
