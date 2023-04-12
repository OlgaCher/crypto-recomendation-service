package com.epam.cryptorecommendationservice.controller;

import com.epam.cryptorecommendationservice.model.ErrorResponse;
import com.epam.cryptorecommendationservice.exceptions.CsvFileReaderServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class CryptoControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {CsvFileReaderServiceException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ResponseEntity<ErrorResponse> handleCsvFileReaderException(CsvFileReaderServiceException ex){
        ErrorResponse response = new ErrorResponse(HttpStatus.CONFLICT.toString(), ex.getMessage());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @ExceptionHandler(value = {IOException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ResponseEntity<ErrorResponse> handleIOException(IOException ex){
        ErrorResponse response = new ErrorResponse(HttpStatus.CONFLICT.toString(), ex.getMessage());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
