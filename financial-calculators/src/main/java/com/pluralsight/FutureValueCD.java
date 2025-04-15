package com.pluralsight;

import java.util.Scanner;

public class FutureValueCD {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);


        System.out.print("Enter the deposit amount (principal): $");
        double principal = input.nextDouble();

        System.out.print("Enter the annual interest rate (e.g., 1.75 for 1.75%): ");
        double annualRate = input.nextDouble() / 100;

        System.out.print("Enter the number of years: ");
        int years = input.nextInt();


        int daysPerYear = 365;
        int totalDays = daysPerYear * years;


        double futureValue = principal * Math.pow(1 + (annualRate / daysPerYear), totalDays);
        double totalInterest = futureValue - principal;


        System.out.printf("\nFuture Value: $%.2f\n", futureValue);
        System.out.printf("Total Interest Earned: $%.2f\n", totalInterest);
    }
}