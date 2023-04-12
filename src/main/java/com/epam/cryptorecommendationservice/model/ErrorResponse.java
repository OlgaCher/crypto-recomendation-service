package com.epam.cryptorecommendationservice.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ErrorResponse {
    private final String errorCode;
    private final String errorMessage;
}
