package com.epam.cryptorecommendationservice.controller;

import com.epam.cryptorecommendationservice.model.Crypto;
import com.epam.cryptorecommendationservice.model.CryptoItem;
import com.epam.cryptorecommendationservice.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/cryptos")
public class CryptoController {

    @Autowired
    private CryptoService cryptoService;

    @GetMapping("{cryptoName}/max")
    public CryptoItem getMaxCryptoPrice(@PathVariable String cryptoName) throws IOException {
        return cryptoService.getMaxCrypto(cryptoName);
    }

    @GetMapping("{cryptoName}/min")
    public CryptoItem getMinCryptoPrice(@PathVariable String cryptoName) throws IOException {
        return cryptoService.getMinCrypto(cryptoName);
    }

    @GetMapping("{cryptoName}/newest")
    public CryptoItem getNewestCryptoPrice(@PathVariable String cryptoName) throws IOException {
        return cryptoService.getNewestCrypto(cryptoName);
    }

    @GetMapping("{cryptoName}/oldest")
    public CryptoItem getOldestCryptoPrice(@PathVariable String cryptoName) throws IOException {
        return cryptoService.getOldestCrypto(cryptoName);
    }

    @GetMapping("sorted")
    public List<Crypto> getDescSortedCryptos() {
        return cryptoService.getDescSortedCryptosByRange();
    }

    @GetMapping("{date}/highest")
    public Crypto getCryptoWithHighestPricePerDay(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return cryptoService.getCryptoWithHighestRangeByDate(date);
    }

}
