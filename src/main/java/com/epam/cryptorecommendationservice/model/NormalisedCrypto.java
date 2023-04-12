package com.epam.cryptorecommendationservice.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class NormalisedCrypto {
    private String cryptoName;
    private BigDecimal normalisedPrice;
}
