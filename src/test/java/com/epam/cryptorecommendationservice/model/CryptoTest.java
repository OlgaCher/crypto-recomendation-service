package com.epam.cryptorecommendationservice.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CryptoTest {

    private List<CryptoItem> expectedCryptosBTC = new ArrayList<>() {{
        add(new CryptoItem(LocalDateTime.of(2022, 1, 1, 4, 0, 0),
                "BTC",
                new BigDecimal("46813.21")));
        add(new CryptoItem(LocalDateTime.of(2022, 1, 1, 4, 0, 0),
                "BTC",
                new BigDecimal("46979.61")));
        add(new CryptoItem(LocalDateTime.of(2022, 2, 1, 4, 0, 0),
                "BTC",
                new BigDecimal("47143.98")));
    }};
    private List<CryptoItem> expectedFilteredCrypto = new ArrayList<>() {{
        add(new CryptoItem(LocalDateTime.of(2022, 2, 1, 4, 0, 0),
                "BTC",
                new BigDecimal("47143.98")));
    }};
    private Crypto cryptoTestedInstance = new Crypto(expectedCryptosBTC);

    @Test
    void should_return_normalized() {
        BigDecimal actualResult = cryptoTestedInstance.getNormalized();
        assertEquals(new BigDecimal("0.01"), actualResult);
    }

    @Test
    void should_return_oldest_crypto_item() {
        CryptoItem actualResult = cryptoTestedInstance.getOldest();
        assertEquals(expectedCryptosBTC.get(0), actualResult);
    }

    @Test
    void should_return_newest_crypto_item() {
        CryptoItem actualResult = cryptoTestedInstance.getNewest();
        assertEquals(expectedCryptosBTC.get(2), actualResult);
    }

    @Test
    void should_return_crypto_with_min_price_item() {
        CryptoItem actualResult = cryptoTestedInstance.getWithMinPrice();
        assertEquals(expectedCryptosBTC.get(0), actualResult);
    }

    @Test
    void should_return_crypto_with_max_price_item() {
        CryptoItem actualResult = cryptoTestedInstance.getWithMaxPrice();
        assertEquals(expectedCryptosBTC.get(2), actualResult);
    }

    @Test
    void should_filter_cryptos_by_date() {
        List<CryptoItem> actualResult = cryptoTestedInstance.filterBy(LocalDate.of(2022, 2, 1));
        assertEquals(expectedFilteredCrypto, actualResult);

    }
}