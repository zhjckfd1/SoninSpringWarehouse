package com.example.sonin_spring_warehouse.exception;

import com.example.sonin_spring_warehouse.Constants;

public class ProductNotFoundException extends MyException{

    public ProductNotFoundException() {
        super(Constants.PRODUCT_NOT_FOUND);
    }

    public ProductNotFoundException(Long id) {
        super(Constants.PRODUCT_BY_ID_NOT_FOUND + id);
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
