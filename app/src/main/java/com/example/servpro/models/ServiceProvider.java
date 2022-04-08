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
    private String age;

    @NonNull
    @ColumnInfo(name = "occupation")
    private String occupation;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "email")
    private String email;


    @ColumnInfo(name = "phone")
    private String phone;

    @NonNull
    @ColumnInfo(name = "street")
    private String street;

    @ColumnInfo(name = "city")
    private String city;


    @ColumnInfo(name = "wage")
    private String wage;

    @NonNull
    @ColumnInfo(name = "password")
    private String password;

//    @NonNull
//    @ColumnInfo(name= "image")
//    private Blob image;


    @ColumnInfo(name = "description")
    private String description;



    public ServiceProvider() {
    }


    public ServiceProvider(@NonNull String serviceProvider, @NonNull String age, @NonNull String occupation, String email, String phone, @NonNull String street, String city, String wage, @NonNull String password, String description) {
        this.serviceProvider = serviceProvider;
        this.age = age;
        this.occupation = occupation;
        this.email = email;
        this.phone = phone;
        this.street = street;
        this.city = city;
        this.wage = wage;
        this.password = password;
        this.description = description;
    }

    @NonNull
    public String getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(@NonNull String serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    @NonNull
    public String getAge() {
        return age;
    }

    public void setAge(@NonNull String age) {
        this.age = age;
    }

    @NonNull
    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(@NonNull String occupation) {
        this.occupation = occupation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @NonNull
    public String getStreet() {
        return street;
    }

    public void setStreet(@NonNull String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWage() {
        return wage;
    }

    public void setWage(String wage) {
        this.wage = wage;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
