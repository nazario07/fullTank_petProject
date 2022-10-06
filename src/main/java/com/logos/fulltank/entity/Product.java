package com.logos.fulltank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
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

    @Override
    public String toString() {
        return "Product{" +
                "nameOfFuel=" + nameOfFuel +
                ", pricePerLiterInHrn=" + pricePerLiterInHrn +
                '}';
    }

    public Product( FuelName nameOfFuel, double pricePerLiterInHrn) {
        this.nameOfFuel = nameOfFuel;
        this.pricePerLiterInHrn = pricePerLiterInHrn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Double.compare(product.availableAmountOfFuel, availableAmountOfFuel) == 0 && Double.compare(product.pricePerLiterInHrn, pricePerLiterInHrn) == 0 && nameOfFuel == product.nameOfFuel && Objects.equals(fuellingStation, product.fuellingStation) && Objects.equals(pumpSet, product.pumpSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameOfFuel, availableAmountOfFuel, pricePerLiterInHrn);
    }
}
