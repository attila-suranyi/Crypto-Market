package com.codecool.stockapp.service;

import com.codecool.stockapp.model.entity.currency.CryptoCurrency;
import com.codecool.stockapp.model.entity.transaction.Transaction;
import com.codecool.stockapp.model.entity.transaction.TransactionType;
import com.codecool.stockapp.service.api.CurrencyAPIService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class TraderTest {

    @Autowired
    private Trader trader;

    @MockBean
    private CurrencyAPIService currencyAPIServiceMock;

    private CryptoCurrency crypto = new CryptoCurrency();

    @Test
    void getCurrenciesTest() {

        when(currencyAPIServiceMock.getCurrencies(isA(String.class), isA(String.class))).thenReturn(crypto);
        assertEquals(trader.getCurrencies("default", "default"), crypto);
    }

    @Test
    void isTransactionExecutableTest() {
        Transaction buyHigherPrice = Transaction.builder()
                .price(130.0)
                .transactionType(TransactionType.BUY)
                .id(1l)
                .build();

        Transaction buyLowerPrice = Transaction.builder()
                .price(70.0)
                .transactionType(TransactionType.BUY)
                .id((long) 2)
                .build();

        Transaction sellHigherPrice = Transaction.builder()
                .price(130.0)
                .transactionType(TransactionType.SELL)
                .id((long) 3)
                .build();

        Transaction sellLowerPrice = Transaction.builder()
                .price(70.0)
                .transactionType(TransactionType.SELL)
                .id((long) 4)
                .build();

        when(currencyAPIServiceMock.getSingleCurrencyPrice(any())).thenReturn(100.0);

        assertEquals(100.0, currencyAPIServiceMock.getSingleCurrencyPrice(isA(Long.class)));
        assertTrue(buyHigherPrice.getPrice() >= currencyAPIServiceMock.getSingleCurrencyPrice(isA(Long.class)));
        assertFalse(buyLowerPrice.getPrice() >= currencyAPIServiceMock.getSingleCurrencyPrice(isA(Long.class)));

        assertTrue(trader.isTransactionExecutable(buyHigherPrice));
        assertFalse(trader.isTransactionExecutable(buyLowerPrice));

        assertTrue(trader.isTransactionExecutable(sellLowerPrice));
        assertFalse(trader.isTransactionExecutable(sellHigherPrice));
    }
}
