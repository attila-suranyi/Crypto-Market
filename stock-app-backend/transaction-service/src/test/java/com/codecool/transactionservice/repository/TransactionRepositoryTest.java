package com.codecool.transactionservice.repository;

import com.codecool.cryptomarketjsonclasses.model.transaction.Transaction;
import com.codecool.cryptomarketjsonclasses.model.transaction.TransactionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class TransactionRepositoryTest {

    @Autowired
    TransactionRepository transactionRepository;

    @Test
    void saveOneTransaction() {
        transactionRepository.save(Transaction.builder()
                .amount(1D)
                .date("2015-10-04")
                .price(150D)
                .total(150D)
                .symbol("BTC")
                .currencyId(1L)
                .transactionType(TransactionType.BUY)
                .closedTransaction(false)
                .build());

        List<Transaction> transactions = transactionRepository.findAll();
        assertThat(transactions)
                .hasSize(1);
    }

    @Test
    void fieldShouldBeNotNull() {

        Transaction transaction = Transaction.builder()
                .date("2015-10-4")
                .price(150.0)
                .total(150.0)
                .symbol("BTC")
                .build();

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            transactionRepository.saveAndFlush(transaction);
        });
    }

    @Test
    void closeTransactionTest() {
        transactionRepository.save(Transaction.builder()
                .amount(1D)
                .date("2015-10-04")
                .price(150D)
                .total(150D)
                .symbol("BTC")
                .currencyId(1L)
                .transactionType(TransactionType.BUY)
                .closedTransaction(false)
                .build());

        Long transactionId = transactionRepository.findAllByClosedTransactionFalse()
                .stream()
                .findFirst()
                .get()
                .getId();

        transactionRepository.closeTransaction(transactionId);

        assertThat(transactionRepository.findAllByClosedTransactionFalse())
                .hasSize(0);
    }

    @Test
    void getTransactionsByUserIdAndTransactionTypeTest() {
        transactionRepository.save(Transaction.builder()
                .stockAppUserId(1L)
                .amount(1D)
                .date("2015-10-04")
                .price(150D)
                .total(150D)
                .symbol("BTC")
                .currencyId(1L)
                .transactionType(TransactionType.BUY)
                .closedTransaction(false)
                .build());

        assertThat(transactionRepository.getTransactionsByUserIdAndTransactionType(1L, false))
                .hasSize(1);
    }
}
