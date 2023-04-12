package com.epam.cryptorecommendationservice.service;

import com.epam.cryptorecommendationservice.model.Crypto;
import com.epam.cryptorecommendationservice.model.NormalizedCrypto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
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
    private List<Crypto> expectedCryptosBTC = new ArrayList<>() {{
        add(new Crypto(LocalDateTime.of(2022, 1, 1, 4, 0, 0),
                "BTC",
                new BigDecimal("46813.21")));
        add(new Crypto(LocalDateTime.of(2022, 1, 1, 4, 0, 0),
                "BTC",
                new BigDecimal("46979.61")));
        add(new Crypto(LocalDateTime.of(2022, 2, 1, 4, 0, 0),
                "BTC",
                new BigDecimal("47143.98")));
    }};
    private List<Crypto> expectedCryptosETH = new ArrayList<>() {{
        add(new Crypto(LocalDateTime.of(2022, 1, 1, 4, 0, 0),
                "ETH",
                new BigDecimal("2652.21")));
        add(new Crypto(LocalDateTime.of(2022, 2, 1, 4, 0, 0),
                "ETH",
                new BigDecimal("5636.98")));
    }};

    private NormalizedCrypto normalisedCrypto = NormalizedCrypto.builder().
            cryptoName("BTC").
            normalisedPrice(new BigDecimal(5682.2)).
            build();


    @Test
    void should_return_crypto_with_max_price() throws IOException {
        when(fileReader.getCryptosByName("BTC")).thenReturn(expectedCryptosBTC);
        Crypto actualResult = cryptoService.getMaxCrypto("BTC");
        assertEquals(expectedCryptosBTC.get(2), actualResult);
    }

    @Test
    void should_return_crypto_with_min_price() throws IOException {
        when(fileReader.getCryptosByName("BTC")).thenReturn(expectedCryptosBTC);
        Crypto actualResult = cryptoService.getMinCrypto("BTC");
        assertEquals(expectedCryptosBTC.get(0), actualResult);
    }

    @Test
    void should_return_crypto_with_newest_price() throws IOException {
        when(fileReader.getCryptosByName("BTC")).thenReturn(expectedCryptosBTC);
        Crypto actualResult = cryptoService.getNewestCrypto("BTC");
        assertEquals(expectedCryptosBTC.get(2), actualResult);
    }

    @Test
    void should_return_crypto_with_oldest_price() throws IOException {
        when(fileReader.getCryptosByName("BTC")).thenReturn(expectedCryptosBTC);
        Crypto actualResult = cryptoService.getOldestCrypto("BTC");
        assertEquals(expectedCryptosBTC.get(0), actualResult);
    }

    @Test
    void should_return_desc_sorted_cryptos_by_normalized_range() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method privateMethod = CryptoServiceImpl.class.getDeclaredMethod("getNormalisedCrypto", String.class);
        privateMethod.setAccessible(true);
        when(privateMethod.invoke(cryptoService, "BTC")).thenReturn(normalisedCrypto);
        when(fileReader.getCryptosByName("ETH")).thenReturn(expectedCryptosETH);
        List<NormalizedCrypto> normalisedCryptos = cryptoService.getDescSortedCryptosByRange();

    }

    @Test
    void getCryptoWithHighestRangeByDate() {
    }
}