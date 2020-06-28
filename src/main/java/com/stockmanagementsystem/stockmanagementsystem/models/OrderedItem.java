package com.stockmanagementsystem.stockmanagementsystem.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "OrderedItem")
public class OrderedItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @NotBlank(message = "OrderedItem code cannot be blank")
//    private String code;

    @ManyToOne
    @JoinColumn(name = "itemName", nullable = false, referencedColumnName = "id")
    @JsonManagedReference
    private Item itemName;

    private int quantity;

    private float itemRate;

    private String manufactureDate;

    private String expiryDate;

    public OrderedItem() {
    }

    public OrderedItem(Item itemName, int quantity, float itemRate, String manufactureDate, String expiryDate) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.itemRate = itemRate;
        this.manufactureDate = manufactureDate;
        this.expiryDate = expiryDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItemName() {
        return itemName;
    }

    public void setItemName(Item itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getItemRate() {
        return itemRate;
    }

    public void setItemRate(float itemRate) {
        this.itemRate = itemRate;
    }

    public String getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(String manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "OrderedItem{" +
                "id=" + id +
                ", itemName=" + itemName +
                ", quantity=" + quantity +
                ", itemRate=" + itemRate +
                ", manufactureDate='" + manufactureDate + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                '}';
    }
}
