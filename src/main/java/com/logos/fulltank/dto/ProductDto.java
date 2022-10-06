package com.logos.fulltank.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.logos.fulltank.entity.FuelName;
import com.logos.fulltank.entity.FuellingStation;
import com.logos.fulltank.entity.Pump;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;
@Data
public class ProductDto  implements Serializable {

    private int id;

    private FuelName nameOfFuel;

    private double availableAmountOfFuel;

    private double pricePerLiterInHrn;

    private FuellingStation fuellingStation;
    @JsonIgnore
    private Set<Pump> pumpSet;
}
