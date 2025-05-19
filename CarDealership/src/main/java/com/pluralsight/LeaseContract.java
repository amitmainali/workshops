package com.pluralsight;

public class LeaseContract extends Contract {
    public LeaseContract(String date, String name, String email, Vehicle vehicle) {
        super(date, name, email, vehicle);
    }

    @Override
    public double getTotalPrice() {
        double leaseFee = getVehicle().getPrice() * 0.07;
        double expectedEndingValue = getVehicle().getPrice() * 0.5;
        return leaseFee + expectedEndingValue;
    }

    @Override
    public double getMonthlyPayment() {
        double rate = 0.04;
        int months = 36;
        double principal = getTotalPrice();
        return (principal * rate / 12) / (1 - Math.pow(1 + rate / 12, -months));
    }
}