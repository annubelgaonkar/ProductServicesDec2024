package dev.anuradha.productservicesdec2024.advices;

import dev.anuradha.productservicesdec2024.dtos.ErrorDTO;
import dev.anuradha.productservicesdec2024.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;


@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleProductNotProductNotFoundException(ProductNotFoundException productNotFoundException){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(productNotFoundException.getMessage());

        ResponseEntity<ErrorDTO> responseEntity = new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
        return responseEntity;
    }

}
