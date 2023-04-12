package com.epam.cryptorecommendationservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class Crypto {
    @JsonIgnore
    private List<CryptoItem> cryptoItems = new ArrayList<>();

    @JsonProperty("name")
    public String getName() {
        return cryptoItems.stream().findFirst().get().getCryptoName();
    }
    @JsonProperty("normalizedValue")
    public BigDecimal getNormalized() {
        BigDecimal minPrice = getWithMinPrice().getPrice();
        return getWithMaxPrice().getPrice().subtract(minPrice).divide(minPrice, 2, RoundingMode.HALF_UP);
    }
    @JsonIgnore
    public CryptoItem getOldest(){
       return cryptoItems.stream().min(Comparator.comparing(CryptoItem::getDateTime)).get();
    }

    @JsonIgnore
    public CryptoItem getNewest(){
       return cryptoItems.stream().max(Comparator.comparing(CryptoItem::getDateTime)).get();
    }
    @JsonIgnore
    public CryptoItem getWithMinPrice() {
        return cryptoItems.stream().min(Comparator.comparing(CryptoItem::getPrice)).get();
    }
    @JsonIgnore
    public CryptoItem getWithMaxPrice() {
        return cryptoItems.stream().max(Comparator.comparing(CryptoItem::getPrice)).get();
    }

    public List<CryptoItem> filterBy(LocalDate date) {
        return cryptoItems.stream().filter(cryptoItem -> cryptoItem.getDateTime().toLocalDate().equals(date)).collect(Collectors.toList());
    }
}
