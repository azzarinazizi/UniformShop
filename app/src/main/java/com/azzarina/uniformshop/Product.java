package com.azzarina.uniformshop;

public class Product {
    private String title;
    private String price;
    private String size;
    private String coverImage;
    private String description;
    private String email;
    private String phone;

    public Product (){}
    public Product (String title, String price, String size, String coverImage){
        this.title = title;
        this.price = price;
        this.size = size;
        this.coverImage = coverImage;
        this.description = description;
        this.email = email;
        this.phone = phone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
