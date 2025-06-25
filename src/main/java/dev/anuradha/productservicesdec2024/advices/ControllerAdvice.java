package dev.anuradha.productservicesdec2024.advices;

import dev.anuradha.productservicesdec2024.dtos.ErrorDTO;
import dev.anuradha.productservicesdec2024.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleProductNotFoundException(ProductNotFoundException productNotFoundException){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(productNotFoundException.getMessage());

        ResponseEntity<ErrorDTO> responseEntity = new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
        return responseEntity;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleGenericException(Exception ex){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(ex.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }
}
