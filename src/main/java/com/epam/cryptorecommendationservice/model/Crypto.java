package com.epam.cryptorecommendationservice.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crypto {
    @CsvBindByName(column = "timestamp")
    private Instant timestamp;
    @CsvBindByName(column = "symbol")
    private String cryptoName;
    @CsvBindByName(column = "price")
    @CsvNumber(value = "#0.00")
    private BigDecimal price;
}
