package com.logos.fulltank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name_of_fuel")
    private FuelName nameOfFuel;
    @Column(name = "real_amount_of_fuel")
    private double availableAmountOfFuel;
    @Column(name = "price_per_liter")
    private  double pricePerLiterInHrn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fuelling_station_id")
    private FuellingStation fuellingStation;

    @ManyToMany(mappedBy = "productSet")
    private Set<Pump> pumpSet;


}
