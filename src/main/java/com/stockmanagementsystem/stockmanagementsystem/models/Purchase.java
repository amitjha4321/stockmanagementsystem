package com.stockmanagementsystem.stockmanagementsystem.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "billNumber", unique = true)
    private String billNumber;

    @ManyToOne
    @JoinColumn(name = "documentNumbering_id", nullable = false, referencedColumnName = "id")
    @JsonManagedReference
    private DocumentNumbering documentNumbering;

    @Column(name = "po_number")
    private int poNumber;

    //file upload
    private String poUpload;

    @ManyToOne
    @JoinColumn(name = "vendor", nullable = false, referencedColumnName = "id")
    @JsonManagedReference
    private Vendor vendor;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "purchase_orderedItem", joinColumns = @JoinColumn(name = "purchase_id"), inverseJoinColumns = @JoinColumn(name = "orderedItem_id"))
    private List<OrderedItem> orderedItems;


    private float totalPrice;

    //full or partial payment
    private String paymentMethod;

    private float amountPaid;

    private float remainingAmount;

    //cash or cheque
    private String paymentType;

    //if cheque payment is made
    private String bankName;

    private String chequeNumber;

    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private String paymentDate;

    @Lob
    private byte[] fileUpload;

    private String fileName;

    private String fileType;

    //file upload

    public Purchase() {
    }



    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public byte[] getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(byte[] fileUpload) {
        this.fileUpload = fileUpload;
    }

    public Purchase(String billNumber, DocumentNumbering documentNumbering, int poNumber,
                    String poUpload, Vendor vendor, List<OrderedItem> orderedItems,
                    float totalPrice, String paymentMethod, float amountPaid,
                    float remainingAmount, String paymentType, String bankName,
                    String chequeNumber,String paymentDate, byte[] fileUpload,
                    String fileName,String fileType) {
        this.billNumber = billNumber;
        this.documentNumbering = documentNumbering;
        this.poNumber = poNumber;
        this.poUpload = poUpload;
        this.vendor = vendor;
        this.orderedItems = orderedItems;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
        this.amountPaid = amountPaid;
        this.remainingAmount = remainingAmount;
        this.paymentType = paymentType;
        this.bankName = bankName;
        this.chequeNumber = chequeNumber;
        this.paymentDate = paymentDate;
        this.fileUpload = fileUpload;
        this.fileName=fileName;
        this.fileType=fileType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public DocumentNumbering getDocumentNumbering() {
        return documentNumbering;
    }

    public void setDocumentNumbering(DocumentNumbering documentNumbering) {
        this.documentNumbering = documentNumbering;
    }

    public int getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(int poNumber) {
        this.poNumber = poNumber;
    }

    public String getPoUpload() {
        return poUpload;
    }

    public void setPoUpload(String poUpload) {
        this.poUpload = poUpload;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public List<OrderedItem> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<OrderedItem> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public float getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(float amountPaid) {
        this.amountPaid = amountPaid;
    }

    public float getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(float remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", billNumber='" + billNumber + '\'' +
                ", documentNumbering=" + documentNumbering +
                ", poNumber=" + poNumber +
                ", poUpload='" + poUpload + '\'' +
                ", vendor=" + vendor +
                ", orderedItems=" + orderedItems +
                ", totalPrice=" + totalPrice +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", amountPaid=" + amountPaid +
                ", remainingAmount=" + remainingAmount +
                ", paymentType='" + paymentType + '\'' +
                ", bankName='" + bankName + '\'' +
                ", chequeNumber='" + chequeNumber + '\'' +
                '}';
    }
}
