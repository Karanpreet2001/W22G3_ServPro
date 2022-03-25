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

    @NonNull
    @ColumnInfo(name = "age")
    private int age;

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "email")
    private String email;

    @NonNull
    @ColumnInfo(name = "phone")
    private String phone;

    @NonNull
    @ColumnInfo(name = "address")
    private String address;

    public Customer(){

    }

    public Customer(@NonNull String customerName, int age, @NonNull String email, @NonNull String phone, @NonNull String address) {
        this.customerName = customerName;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.address = address;
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

    @NonNull
    public String getPhone() {
        return phone;
    }

    public void setPhone(@NonNull String phone) {
        this.phone = phone;
    }

    @NonNull
    public String getAddress() {
        return address;
    }

    public void setAddress(@NonNull String address) {
        this.address = address;
    }
}
