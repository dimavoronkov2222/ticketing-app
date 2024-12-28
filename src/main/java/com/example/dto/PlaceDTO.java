package com.example.dto;
public class PlaceDTO {
    private String address;
    private String name;
    public PlaceDTO(String name, String address) {
        this.name = name;
        this.address = address;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}