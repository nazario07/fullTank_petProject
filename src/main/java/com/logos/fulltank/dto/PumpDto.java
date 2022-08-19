package com.logos.fulltank.dto;

import com.logos.fulltank.entity.FuellingStation;
import com.logos.fulltank.entity.Product;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;
@Data
public class PumpDto implements Serializable {
    private int id;

    private String pumpName;


    private boolean available;

    private Set<Product> productSet;

    private FuellingStation fuellingStation;
}
