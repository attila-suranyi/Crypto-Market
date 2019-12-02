package com.codecool.stockapp.model.repository;

import com.codecool.stockapp.model.entity.StockAppUser;
import com.codecool.stockapp.model.entity.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> getAllByStockAppUser(StockAppUser user);

    List<Transaction> findAllByClosedTransactionFalse();

    List<Transaction> findAllByClosedTransactionFalseAndStockAppUserId(long userId);

    void deleteById(Long id);

    @Query("update Transaction t set t.closedTransaction = true where t.id = :id")
    @Modifying(clearAutomatically = true)
    void closeTransaction(@Param("id") Long id);

    @Query("select t from Transaction t where t.stockAppUser.id = :userId AND t.closedTransaction = :closedTransaction")
    List<Transaction> getTransactionsByUserIdAndTransactionType(@Param("userId") Long userId, @Param("closedTransaction") boolean closedTransaction);
}
