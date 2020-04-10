package com.stockmanagementsystem.stockmanagementsystem.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "vendors")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Vendor code cannot be blank")
    private String code;

    @NotBlank(message = "Name of vendor cannot be blank")
    private String name;

    private String address;

    private String telephone;

    @NotBlank(message = "PAN number of vendor cannot be blank")
    private String panNumber;

    @NotBlank(message = "VAT number of vendor cannot be blank")
    private String vatNumber;

    @NotBlank(message = "Name of vendor cannot be blank")
    private String emergencyContact;

    private String phone;


    public Vendor() {
    }

    public Vendor(String code, String name, String address, String telephone, String panNumber, String vatNumber,  String emergencyContact, String phone) {
        this.code = code;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.panNumber = panNumber;
        this.vatNumber = vatNumber;
        this.emergencyContact = emergencyContact;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Vendor{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", panNumber='" + panNumber + '\'' +
                ", vatNumber='" + vatNumber + '\'' +
                ", emergencyContact='" + emergencyContact + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
