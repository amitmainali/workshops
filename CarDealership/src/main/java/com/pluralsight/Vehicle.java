package com.pluralsight;

public class Vehicle {
    private int vin, year, odometer;
    private String make, model, vehicleType, color;
    private double price;

    public Vehicle(int vin, int year, String make, String model,
                   String vehicleType, String color, int odometer, double price){
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.vehicleType = vehicleType;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
    }

    public int getVin() {
        return vin;
    }

    public int getYear() {
        return year;
    }

    public int getOdometer() {
        return odometer;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return vin + " | " + year + " | " + make + " | " + model + " | " + vehicleType
                + " | " + color + " | " + odometer + " | $" + price;
    }
}
