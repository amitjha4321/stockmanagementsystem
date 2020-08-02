package com.stockmanagementsystem.stockmanagementsystem.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String invoiceno;
    private String customername;
    private String solditems;
    private String date;
    private int quantity;
    private float price;
    private float rate;

    public Sales() {
    }

    public Sales(String customername, String date, int quantity, float price, float rate, String invoiceno,String solditems) {
        this.invoiceno=invoiceno;
        this.customername = customername;
        this.solditems=solditems;
        this.date = date;
        this.quantity = quantity;
        this.price = price;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getInvoiceno() {
        return invoiceno;
    }

    public void setInvoiceno(String invoiceno) {
        this.invoiceno = invoiceno;
    }

    public String getSolditems() {
        return solditems;
    }

    public void setSolditems(String solditems) {
        this.solditems = solditems;
    }
}
