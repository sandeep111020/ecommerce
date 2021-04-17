package com.comapany.shopping.Models;

public class ProductDisplay {
    String imageName, imageDesc, imageURL;


    String imagePrice;

    public ProductDisplay(){

    }

    public ProductDisplay(String imageName, String imageDesc, String imagePrice, String imageURL) {
        this.imageName = imageName;
        this.imageDesc = imageDesc;
        this.imagePrice = imagePrice;
        this.imageURL = imageURL;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageDesc() {
        return imageDesc;
    }

    public void setImageDesc(String imageDesc) {
        this.imageDesc = imageDesc;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImagePrice() {
        return imagePrice;
    }

    public void setImagePrice(String imagePrice) {
        this.imagePrice = imagePrice;
    }



   /* public String getPdes() {
        return imageDesc;
    }

    public void setPdes(String pdes) {
        this.imageDesc = imageDesc;
    }

    public String getPname() {
        return imageName;
    }

    public void setPname(String pname) {
        this.imageName = imageName;
    }

    public String getPprice() {
        return imagePrice;
    }

    public void setPprice(String pprice) {
        this.imagePrice = imagePrice;
    }

    public String getPurl() {
        return imageURL;
    }

    public void setPurl(String purl) {
        this.imageURL = imageURL;
    }*/


}
