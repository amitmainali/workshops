package com.pluralsight;

import java.util.Scanner;

public class MortgageCalculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);


        System.out.print("Enter the loan amount (principal): $");
        double principal = input.nextDouble();

        System.out.print("Enter the annual interest rate (e.g., 7.625 for 7.625%): ");
        double annualRate = input.nextDouble() / 100;

        System.out.print("Enter the loan term in years: ");
        int years = input.nextInt();


        int numberOfPayments = years * 12;
        double monthlyRate = annualRate / 12;

        // Mortgage formula: M = P × [i(1+i)^n] / [(1+i)^n − 1]
        double monthlyPayment = principal * (
                monthlyRate * Math.pow(1 + monthlyRate, numberOfPayments)
                        / (Math.pow(1 + monthlyRate, numberOfPayments) - 1)
        );

        double totalPayment = monthlyPayment * numberOfPayments;
        double totalInterest = totalPayment - principal;


        System.out.printf("\nMonthly Payment: $%.2f\n", monthlyPayment);
        System.out.printf("Total Interest Paid: $%.2f\n", totalInterest);
    }
}