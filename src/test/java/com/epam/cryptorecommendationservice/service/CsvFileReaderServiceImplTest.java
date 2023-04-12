package com.epam.cryptorecommendationservice.service;

import com.epam.cryptorecommendationservice.exceptions.CsvFileReaderServiceException;
import com.epam.cryptorecommendationservice.model.CryptoItem;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvFileReaderServiceImplTest {
    private String testDirectoryPath = "src/test/resources/file/";
    private String testRegex = "^[\\w-]+_values\\.csv$";
    private CsvFileReaderService testedInstance = new CsvFileReaderServiceImpl();

    private List<CryptoItem> expectedCryptos = new ArrayList<>() {{
        add(new CryptoItem(LocalDateTime.of(2022, 1, 1, 5, 0, 0),
                "BTC",
                new BigDecimal("46813.21")));
        add(new CryptoItem(LocalDateTime.of(2022, 1, 1, 8, 0, 0),
                "BTC",
                new BigDecimal("46979.61")));
        add(new CryptoItem(LocalDateTime.of(2022, 1, 1, 11, 0, 0),
                "BTC",
                new BigDecimal("47143.98")));
    }};

    private List<String> expectedCryptoNames = Arrays.asList("BTC", "ETH");

    @Test
    void should_return_crypto_names_from_file_folder() {
        ReflectionTestUtils.setField(testedInstance, "directoryPath", testDirectoryPath);
        ReflectionTestUtils.setField(testedInstance, "fileNameRegex", testRegex);
        List<String> actualCryptoNames = testedInstance.getCryptoNames();
        assertEquals(expectedCryptoNames, actualCryptoNames);
    }

    @Test
    void should_parse_csv_file_without_exception() throws IOException {
        ReflectionTestUtils.setField(testedInstance, "directoryPath", testDirectoryPath);
        List<CryptoItem> actualList = testedInstance.getCryptosByName("BTC").getCryptoItems();
        assertNotNull(actualList);
        assertEquals(expectedCryptos, actualList);
    }

    @Test
    void should_throw_csv_file_reader_exception_when_unable_to_parse_csv_file() {
        assertThrows(CsvFileReaderServiceException.class, () -> {
                    testedInstance.getCryptosByName("NOT_EXISTS");
                }
        );
    }
}