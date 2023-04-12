package com.epam.cryptorecommendationservice.service;

import com.epam.cryptorecommendationservice.model.Crypto;
import com.epam.cryptorecommendationservice.model.CryptoItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CryptoServiceImplTest {
    @Mock
    private CsvFileReaderService fileReader;

    @InjectMocks
    private CryptoServiceImpl cryptoService;
    private Crypto expectedCryptosBTC = Crypto.builder().cryptoItems(new ArrayList<>() {{
        add(new CryptoItem(LocalDateTime.of(2022, 1, 1, 4, 0, 0),
                "BTC",
                new BigDecimal("46813.21")));
        add(new CryptoItem(LocalDateTime.of(2022, 1, 1, 4, 0, 0),
                "BTC",
                new BigDecimal("46979.61")));
        add(new CryptoItem(LocalDateTime.of(2022, 2, 1, 4, 0, 0),
                "BTC",
                new BigDecimal("47143.98")));
    }}).build();
    private Crypto expectedCryptosETH = Crypto.builder().cryptoItems(new ArrayList<>() {{
        add(new CryptoItem(LocalDateTime.of(2022, 1, 1, 4, 0, 0),
                "ETH",
                new BigDecimal("2652.21")));
        add(new CryptoItem(LocalDateTime.of(2022, 2, 1, 4, 0, 0),
                "ETH",
                new BigDecimal("5636.98")));
    }}).build();


    @Test
    void should_return_crypto_with_max_price() {
        when(fileReader.getCryptosByName("BTC")).thenReturn(expectedCryptosBTC);
        CryptoItem actualResult = cryptoService.getMaxCrypto("BTC");
        assertEquals(expectedCryptosBTC.getCryptoItems().get(2), actualResult);
    }

    @Test
    void should_return_crypto_with_min_price() {
        when(fileReader.getCryptosByName("BTC")).thenReturn(expectedCryptosBTC);
        CryptoItem actualResult = cryptoService.getMinCrypto("BTC");
        assertEquals(expectedCryptosBTC.getCryptoItems().get(0), actualResult);
    }

    @Test
    void should_return_crypto_with_newest_price() {
        when(fileReader.getCryptosByName("BTC")).thenReturn(expectedCryptosBTC);
        CryptoItem actualResult = cryptoService.getNewestCrypto("BTC");
        assertEquals(expectedCryptosBTC.getCryptoItems().get(2), actualResult);
    }

    @Test
    void should_return_crypto_with_oldest_price() {
        when(fileReader.getCryptosByName("BTC")).thenReturn(expectedCryptosBTC);
        CryptoItem actualResult = cryptoService.getOldestCrypto("BTC");
        assertEquals(expectedCryptosBTC.getCryptoItems().get(0), actualResult);
    }

    @Test
    void should_return_desc_sorted_cryptos_by_normalized_range() {
        when(fileReader.getCryptoNames()).thenReturn(List.of("BTC", "ETH"));
        when(fileReader.getCryptosByName("BTC")).thenReturn(expectedCryptosBTC);
        when(fileReader.getCryptosByName("ETH")).thenReturn(expectedCryptosETH);
        List<Crypto> actualResult = cryptoService.getDescSortedCryptosByRange();
        List<Crypto> expectedResult = new ArrayList<>() {{
            add(expectedCryptosETH);
            add(expectedCryptosBTC);
        }};
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void should_return_crypto_with_highest_range_by_date() {
        when(fileReader.getCryptoNames()).thenReturn(List.of("BTC", "ETH"));
        when(fileReader.getCryptosByName("BTC")).thenReturn(expectedCryptosBTC);
        when(fileReader.getCryptosByName("ETH")).thenReturn(expectedCryptosETH);
        Crypto actualResult = cryptoService.getCryptoWithHighestRangeByDate(LocalDate.of(2022, 1, 1));
        assertEquals(expectedCryptosETH, actualResult);
    }
}