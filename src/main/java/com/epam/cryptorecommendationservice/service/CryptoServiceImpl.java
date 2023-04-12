package com.epam.cryptorecommendationservice.service;

import com.epam.cryptorecommendationservice.model.Crypto;
import com.epam.cryptorecommendationservice.model.NormalizedCrypto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CryptoServiceImpl implements CryptoService {
    @Autowired
    CsvFileReaderService csvFileReaderService;

    @Override
    public Crypto getMaxCrypto(String cryptoName) throws IOException {
        List<Crypto> cryptos = csvFileReaderService.getCryptosByName(cryptoName);
        return cryptos.stream().max(Comparator.comparing(Crypto::getPrice)).get();
    }

    @Override
    public Crypto getMinCrypto(String cryptoName) throws IOException {
        List<Crypto> cryptos = csvFileReaderService.getCryptosByName(cryptoName);
        return cryptos.stream().min(Comparator.comparing(Crypto::getPrice)).get();
    }

    @Override
    public Crypto getNewestCrypto(String cryptoName) throws IOException {
        List<Crypto> cryptos = csvFileReaderService.getCryptosByName(cryptoName);
        return cryptos.stream().max(Comparator.comparing(Crypto::getDateTime)).get();
    }

    @Override
    public Crypto getOldestCrypto(String cryptoName) throws IOException {
        List<Crypto> cryptos = csvFileReaderService.getCryptosByName(cryptoName);
        return cryptos.stream().min(Comparator.comparing(Crypto::getDateTime)).get();
    }

    @Override
    public List<NormalizedCrypto> getDescSortedCryptosByRange() {
        return csvFileReaderService.getCryptoNames().stream().map(this::getNormalisedCrypto).
                sorted(Comparator.comparing(NormalizedCrypto::getNormalisedPrice).reversed()).
                collect(Collectors.toList());
    }

    @Override
    public Crypto getCryptoWithHighestRangeByDate(LocalDate date) {
//        return csvFileReaderService.getCryptoNames()
//                .stream()
//                .collect(Collectors.groupingBy(Crypto::getDateTime)).max(Comparator.comparing(NormalisedCrypto::getNormalisedPrice))
        return null;
    }

    private NormalizedCrypto getNormalisedCrypto(String cryptoName) {
        List<Crypto> cryptos = csvFileReaderService.getCryptosByName(cryptoName);
        BigDecimal maxPrice = getMaxPrice(cryptos);
        BigDecimal minPrice = getMinPrice(cryptos);
        return NormalizedCrypto.builder().
                cryptoName(cryptoName).
                normalisedPrice(maxPrice.subtract(minPrice).divide(minPrice, 2, RoundingMode.HALF_UP)).
                build();
    }

    private NormalizedCrypto getNormalizedCryptoByDate(String cryptoName, LocalDate date) throws IOException {
        List<Crypto> cryptos = csvFileReaderService.getCryptosByName(cryptoName);
//        List<Crypto> filteredByDate = cryptos.stream().collect(Collectors.groupingBy(Crypto::getDateTime)).get()
//                .filter(d -> d.getDateTime().equals(date)).collect(Collectors.toList());
//        BigDecimal maxPrice = getMaxPrice(filteredByDate);
//        BigDecimal minPrice = getMinPrice(filteredByDate);
        return NormalizedCrypto.builder().
                cryptoName(cryptoName).
//                normalisedPrice(maxPrice.subtract(minPrice).divide(minPrice, 2, RoundingMode.HALF_UP)).
        build();
    }

    private static BigDecimal getMinPrice(List<Crypto> cryptos) {
        return cryptos.stream().min(Comparator.comparing(Crypto::getPrice)).get().getPrice();
    }

    private static BigDecimal getMaxPrice(List<Crypto> cryptos) {
        return cryptos.stream().max(Comparator.comparing(Crypto::getPrice)).get().getPrice();
    }

}
