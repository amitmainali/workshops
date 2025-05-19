package com.pluralsight;

public class SalesContract extends Contract {
    private boolean isFinanced;

    public SalesContract(String date, String name, String email, Vehicle vehicle, boolean isFinanced) {
        super(date, name, email, vehicle);
        this.isFinanced = isFinanced;
    }

    public boolean isFinanced() {
        return isFinanced;
    }

    @Override
    public double getTotalPrice() {
        double price = getVehicle().getPrice();
        double salesTax = price * 0.05;
        double recordingFee = 100.0;
        double processingFee;

        if (price < 10000) {
            processingFee = 295.0;
        } else {
            processingFee = 495.0;
        }

        return price + salesTax + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (!isFinanced) {
            return 0.0;
        }

        double principal = getTotalPrice();
        double rate;
        int termMonths;

        if (getVehicle().getPrice() >= 10000) {
            rate = 0.0425;
            termMonths = 48;
        } else {
            rate = 0.0525;
            termMonths = 24;
        }

        double monthlyPayment = (principal * rate / 12) / (1 - Math.pow(1 + rate / 12, -termMonths));
        return monthlyPayment;
    }
}