package com.example.sonin_spring_warehouse.dto;

public class PriceDto {
    private Integer min = 0;
    private Integer max = Integer.MAX_VALUE;

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

}
