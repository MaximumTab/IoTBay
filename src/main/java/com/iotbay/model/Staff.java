package com.iotbay.model;

public class Staff {
    private int id;
    private String name;
    private String email;
    private String position;
    private String address;
    private String status;

    public Staff() {
    }

    public Staff(int id, String name, String email, String position, String address, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.position = position;
        this.address = address;
        this.status = status;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
} 