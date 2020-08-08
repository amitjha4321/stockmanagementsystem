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
    @JoinColumn(name = "item_id", nullable = false, referencedColumnName = "id")
    @JsonManagedReference
    private Item item;

    private int quantity;

    private float discountPercent;

    private String couponApplied;

    private float effectiveItemRate;

    public OrderedItem() {
    }

    public OrderedItem(Item item, int quantity, float effectiveItemRate, float discountPercent, String couponApplied) {
        this.item = item;
        this.quantity = quantity;
        this.effectiveItemRate = effectiveItemRate;
        this.discountPercent = discountPercent;
        this.couponApplied = couponApplied;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItemName() {
        return item;
    }

    public void setItemName(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(float discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getCouponApplied() {
        return couponApplied;
    }

    public void setCouponApplied(String couponApplied) {
        this.couponApplied = couponApplied;
    }

    public float getEffectiveItemRate() {
        return effectiveItemRate;
    }

    public void setEffectiveItemRate(float effectiveItemRate) {
        this.effectiveItemRate = effectiveItemRate;
    }

    @Override
    public String toString() {
        return "OrderedItem{" +
                "id=" + id +
                ", item=" + item +
                ", quantity=" + quantity +
                ", discountPercent=" + discountPercent +
                ", couponApplied='" + couponApplied + '\'' +
                ", effectiveItemRate=" + effectiveItemRate +
                '}';
    }
}
