package com.epam.cryptorecommendationservice.service;

import com.epam.cryptorecommendationservice.exceptions.CsvFileReaderServiceException;
import com.epam.cryptorecommendationservice.exceptions.UnsupportedCryptoException;
import com.epam.cryptorecommendationservice.model.Crypto;
import com.epam.cryptorecommendationservice.model.CryptoItem;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CsvFileReaderServiceImpl implements CsvFileReaderService {
    public static final int TABLE_HEADER = 1;
    @Value("${directory.path}")
    private String directoryPath;

    @Value("${filename.regex}")
    private String fileNameRegex;
    @Value("${name.pattern}")
    private String fileNamePattern;

    @Cacheable("preloadCryptoNames")
    @Override
    public List<String> getCryptoNames() {
        File[] files = getAllCsvFilesFromPathByRegex(directoryPath, fileNameRegex);
        return Stream.of(files).map(file ->
                file.getName().substring(0, file.getName().lastIndexOf("_"))).collect(Collectors.toList());
    }

    @Cacheable("preloadFiles")
    @Override
    public Crypto getCryptosByName(String cryptoName) {
        Crypto crypto;
        if (!getCryptoNames().contains(cryptoName.toUpperCase())){
            throw new UnsupportedCryptoException(String.format("We are currently don't support this crypto: %s",cryptoName.toUpperCase()));
        }
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(String.format(fileNamePattern, directoryPath, cryptoName))).
                withSkipLines(TABLE_HEADER).
                build()) {

            crypto = Crypto.builder().cryptoItems(reader.readAll().stream().
                    map(row -> {
                        CryptoItem cryptoItem = new CryptoItem();
                        cryptoItem.setDateTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.valueOf(row[0])), ZoneId.systemDefault()));
                        cryptoItem.setCryptoName(row[1]);
                        cryptoItem.setPrice(new BigDecimal(row[2]));
                        return cryptoItem;
                    }).collect(Collectors.toList())).build();
        } catch (CsvException | IOException e) {
            throw new CsvFileReaderServiceException(String.format("Unable to parse .csv file. File path: %s", directoryPath));
        }
        return crypto;
    }

    private static File[] getAllCsvFilesFromPathByRegex(String directoryPath, String fileNameRegex) {
        FileFilter fileFilter = new RegexFileFilter(fileNameRegex);
        return new File(directoryPath).listFiles(fileFilter);
    }

}
