package com.epam.cryptorecommendationservice.service;

import com.epam.cryptorecommendationservice.model.Crypto;

import java.util.List;

public interface CsvFileReaderService {
    List<Crypto> readFileByName(String filePath);
    List<String> getCryptoNames();
}
