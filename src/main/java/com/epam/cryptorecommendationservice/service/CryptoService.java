package com.epam.cryptorecommendationservice.service;

import com.epam.cryptorecommendationservice.model.Crypto;
import com.epam.cryptorecommendationservice.model.NormalisedCrypto;

import java.time.LocalDate;
import java.util.List;

public interface CryptoService {

    Crypto getMaxCrypto(String cryptoName);

    Crypto getMinCrypto(String cryptoName);

    Crypto getNewestCrypto(String cryptoName);

    Crypto getOldestCrypto(String cryptoName);

    List<NormalisedCrypto> getDescSortedCryptosByRange();

    Crypto getCryptoWithHighestRangeByDate(LocalDate date);
}
