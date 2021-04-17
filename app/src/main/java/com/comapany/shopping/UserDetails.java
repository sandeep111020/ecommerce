package com.comapany.shopping;


public class UserDetails {
    private String name;
    private String number;
    private String address;
    private String pincode;
    private String city,state;





    public  UserDetails()
    {

    }

    public UserDetails(String name, String number, String address,String pincode,String city,String state) {
        this.name = name;
        this.number = number;
        this.address= address;
        this.pincode = pincode;
        this.city = city;
        this.state = state;


    }


    public String getNameU() {
        return name;
    }

    public void setNameU(String name) {
        this.name = name;
    }

    public String getNumberU() {
        return number;
    }

    public void setNumberU(String number) {
        this.number = number;
    }

    public String getAddressU() {
        return address;
    }

    public void setAddressU(String address) {
        this.address = address;
    }

    public String getPincodeU() {
        return pincode;
    }

    public void setPincodeU(String pincode) {
        this.pincode = pincode;
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









}
