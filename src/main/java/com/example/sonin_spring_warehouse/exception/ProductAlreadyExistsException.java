package com.example.sonin_spring_warehouse.exception;

import com.example.sonin_spring_warehouse.Constants;

public class ProductAlreadyExistsException extends MyException{
    public ProductAlreadyExistsException() {
        super(Constants.PRODUCT_ALREADY_EXISTS);
    }
    public ProductAlreadyExistsException(String message) {
        super(message);
    }
}
