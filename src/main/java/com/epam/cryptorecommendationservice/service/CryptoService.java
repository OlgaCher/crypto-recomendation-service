package com.epam.cryptorecommendationservice.service;

import com.epam.cryptorecommendationservice.model.Crypto;
import com.epam.cryptorecommendationservice.model.NormalizedCrypto;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface CryptoService {

    Crypto getMaxCrypto(String cryptoName) throws IOException;

    Crypto getMinCrypto(String cryptoName) throws IOException;

    Crypto getNewestCrypto(String cryptoName) throws IOException;

    Crypto getOldestCrypto(String cryptoName) throws IOException;

    List<NormalizedCrypto> getDescSortedCryptosByRange();

    Crypto getCryptoWithHighestRangeByDate(LocalDate date);
}
