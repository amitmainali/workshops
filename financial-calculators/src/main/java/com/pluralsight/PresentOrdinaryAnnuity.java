package com.pluralsight;

import java.util.Scanner;

public class PresentOrdinaryAnnuity {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);


        System.out.print("Enter the monthly payout: $");
        double monthlyPayout = input.nextDouble();

        System.out.print("Enter the annual interest rate (e.g., 2.5 for 2.5%): ");
        double annualRate = input.nextDouble() / 100;

        System.out.print("Enter the number of years: ");
        int years = input.nextInt();


        int months = years * 12;
        double monthlyRate = annualRate / 12;

        // PV = Pmt × [1 - (1 + r)^-n] / r
        double presentValue = monthlyPayout *
                (1 - Math.pow(1 + monthlyRate, -months)) / monthlyRate;


        System.out.printf("\nPresent Value of the Annuity: $%.2f\n", presentValue);
    }
}