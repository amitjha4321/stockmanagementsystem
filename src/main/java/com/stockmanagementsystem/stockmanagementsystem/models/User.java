package com.stockmanagementsystem.stockmanagementsystem.models;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    private String fname;
    private String mname;
    private String lname;
    @Column(unique=true)
    private String email;
    private String password;
    private String phone;
    private String filename;
    private boolean isEnabled;
    @Lob
    private byte [] dataimage;

    //private String base64EncodedImage;

//, cascade = CascadeType.ALL
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public User() {
    }

    public User(String fname, String mname, String lname, String email,
                String password, String phone, String filename, byte[] dataimage,
                List<Role> roles, boolean isEnabled) {
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.filename = filename;
        this.dataimage = dataimage;
        this.roles = roles;
        this.isEnabled=isEnabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getDataimage() {
        return dataimage;
    }

    public void setDataimage(byte[] dataimage) {
        this.dataimage = dataimage;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", mname='" + mname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", filename='" + filename + '\'' +
               // ", dataimage=" + Arrays.toString(dataimage) +
                ", roles=" + roles +
                '}';
    }
}
