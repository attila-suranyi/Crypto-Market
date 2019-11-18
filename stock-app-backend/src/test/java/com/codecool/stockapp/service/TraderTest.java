package com.codecool.stockapp.service;

import com.codecool.stockapp.model.entity.currency.CryptoCurrency;
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
}
