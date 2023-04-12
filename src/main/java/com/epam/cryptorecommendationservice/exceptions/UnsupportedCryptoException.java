package com.epam.cryptorecommendationservice.exceptions;

public class UnsupportedCryptoException extends RuntimeException {
    public UnsupportedCryptoException(String message) {
        super(message);
    }
}
