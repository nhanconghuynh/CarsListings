package com.nhanhuynh.challenge;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private boolean recentadd;

    private boolean recentremove;

    private boolean recentmodified;

    private String categorytype;

    @Size(min=3)
    private String manufacturer;

    @Size(min=2)
    private String carmodel;

    @Size(min=1)
    private String cartrim;

    @Min(1980)
    @Max(2019)
    private long year;

    @Size(min=2)
    private String color;

    @Min(0)
    private double price;

    @Min(0)
    private int mileage;

    @Size(min=3)
    private String description;

    @Size(min=3)
    private String features;


    private String carphoto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    public Car() {
        this.recentadd = false;
        this.recentmodified = false;
        this.recentremove = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCarmodel() {
        return carmodel;
    }

    public void setCarmodel(String carmodel) {
        this.carmodel = carmodel;
    }

    public String getCartrim() {
        return cartrim;
    }

    public void setCartrim(String cartrim) {
        this.cartrim = cartrim;
    }

    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFeatures() { return features; }

    public void setFeatures(String features) { this.features = features; }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getCarphoto() {
        return carphoto;
    }

    public void setCarphoto(String carphoto) {
        this.carphoto = carphoto;
    }

    public boolean isRecentadd() {
        return recentadd;
    }

    public void setRecentadd(boolean recentadd) {
        this.recentadd = recentadd;
    }

    public boolean isRecentremove() {
        return recentremove;
    }

    public void setRecentremove(boolean recentremove) {
        this.recentremove = recentremove;
    }

    public boolean isRecentmodified() {
        return recentmodified;
    }

    public void setRecentmodified(boolean recentmodified) {
        this.recentmodified = recentmodified;
    }

    public String getCategorytype() {
        return categorytype;
    }

    public void setCategorytype(String categorytype) {
        this.categorytype = categorytype;
    }
}

