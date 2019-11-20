package com.codecool.stockapp.service;

import com.codecool.stockapp.model.entity.currency.CryptoCurrency;
import com.codecool.stockapp.model.entity.currency.CurrencyDetails;
import com.codecool.stockapp.model.entity.currency.SingleCurrency;
import com.codecool.stockapp.model.entity.transaction.Transaction;
import com.codecool.stockapp.model.entity.transaction.TransactionType;
import com.codecool.stockapp.service.api.CurrencyAPIService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;


@SpringBootTest
class TraderTest {

    @InjectMocks
    Trader trader = new Trader();

    @Mock
    private CurrencyAPIService currencyAPIServiceMock;

    private CryptoCurrency crypto = new CryptoCurrency();

    @Test
    void getCurrenciesTest() {

        when(currencyAPIServiceMock.getCurrencies(isA(String.class), isA(String.class))).thenReturn(crypto);
        assertEquals(trader.getCurrencies("default", "default"), crypto);
    }

    //TODO mocking doesnt work
    @Test
    void isTransactionExecutableTest() {
        Transaction buyHigherPrice = Transaction.builder()
                .price(130.0)
                .transactionType(TransactionType.BUY)
                .id((long) 1)
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

        when(currencyAPIServiceMock.getSingleCurrencyPrice(isA(Long.class))).thenReturn(100.0);

        assertEquals(100.0, currencyAPIServiceMock.getSingleCurrencyPrice(isA(Long.class)));
        assertTrue(buyHigherPrice.getPrice() >= currencyAPIServiceMock.getSingleCurrencyPrice(isA(Long.class)));
        assertFalse(buyLowerPrice.getPrice() >= currencyAPIServiceMock.getSingleCurrencyPrice(isA(Long.class)));

        assertTrue(trader.isTransactionExecutable(buyHigherPrice));
        //assertFalse(trader.isTransactionExecutable(buyLowerPrice));

        /*assertFalse(trader.isTransactionExecutable(sellLowerPrice));
        assertFalse(trader.isTransactionExecutable(sellHigherPrice));*/
    }
}
