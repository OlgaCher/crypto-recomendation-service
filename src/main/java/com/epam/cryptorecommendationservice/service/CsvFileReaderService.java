package com.epam.cryptorecommendationservice.service;

import com.epam.cryptorecommendationservice.model.Crypto;

import java.io.IOException;
import java.util.List;

public interface CsvFileReaderService {
    List<Crypto> getCryptosByName(String cryptoName);
    List<String> getCryptoNames();
}
