package com.comapany.shopping.Models;

public class Orders {
    private String name;
    private String number,address,city,state,pin;

    public  Orders()
    {

    }

    public Orders(String name, String number, String address,String city, String state, String pin) {
        this.name = name;
        this.number = number;
        this.address = address;
        this.city = city;
        this.state = state;
        this.pin = pin;


    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }








    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
