package com.epam.cryptorecommendationservice.controller;

import com.epam.cryptorecommendationservice.model.CryptoItem;
import com.epam.cryptorecommendationservice.service.CryptoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CryptoController.class)
class CryptoControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private CryptoService cryptoService;

    private CryptoItem crypto = new CryptoItem(LocalDateTime.of(2022, 1, 1, 4, 0, 0),
            "BTC",
            new BigDecimal("46813.21"));

    @Test
    void should_return_not_empty_json_when_request_max() throws Exception {
        when(cryptoService.getMaxCrypto("BTC")).thenReturn(crypto);
        mvc.perform(MockMvcRequestBuilders
                        .get("/cryptos/BTC/max")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cryptoName").isNotEmpty());
    }

    @Test
    void should_return_not_empty_json_when_request_min() throws Exception {
        when(cryptoService.getMinCrypto("BTC")).thenReturn(crypto);
        mvc.perform(MockMvcRequestBuilders
                        .get("/cryptos/BTC/min")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cryptoName").isNotEmpty());
    }

    @Test
    void should_return_not_empty_json_when_request_oldest() throws Exception {
        when(cryptoService.getOldestCrypto("BTC")).thenReturn(crypto);
        mvc.perform(MockMvcRequestBuilders
                        .get("/cryptos/BTC/oldest")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cryptoName").isNotEmpty());
    }

    @Test
    void should_return_not_empty_json_when_request_newest() throws Exception {
        when(cryptoService.getNewestCrypto("BTC")).thenReturn(crypto);
        mvc.perform(MockMvcRequestBuilders
                        .get("/cryptos/BTC/newest")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cryptoName").isNotEmpty());
    }
}