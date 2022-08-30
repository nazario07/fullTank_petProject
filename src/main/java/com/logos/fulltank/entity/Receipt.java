package com.logos.fulltank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "receipts")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "date")
    private Date date;
    @Column(name = "name_of_product")
    private FuelName productName;
    @Column(name = "price")
    private double price;
    @Column(name = "amount")
    private double amount;
    @Column(name = "total")
    private double total;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Receipt(Date date, FuelName productName, double price, double amount, double total, User user) {
        this.date = date;
        this.productName = productName;
        this.price = price;
        this.amount = amount;
        this.total = total;
        this.user = user;
    }
}
