package com.epam.cryptorecommendationservice.service;

import com.epam.cryptorecommendationservice.model.Crypto;
import com.epam.cryptorecommendationservice.model.CryptoItem;

import java.time.LocalDate;
import java.util.List;

public interface CryptoService {

    CryptoItem getMaxCrypto(String cryptoName);

    CryptoItem getMinCrypto(String cryptoName);

    CryptoItem getNewestCrypto(String cryptoName);

    CryptoItem getOldestCrypto(String cryptoName);

    List<Crypto> getDescSortedCryptosByRange();

    Crypto getCryptoWithHighestRangeByDate(LocalDate date);
}
