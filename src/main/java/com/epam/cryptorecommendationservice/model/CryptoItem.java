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
    //@CsvBindByName(column = "timestamp")
    private LocalDateTime dateTime;
    // @CsvBindByName(column = "symbol")
    private String cryptoName;
    //  @CsvBindByName(column = "price")
    // @CsvNumber(value = "#0.00")
    private BigDecimal price;

}
