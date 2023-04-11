package com.epam.cryptorecommendationservice.service;

import com.epam.cryptorecommendationservice.model.Crypto;

import java.time.LocalDate;
import java.util.List;

public interface CryptoService {

    Crypto getMaxCrypto(String cryptoName);

    Crypto getMinCrypto(String cryptoName);

    Crypto getNewestCrypto(String cryptoName);

    Crypto getOldestCrypto(String cryptoName);

    List<Crypto> getDescSortedCryptosByRange(String cryptoName);

    Crypto getCryptoWithHighestRangeByDate(LocalDate date);
}
