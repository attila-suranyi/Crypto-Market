package com.codecool.transactionservice.service;

import com.codecool.cryptomarketjsonclasses.model.transaction.Transaction;
import com.codecool.cryptomarketjsonclasses.model.transaction.TransactionType;
import com.codecool.transactionservice.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @InjectMocks
    private TransactionService service;

    @Mock
    private CurrencyCaller currencyCallerMock;

    @Mock
    private UserCaller userCallerMock;

    @Mock
    private WalletCaller walletCallerMock;

    @Mock
    private TransactionRepository transactionRepositoryMock;

    @Test
    void isTransactionExecutableTest() {
        Transaction buyHigherPrice = Transaction.builder()
                .price(130D)
                .transactionType(TransactionType.BUY)
                .id(1L)
                .build();

        Transaction buyLowerPrice = Transaction.builder()
                .price(70D)
                .transactionType(TransactionType.BUY)
                .id(2L)
                .build();

        Transaction sellHigherPrice = Transaction.builder()
                .price(130D)
                .transactionType(TransactionType.SELL)
                .id(3L)
                .build();

        Transaction sellLowerPrice = Transaction.builder()
                .price(70D)
                .transactionType(TransactionType.SELL)
                .id(4L)
                .build();

        when(currencyCallerMock.getSingleCurrencyPrice(any())).thenReturn(100.0);

        assertEquals(100D, currencyCallerMock.getSingleCurrencyPrice(isA(Long.class)));
        assertTrue(buyHigherPrice.getPrice() >= currencyCallerMock.getSingleCurrencyPrice(isA(Long.class)));
        assertFalse(buyLowerPrice.getPrice() >= currencyCallerMock.getSingleCurrencyPrice(isA(Long.class)));

        assertTrue(service.isTransactionExecutable(buyHigherPrice));
        assertFalse(service.isTransactionExecutable(buyLowerPrice));

        assertTrue(service.isTransactionExecutable(sellLowerPrice));
        assertFalse(service.isTransactionExecutable(sellHigherPrice));
    }

    @Test
    void isBuySuccessfulTest() {
        Transaction affordableTransaction = Transaction.builder()
                .stockAppUserId(1L)
                .price(70D)
                .transactionType(TransactionType.BUY)
                .id(2L)
                .total(70D)
                .amount(1D)
                .build();

        Transaction tooExpensiveTransaction = Transaction.builder()
                .stockAppUserId(1L)
                .price(70D)
                .transactionType(TransactionType.BUY)
                .id(2L)
                .amount(1D)
                .total(140D)
                .build();

        when(userCallerMock.getBalance(any())).thenReturn(100D);
        when(currencyCallerMock.getSingleCurrencyPrice(any())).thenReturn(70D);

        doNothing().when(userCallerMock).updateBalance(isA(Double.class), isA(Long.class));

        assertTrue(service.buy(affordableTransaction));
        assertFalse(service.buy(tooExpensiveTransaction));
    }
}
