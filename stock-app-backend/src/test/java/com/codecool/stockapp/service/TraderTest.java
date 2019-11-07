package com.codecool.stockapp.service;

import com.codecool.stockapp.model.Currencies.CryptoCurrency;
import com.codecool.stockapp.service.api.CurrencyAPIService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mockito;
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

        when(currencyAPIServiceMock.getCurrencies()).thenReturn(crypto);
        assertEquals(trader.getCurrencies(), crypto);
    }

    @Test
    void getSortedCurrenciesTest() {

        when(currencyAPIServiceMock.getSortedCurrencies(isA(String.class), isA(String.class))).thenReturn(crypto);
        assertEquals(trader.getCurrencies("symbol", "asc"), crypto);
    }
}
