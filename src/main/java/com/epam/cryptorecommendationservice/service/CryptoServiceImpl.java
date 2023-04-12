package com.epam.cryptorecommendationservice.service;

import com.epam.cryptorecommendationservice.model.Crypto;
import com.epam.cryptorecommendationservice.model.NormalisedCrypto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CryptoServiceImpl implements CryptoService {
    @Autowired
    CsvFileReaderService csvFileReaderService;

    @Override
    public Crypto getMaxCrypto(String cryptoName) {
        List<Crypto> cryptos = csvFileReaderService.readFileByName(cryptoName);
        return cryptos.stream().max(Comparator.comparing(Crypto::getPrice)).get();
    }

    @Override
    public Crypto getMinCrypto(String cryptoName) {
        List<Crypto> cryptos = csvFileReaderService.readFileByName(cryptoName);
        return cryptos.stream().min(Comparator.comparing(Crypto::getPrice)).get();
    }

    @Override
    public Crypto getNewestCrypto(String cryptoName) {
        List<Crypto> cryptos = csvFileReaderService.readFileByName(cryptoName);
        return cryptos.stream().max(Comparator.comparing(Crypto::getDate)).get();
    }

    @Override
    public Crypto getOldestCrypto(String cryptoName) {
        List<Crypto> cryptos = csvFileReaderService.readFileByName(cryptoName);
        return cryptos.stream().min(Comparator.comparing(Crypto::getDate)).get();
    }

    @Override
    public List<NormalisedCrypto> getDescSortedCryptosByRange() {
        return csvFileReaderService.getCryptoNames().stream().map(this::getNormalisedCrypto)
                .sorted(Comparator.comparing(NormalisedCrypto::getNormalisedPrice)
                        .reversed())
                .collect(Collectors.toList());

    }

    private NormalisedCrypto getNormalisedCrypto(String cryptoName) {
        List<Crypto> cryptos = csvFileReaderService.readFileByName(cryptoName);
        BigDecimal maxPrice = cryptos.stream().max(Comparator.comparing(Crypto::getPrice)).get().getPrice();
        BigDecimal minPrice = cryptos.stream().min(Comparator.comparing(Crypto::getPrice)).get().getPrice();
        return NormalisedCrypto.builder().
                cryptoName(cryptoName).
                normalisedPrice(maxPrice.subtract(minPrice).divide(minPrice, 2, BigDecimal.ROUND_HALF_UP)).
                build();
    }


    @Override
    public Crypto getCryptoWithHighestRangeByDate(LocalDate date) {
        return null;
    }
}
