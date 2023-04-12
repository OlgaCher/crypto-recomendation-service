package com.epam.cryptorecommendationservice.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
public class NormalizedCrypto {
    private String cryptoName;
    private BigDecimal normalisedPrice;
    private LocalDate date;
}
