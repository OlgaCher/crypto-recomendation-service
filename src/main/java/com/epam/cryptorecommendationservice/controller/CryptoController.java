package com.epam.cryptorecommendationservice.controller;

import com.epam.cryptorecommendationservice.model.Crypto;
import com.epam.cryptorecommendationservice.model.NormalizedCrypto;
import com.epam.cryptorecommendationservice.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/cryptos")
public class CryptoController {

    @Autowired
    private CryptoService cryptoService;

    @GetMapping("{cryptoName}/max")
    public Crypto getMaxCryptoPrice(@PathVariable String cryptoName) throws IOException {
        return cryptoService.getMaxCrypto(cryptoName);
    }

    @GetMapping("{cryptoName}/min")
    public Crypto getMinCryptoPrice(@PathVariable String cryptoName) throws IOException {
        return cryptoService.getMinCrypto(cryptoName);
    }

    @GetMapping("{cryptoName}/newest")
    public Crypto getNewestCryptoPrice(@PathVariable String cryptoName) throws IOException {
        return cryptoService.getNewestCrypto(cryptoName);
    }

    @GetMapping("{cryptoName}/oldest")
    public Crypto getOldestCryptoPrice(@PathVariable String cryptoName) throws IOException {
        return cryptoService.getOldestCrypto(cryptoName);
    }

    @GetMapping("sorted")
    public List<NormalizedCrypto> getDescSortedCryptos() {
        return cryptoService.getDescSortedCryptosByRange();
    }

    @GetMapping("{date}/normalized")
    public Crypto getCryptoWithHighestPricePerDay(@PathVariable String date) {
        return null;
    }

}
