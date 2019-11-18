package com.codecool.stockapp.model.repository;

import com.codecool.stockapp.model.entity.User;
import com.codecool.stockapp.model.entity.transaction.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static org.junit.Assert.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class AllRepositoryTest {

    @Autowired
    TransactionRepository transactionRepository;


    @Test
    public void saveOneTransaction() {
        Transaction transaction = Transaction.builder()
                .amount(1.0)
                .date("2015-10-04")
                .price(150.0)
                .total(150.0)
                .symbol("BTC")
                .build();

        transactionRepository.save(transaction);

        List<Transaction> transactions = transactionRepository.findAll();
        assertThat(transactions)
                .hasSize(1);
    }

    @Test
    public void fieldShouldBeNotNull() {

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
}
