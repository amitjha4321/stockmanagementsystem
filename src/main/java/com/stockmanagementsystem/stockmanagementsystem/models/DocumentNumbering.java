package com.stockmanagementsystem.stockmanagementsystem.models;

import javax.persistence.*;

@Entity
public class DocumentNumbering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "prefixNo")
    private int prefixNo;

    @Column(name ="startingPrefixNo")
    private int startingPrefixNo;

    @Column(name ="prefixWord")
    private String prefixWord;

    @Column(name ="suffixNo")
    private int suffixNo;

    @Column(name ="startingSuffixNo")
    private int startingSuffixNo;

    @Column(name ="suffixWord")
    private String suffixWord;

    @Column(name ="displayNo")
    private String displayNo;

    public DocumentNumbering() {
    }

    public DocumentNumbering(String name, int prefixNo, int startingPrefixNo, String prefixWord, int suffixNo, int startingSuffixNo, String suffixWord, String displayNo) {
        this.name = name;
        this.prefixNo = prefixNo;
        this.startingPrefixNo = startingPrefixNo;
        this.prefixWord = prefixWord;
        this.suffixNo = suffixNo;
        this.startingSuffixNo = startingSuffixNo;
        this.suffixWord = suffixWord;
        this.displayNo = displayNo;
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

    public int getPrefixNo() {
        return prefixNo;
    }

    public void setPrefixNo(int prefixNo) {
        this.prefixNo = prefixNo;
    }

    public int getStartingPrefixNo() {
        return startingPrefixNo;
    }

    public void setStartingPrefixNo(int startingPrefixNo) {
        this.startingPrefixNo = startingPrefixNo;
    }

    public String getPrefixWord() {
        return prefixWord;
    }

    public void setPrefixWord(String prefixWord) {
        this.prefixWord = prefixWord;
    }

    public int getSuffixNo() {
        return suffixNo;
    }

    public void setSuffixNo(int suffixNo) {
        this.suffixNo = suffixNo;
    }

    public int getStartingSuffixNo() {
        return startingSuffixNo;
    }

    public void setStartingSuffixNo(int startingSuffixNo) {
        this.startingSuffixNo = startingSuffixNo;
    }

    public String getSuffixWord() {
        return suffixWord;
    }

    public void setSuffixWord(String suffixWord) {
        this.suffixWord = suffixWord;
    }

    public String getDisplayNo() {
        return displayNo;
    }

    public void setDisplayNo(String displayNo) {
        this.displayNo = displayNo;
    }

    @Override
    public String toString() {
        return "documentNumbering{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", prefixNo=" + prefixNo +
                ", startingPrefixNo=" + startingPrefixNo +
                ", prefixWord='" + prefixWord + '\'' +
                ", suffixNo=" + suffixNo +
                ", startingSuffixNo=" + startingSuffixNo +
                ", suffixWord='" + suffixWord + '\'' +
                ", displayNo='" + displayNo + '\'' +
                '}';
    }
}
