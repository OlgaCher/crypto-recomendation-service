package com.epam.cryptorecommendationservice.service;

import com.epam.cryptorecommendationservice.model.Crypto;

import java.util.List;

public interface CsvFileReaderService {
    Crypto getCryptosByName(String cryptoName);
    List<String> getCryptoNames();
}
