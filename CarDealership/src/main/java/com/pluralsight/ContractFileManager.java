package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {

    public void saveContract(Contract contract) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("contracts.csv", true))) {
            Vehicle v = contract.getVehicle();

            String contractType;
            if (contract instanceof SalesContract) {
                contractType = "SALE";
            } else if (contract instanceof LeaseContract) {
                contractType = "LEASE";
            } else {
                contractType = "UNKNOWN";
            }

            String base = contractType + "|" +
                    contract.getDate() + "|" +
                    contract.getCustomerName() + "|" +
                    contract.getCustomerEmail() + "|" +
                    v.getVin() + "|" +
                    v.getYear() + "|" +
                    v.getMake() + "|" +
                    v.getModel() + "|" +
                    v.getVehicleType() + "|" +
                    v.getColor() + "|" +
                    v.getOdometer() + "|" +
                    v.getPrice();

            String extra = "";

            if (contract instanceof SalesContract) {
                SalesContract sc = (SalesContract) contract;
                double tax = v.getPrice() * 0.05;
                double recordingFee = 100.0;
                double processingFee = v.getPrice() < 10000 ? 295 : 495;
                double totalPrice = sc.getTotalPrice();
                double monthlyPayment = sc.getMonthlyPayment();

                String financeStatus;
                if (sc.isFinanced()) {
                    financeStatus = "YES";
                } else {
                    financeStatus = "NO";
                }

                extra = "|" + String.format("%.2f", tax) +
                        "|" + String.format("%.2f", recordingFee) +
                        "|" + String.format("%.2f", processingFee) +
                        "|" + String.format("%.2f", totalPrice) +
                        "|" + financeStatus +
                        "|" + String.format("%.2f", monthlyPayment);
            } else if (contract instanceof LeaseContract) {
                LeaseContract lc = (LeaseContract) contract;
                double expectedEndingValue = v.getPrice() * 0.5;
                double leaseFee = v.getPrice() * 0.07;
                double totalPrice = lc.getTotalPrice();
                double monthlyPayment = lc.getMonthlyPayment();

                extra = "|" + String.format("%.2f", expectedEndingValue) +
                        "|" + String.format("%.2f", leaseFee) +
                        "|" + String.format("%.2f", totalPrice) +
                        "|" + String.format("%.2f", monthlyPayment);
            }

            writer.write(base + extra);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}