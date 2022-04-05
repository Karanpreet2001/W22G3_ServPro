package com.example.servpro.models;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "customer")
public class Customer {

    @NonNull
    @ColumnInfo(name = "customerName")
    private String customerName;


    @ColumnInfo(name = "age")
    private int age;


    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "email")
    private String email;


    @ColumnInfo(name = "phone")
    private String phone;


    @ColumnInfo(name = "address")
    private String address;


    @ColumnInfo(name = "password")
    private String password;

    public Customer() {
    }

    public Customer(@NonNull String customerName, int age, @NonNull String email, String phone, String address, String password) {
        this.customerName = customerName;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
    }

    @NonNull
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(@NonNull String customerName) {
        this.customerName = customerName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
