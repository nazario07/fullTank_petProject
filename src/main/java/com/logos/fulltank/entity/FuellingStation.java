package com.logos.fulltank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
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

    @Column(name = "number_of_pumps")
    private  int numberOfPumps;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "address")
    private String address;

    @Column(name = "available")
    private boolean available = true;

    @OneToMany(mappedBy = "fuellingStation",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Product> products;

    @OneToMany(mappedBy = "fuellingStation", fetch = FetchType.LAZY)
    private  Set<Pump> pumpSet;

    public FuellingStation(String providerOrBrand, int numberOfPumps, double latitude, double longitude, String address) {
        this.providerOrBrand = providerOrBrand;
        this.numberOfPumps = numberOfPumps;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuellingStation that = (FuellingStation) o;
        return id == that.id && numberOfPumps == that.numberOfPumps && Double.compare(that.latitude, latitude) == 0 && Double.compare(that.longitude, longitude) == 0 && available == that.available && Objects.equals(providerOrBrand, that.providerOrBrand) && Objects.equals(address, that.address) && Objects.equals(products, that.products) && Objects.equals(pumpSet, that.pumpSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, providerOrBrand, numberOfPumps, latitude, longitude, address, available);
    }

    @Override
    public String toString() {
        return "FuellingStation{" +
                "id=" + id +
                ", providerOrBrand='" + providerOrBrand + '\'' +
                ", numberOfPumps=" + numberOfPumps +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", address='" + address + '\'' +
                ", pumpSet=" + pumpSet +
                '}';
    }
}
