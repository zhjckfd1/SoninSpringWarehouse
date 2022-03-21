package com.example.sonin_spring_warehouse.controller;

import com.example.sonin_spring_warehouse.exception.ProductAlreadyExistsException;
import com.example.sonin_spring_warehouse.exception.ProductNotFoundException;
import com.example.sonin_spring_warehouse.exception.ProductQuantityNotFoundException;
import com.example.sonin_spring_warehouse.exception.need_body.BaseError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlingController {

    @ResponseBody
    @ResponseStatus(value= HttpStatus.NOT_FOUND)  // 404
    @ExceptionHandler({ProductNotFoundException.class})
    public BaseError notFound(RuntimeException ex) {
        return new BaseError(ex.getMessage());
    }

    @ResponseBody
    @ResponseStatus(value= HttpStatus.BAD_REQUEST)  // 400
    @ExceptionHandler({ProductAlreadyExistsException.class,
                       ProductQuantityNotFoundException.class})
    public BaseError badRequest(RuntimeException ex) {
        return new BaseError(ex.getMessage());
    }
}
