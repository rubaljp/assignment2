package com.example.carrental.GetterSetter;

import android.net.Uri;

public class Vehicle
{
    private String uniqueID;
    private String Type, Brand, Model, Year, Color, LicensePlate;
    Uri ImageUri;

    public String getType() { return Type; }
    public void setType(String type) { Type = type; }

    public String getBrand() { return Brand; }
    public void setBrand(String brand) { Brand = brand; }

    public String getModel() { return Model; }
    public void setModel(String model) { Model = model; }

    public String getYear() { return Year; }
    public void setYear(String year) { Year = year; }

    public String getColor() { return Color; }
    public void setColor(String color) { Color = color; }

    public String getLicensePlate() { return LicensePlate; }
    public void setLicensePlate(String licensePlate) { LicensePlate = licensePlate; }

    public Uri getImageUri() { return ImageUri; }
    public void setImageUri(Uri image) { ImageUri = image; }

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }
}