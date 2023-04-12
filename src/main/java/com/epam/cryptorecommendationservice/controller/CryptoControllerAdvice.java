package com.epam.cryptorecommendationservice.controller;

import com.epam.cryptorecommendationservice.exceptions.UnsupportedCryptoException;
import com.epam.cryptorecommendationservice.model.ErrorResponse;
import com.epam.cryptorecommendationservice.exceptions.CsvFileReaderServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CryptoControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {CsvFileReaderServiceException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ResponseEntity<ErrorResponse> handleCsvFileReaderException(CsvFileReaderServiceException ex){
        ErrorResponse response = new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.toString(), ex.getMessage());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @ExceptionHandler(value = {UnsupportedCryptoException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<ErrorResponse> handleUnsupportedFileException(UnsupportedCryptoException ex){
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
