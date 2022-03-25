package com.example.servpro.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "serviceProvider")
public class ServiceProvider {


    @NonNull
    @ColumnInfo(name = "serviceProvider")
    private String serviceProvider;

    @NonNull
    @ColumnInfo(name = "age")
    private int age;

    @NonNull
    @ColumnInfo(name = "occupation")
    private String occupation;

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

    @NonNull
    @ColumnInfo(name = "password")
    private String password;

    @NonNull
    @ColumnInfo(name = "city")
    private String city;

    @NonNull
    @ColumnInfo(name = "chargesPerHours")
    private double chargesPerHours;

    @NonNull
    @ColumnInfo(name = "images")
    private String images;

    @NonNull
    @ColumnInfo(name = "description")
    private String description;

    public ServiceProvider() {
    }

    public ServiceProvider(@NonNull String serviceProvider, int age, @NonNull String occupation, @NonNull String email, @NonNull String phone, @NonNull String address, @NonNull String password, @NonNull String city, double chargesPerHours, @NonNull String images, @NonNull String description) {
        this.serviceProvider = serviceProvider;
        this.age = age;
        this.occupation = occupation;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.city = city;
        this.chargesPerHours = chargesPerHours;
        this.images = images;
        this.description = description;
    }

    @NonNull
    public String getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(@NonNull String serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @NonNull
    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(@NonNull String occupation) {
        this.occupation = occupation;
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

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @NonNull
    public String getCity() {
        return city;
    }

    public void setCity(@NonNull String city) {
        this.city = city;
    }

    public double getChargesPerHours() {
        return chargesPerHours;
    }

    public void setChargesPerHours(double chargesPerHours) {
        this.chargesPerHours = chargesPerHours;
    }

    @NonNull
    public String getImages() {
        return images;
    }

    public void setImages(@NonNull String images) {
        this.images = images;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }
}
