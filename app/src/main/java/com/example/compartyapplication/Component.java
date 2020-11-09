package com.example.compartyapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "component_table")
public class Component
{
    @PrimaryKey(autoGenerate = true)
    protected int id;
    protected String name;
    protected int image;
    protected String description;
    protected String manufacturer;
    protected String link;
    protected double price;
    protected String productType;

    public Component(String name, int image, String description, String manufacturer, String link, double price, String productType)
    {
        this.name = name;
        this.image = image;
        this.description = description;
        this.manufacturer = manufacturer;
        this.link = link;
        this.price = price;
        this.productType = productType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getLink() {
        return link;
    }

    public double getPrice() {
        return price;
    }

    public String getProductType() {
        return productType;
    }
}
