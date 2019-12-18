package com.codecool.transactionservice.repository;

import com.codecool.cryptomarketjsonclasses.model.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> getAllByStockAppUserId(Long userId);

    List<Transaction> findAllByClosedTransactionFalse();

    List<Transaction> findAllByClosedTransactionFalseAndStockAppUserId(long userId);

    void deleteById(Long id);

    @Query("update Transaction t set t.closedTransaction = true where t.id = :id")
    @Modifying(clearAutomatically = true)
    void closeTransaction(@Param("id") Long id);

    @Query("select t from Transaction t where t.stockAppUserId = :userId AND t.closedTransaction = :closedTransaction")
    List<Transaction> getTransactionsByUserIdAndTransactionType(@Param("userId") Long userId, @Param("closedTransaction") boolean closedTransaction);
}
