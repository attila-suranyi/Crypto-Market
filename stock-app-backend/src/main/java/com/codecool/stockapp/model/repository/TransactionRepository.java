package com.codecool.stockapp.model.repository;

import com.codecool.stockapp.model.entity.User;
import com.codecool.stockapp.model.entity.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> getAllByUser(User user);

    List<Transaction> findAllByClosedTransactionFalse();

    List<Transaction> findAllByClosedTransactionFalseAndUserId(long userId);

    void deleteById(Long id);

    @Query("update Transaction t set t.closedTransaction = true where t.id = :id")
    @Modifying(clearAutomatically = true)
    void closeTransaction(@Param("id") Long id);


    //TODO check if this refactored method works with two params
    @Query("select t from Transaction t where t.user.id = :userId AND t.closedTransaction = :closedTransaction")
    List<Transaction> getTransactionsByUserIdAndTransactionType(@Param("userId") Long userId, @Param("closedTransaction") boolean closedTransaction);
}
