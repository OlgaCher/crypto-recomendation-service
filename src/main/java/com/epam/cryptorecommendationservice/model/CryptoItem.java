package com.epam.cryptorecommendationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CryptoItem {
    private LocalDateTime dateTime;
    private String cryptoName;
    private BigDecimal price;

}
