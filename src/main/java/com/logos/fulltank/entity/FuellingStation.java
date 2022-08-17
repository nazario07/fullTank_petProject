package com.logos.fulltank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "fuelling_station")
public class FuellingStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "brand")
    private String providerOrBrand;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "address")
    private String address;

    @Column(name = "available")
    private boolean available;

    @OneToMany(mappedBy = "fuellingStation",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Product> products;

    @OneToMany(mappedBy = "fuellingStation", fetch = FetchType.LAZY)
    private  Set<Pump> pumpSet;


}
