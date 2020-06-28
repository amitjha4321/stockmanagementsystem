package com.stockmanagementsystem.stockmanagementsystem.models;


import javax.persistence.*;

@Entity
@Table(name = "paymentType")
public class PaymentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public PaymentType() {
    }

    public PaymentType(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PaymentType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

