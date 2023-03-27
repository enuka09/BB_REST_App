package com.example.bb_rest_app;

import java.sql.Date;

public class Customer {

    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String nic;
    private Date dob;

    public Customer(int id, String firstName, String lastName, String username, String password, String nic, Date dob) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.nic = nic;
        this.dob = dob;
    }

    public Customer(int id, String firstName, String lastName, Date dob) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNIC() {
        return nic;
    }

    public void setNIC(String nic) {
        this.nic = nic;
    }

    public Date getDOB() {
        return dob;
    }

    public void setDOB(Date dob) {
        this.dob = dob;
    }

}

