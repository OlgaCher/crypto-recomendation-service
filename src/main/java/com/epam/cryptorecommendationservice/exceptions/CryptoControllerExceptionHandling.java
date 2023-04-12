package com.epam.cryptorecommendationservice.exceptions;

import com.epam.cryptorecommendationservice.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class CryptoControllerExceptionHandling  extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {CsvFileReaderServiceException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ResponseEntity<ErrorResponseDto> handleCsvFileReaderException(CsvFileReaderServiceException ex){
        ErrorResponseDto responseDto = new ErrorResponseDto(HttpStatus.CONFLICT.toString(), ex.getMessage());
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @ExceptionHandler(value = {IOException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ResponseEntity<ErrorResponseDto> handleIOException(IOException ex){
        ErrorResponseDto responseDto = new ErrorResponseDto(HttpStatus.CONFLICT.toString(), ex.getMessage());
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
}
