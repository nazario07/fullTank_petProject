package com.logos.fulltank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "pump")
public class Pump {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_of_pump")
    private String pumpName;

    @Column(name = "available")
    private boolean available= true;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "pump_product",
            joinColumns = @JoinColumn(name = "pump_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> productSet;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private FuellingStation fuellingStation;

    public Pump(String pumpName, FuellingStation fuellingStation) {
        this.pumpName = pumpName;
        this.fuellingStation = fuellingStation;
    }
}
