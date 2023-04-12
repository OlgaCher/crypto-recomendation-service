package com.epam.cryptorecommendationservice.service;

import com.epam.cryptorecommendationservice.model.Crypto;
import com.epam.cryptorecommendationservice.model.CryptoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CryptoServiceImpl implements CryptoService {
    @Autowired
    private CsvFileReaderService csvFileReaderService;

    @Override
    public CryptoItem getMaxCrypto(String cryptoName) {
        return csvFileReaderService.getCryptosByName(cryptoName).getWithMaxPrice();
    }

    @Override
    public CryptoItem getMinCrypto(String cryptoName) {
        return csvFileReaderService.getCryptosByName(cryptoName).getWithMinPrice();
    }

    @Override
    public CryptoItem getNewestCrypto(String cryptoName) {
        return csvFileReaderService.getCryptosByName(cryptoName).getNewest();
    }

    @Override
    public CryptoItem getOldestCrypto(String cryptoName) {
        return csvFileReaderService.getCryptosByName(cryptoName).getOldest();
    }

    @Override
    public List<Crypto> getDescSortedCryptosByRange() {
        return descendingSort(getAllCryptos());
    }

    @Override
    public Crypto getCryptoWithHighestRangeByDate(LocalDate date) {
        List<Crypto> cryptosByDate = getAllCryptos().stream()
                .map(crypto -> Crypto.builder().cryptoItems(crypto.filterBy(date)).build())
                .collect(Collectors.toList());
        return descendingSort(cryptosByDate).stream().findFirst().get();
    }
    private static List<Crypto> descendingSort(List<Crypto> cryptos) {
        return cryptos.stream().
                sorted(Comparator.comparing(Crypto::getNormalized).reversed()).
                collect(Collectors.toList());
    }
    private List<Crypto> getAllCryptos() {
        return csvFileReaderService.getCryptoNames()
                .stream()
                .map(name -> csvFileReaderService.getCryptosByName(name))
                .collect(Collectors.toList());
    }

}
