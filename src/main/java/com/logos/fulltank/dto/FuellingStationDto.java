package com.logos.fulltank.dto;

import com.logos.fulltank.entity.Product;
import com.logos.fulltank.entity.Pump;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class FuellingStationDto  implements Serializable {
    private int id;

    private String providerOrBrand;

    private  int numberOfPumps;

    private double latitude;

    private double longitude;

    private String address;

    private boolean available;

    private Set<Product> products;

    private Set<Pump> pumpSet;
}
