package com.example.sonin_spring_warehouse.exception;

import com.example.sonin_spring_warehouse.Constants;

public class ProductQuantityNotFoundException extends MyException{

    public ProductQuantityNotFoundException() {
            super(Constants.CURRENT_QUANTITY_NOT_FOUND);
        }

    public ProductQuantityNotFoundException(String message) {
            super(message);
        }
    }

