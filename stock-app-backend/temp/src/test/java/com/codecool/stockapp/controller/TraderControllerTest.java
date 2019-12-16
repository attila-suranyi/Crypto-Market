package com.codecool.stockapp.controller;

import com.codecool.temp.model.entity.currency.CryptoCurrency;
import com.codecool.temp.model.entity.currency.CurrencyDetails;
import com.codecool.temp.model.repository.UserRepository;
import com.codecool.temp.service.Trader;
import com.codecool.temp.controller.TraderController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TraderController.class)
class TraderControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private Trader trader;

    @BeforeEach
    void setUp() {
    }

    @Test
    void showCryptoCurrenciesTest() throws Exception {

        CryptoCurrency crypto = new CryptoCurrency();
        List<CurrencyDetails> dataItem = new ArrayList<>();
        dataItem.add(new CurrencyDetails());

        crypto.setData(dataItem);

        when(trader.getCurrencies("default", "default")).thenReturn(crypto);

        mvc.perform( MockMvcRequestBuilders
                .get("/")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists());
    }
}
