package com.codecool.stockapp.model.repository;


import com.codecool.stockapp.model.entity.StockAppUser;
import com.codecool.stockapp.model.entity.transaction.Transaction;
import com.codecool.stockapp.model.entity.transaction.TransactionType;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class AllRepositoryTest {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void saveOneTransaction() {
        Transaction transaction = Transaction.builder()
                .amount(1.0)
                .date("2015-10-04")
                .price(150.0)
                .total(150.0)
                .symbol("BTC")
                .currencyId((long) 1)
                .transactionType(TransactionType.BUY)
                .closedTransaction(false)
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

    @Test
    public void transactionsArePersistedAndDeletedWithUser() {
        StockAppUser user = StockAppUser.builder()
                .firstName("Satosi")
                .lastName("Nakamoto")
                .balance(1000000)
                .email("satoshinakamoto@gmail.com")
                .userName("satosi")
                .password("Test123")
                .build();

        Transaction transaction1 = Transaction.builder()
                .amount(1.0)
                .date("2015-10-04")
                .price(150.0)
                .total(150.0)
                .symbol("BTC")
                .currencyId((long) 1)
                .transactionType(TransactionType.BUY)
                .closedTransaction(false)
                .build();

        Transaction transaction2 = Transaction.builder()
                .amount(2.0)
                .date("2015-10-04")
                .price(150.0)
                .total(150.0)
                .symbol("ETH")
                .currencyId((long) 1027)
                .transactionType(TransactionType.BUY)
                .closedTransaction(false)
                .build();

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);

        user.setTransactionList(transactions);

        userRepository.save(user);

        assertThat(transactionRepository.findAll())
                .hasSize(2)
                .anyMatch(transaction -> transaction.getSymbol().equals("ETH"));

        userRepository.deleteAll();

        assertThat(transactionRepository.findAll())
                .hasSize(0);
    }

    @Test
    void getTransactionsByUserIdAndTransactionTypeTest() {
        StockAppUser user1 = StockAppUser.builder()
                .firstName("Satosi")
                .lastName("Nakamoto")
                .balance(1000000)
                .email("satoshinakamoto@gmail.com")
                .userName("satosi")
                .password("Test123")
                .build();

        StockAppUser user2 = StockAppUser.builder()
                .firstName("gergo")
                .lastName("kis")
                .balance(1000000)
                .email("valami.gmail.com")
                .userName("geri")
                .password("Test123")
                .build();

        Transaction transaction1 = Transaction.builder()
                .amount(1.0)
                .date("2015-10-04")
                .price(150.0)
                .total(150.0)
                .symbol("BTC")
                .currencyId((long) 1)
                .transactionType(TransactionType.BUY)
                .closedTransaction(false)
                .build();

        Transaction transaction2 = Transaction.builder()
                .amount(2.0)
                .date("2015-10-04")
                .price(70.0)
                .total(140.0)
                .symbol("ETH")
                .currencyId((long) 1027)
                .transactionType(TransactionType.BUY)
                .closedTransaction(false)
                .build();

        Transaction transaction3 = Transaction.builder()
                .amount(3.0)
                .date("2015-10-04")
                .price(100.0)
                .total(300.0)
                .symbol("XRM")
                .currencyId((long) 30)
                .transactionType(TransactionType.BUY)
                .closedTransaction(true)
                .build();

        transaction1.addUser(user1);
        transaction2.addUser(user2);
        transaction3.addUser(user2);

        userRepository.saveAll(Lists.newArrayList(user1, user2));

        assertThat(transactionRepository.getTransactionsByUserIdAndTransactionType(user1.getId(), false))
                .hasSize(1)
                .contains(transaction1);

        assertThat(transactionRepository.getTransactionsByUserIdAndTransactionType(user2.getId(), true))
                .hasSize(1)
                .contains(transaction3);

        assertThat(transactionRepository.getTransactionsByUserIdAndTransactionType(user1.getId(), true))
                .hasSize(0);
    }
}
