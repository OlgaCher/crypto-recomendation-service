package com.epam.cryptorecommendationservice.service;

import com.epam.cryptorecommendationservice.exceptions.CsvFileReaderServiceException;
import com.epam.cryptorecommendationservice.model.Crypto;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CsvFileReaderServiceImpl implements CsvFileReaderService {
    public static final int TABLE_HEADER = 1;
    @Value("${directory.path}")
    private String directoryPath;

    @Value("${file.extension}")
    private String fileExtension;

    @Value("${filename.regex}")
    private String fileNameRegex;


    public List<String> getCryptoNames() {
        File[] files = getAllCsvFilesFromPathByRegex(directoryPath);
        return Stream.of(files).map(file ->
                file.getName().substring(0, file.getName().lastIndexOf("_"))).collect(Collectors.toList());
    }

    private File[] getAllCsvFilesFromPathByRegex(String directoryPath) {
        FileFilter fileFilter = new RegexFileFilter(fileNameRegex);
        return new File(directoryPath).listFiles(fileFilter);
    }

    @Cacheable("preloadFiles")
    @Override
    public List<Crypto> readFileByName(String cryptoName) {
        List<Crypto> cryptos = new ArrayList<>();

        try (CSVReader reader = new CSVReaderBuilder(new FileReader(String.format("%s%s_values.csv", directoryPath, cryptoName))).
                withSkipLines(TABLE_HEADER).
                build()) {
            cryptos = reader.readAll().stream().
                    map(row -> {
                        Crypto crypto = new Crypto();
                        crypto.setDate(LocalDate.ofInstant(Instant.ofEpochMilli(Long.valueOf(row[0])), ZoneId.systemDefault()));
                        crypto.setCryptoName(row[1]);
                        crypto.setPrice(new BigDecimal(row[2]));
                        return crypto;
                    }).collect(Collectors.toList());
        } catch (IOException | CsvException e) {
            throw new CsvFileReaderServiceException(String.format("Unable to parse .csv file. File path: %s", directoryPath));
        }
        return cryptos;
    }
}
