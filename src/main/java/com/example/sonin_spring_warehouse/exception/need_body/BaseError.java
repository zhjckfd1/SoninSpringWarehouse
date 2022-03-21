package com.example.sonin_spring_warehouse.exception.need_body;

import com.example.sonin_spring_warehouse.Constants;
import java.time.LocalDateTime;

public class BaseError {
    private final String message;
    private final LocalDateTime localDateTime = LocalDateTime.now();

    public BaseError(){
        this.message = Constants.UNKNOWN_ERROR;
    }

    public BaseError(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
