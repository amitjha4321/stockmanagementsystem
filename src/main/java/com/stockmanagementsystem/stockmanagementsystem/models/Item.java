package com.stockmanagementsystem.stockmanagementsystem.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "Items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String code;

    private String unit;

    private String batchNumber;


    private float itemRate;

    private String manufactureDate;

    private String expiryDate;




    @ManyToOne
    @JoinColumn(name = "category", nullable = false, referencedColumnName = "id")
    @JsonManagedReference
    private Category category;

    public Item(String name, String code, String unit, String batchNumber, Category category) {
        this.name = name;
        this.code = code;
        this.unit = unit;
        this.batchNumber = batchNumber;
        this.category = category;
    }

    public Item() {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", unit='" + unit + '\'' +
                ", batchNumber='" + batchNumber + '\'' +
                ", category=" + category +
                '}';
    }
}
