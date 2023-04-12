package com.epam.cryptorecommendationservice.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ErrorResponseDto {
    private final String errorCode;
    private final String errorMessage;
}
