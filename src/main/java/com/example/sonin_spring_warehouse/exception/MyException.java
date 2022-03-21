package com.example.sonin_spring_warehouse.exception;

import com.example.sonin_spring_warehouse.Constants;

public class MyException extends RuntimeException{
    public MyException() {
        super(Constants.UNKNOWN_ERROR);
    }
    public MyException(String message) {
        super(message);
    }
}
