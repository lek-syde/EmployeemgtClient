package com.vaadin.tutorial.crm.backend.entity;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.Gson;
import com.vaadin.tutorial.crm.backend.util.DateHandler;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Temporal;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;


public class Employee {


    private Long id;

    private String name;

    private String position;

    private String email;


    private String phone;





    @JsonFormat(pattern="dd-MM-yyyy")
    @JsonDeserialize(using = DateHandler.class)
    private Date dateHired;

    @JsonFormat(pattern="dd-MM-yyyy")
    @JsonDeserialize(using = DateHandler.class)
    private Date dateOfBirth;


    private String sex;
    private String address;
    private String city;
    private String state;
    private boolean isarchived;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public Date getDateHired() {
        return dateHired;
    }

    public void setDateHired(Date dateHired) {
        this.dateHired = dateHired;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", dateHired=" + dateHired +
                ", dateOfBirth=" + dateOfBirth +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", isarchived=" + isarchived +
                '}';
    }

    public Long getId() {
        return id;
    }



    public boolean isIsarchived() {
        return isarchived;
    }

    public void setIsarchived(boolean isarchived) {
        this.isarchived = isarchived;
    }


}


