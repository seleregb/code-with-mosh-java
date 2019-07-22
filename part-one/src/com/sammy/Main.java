package com.sammy;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    // constants
    private final static byte MONTHS_IN_YEAR = 12;
    private final static byte PERCENT = 100;

    public static void main(String[] args) {

        final int PRINCIPAL_MIN = 1000;
        final int PRINCIPAL_MAX = 1_000_000;

        int principal = (int) readNumber("Principal: ", PRINCIPAL_MIN, PRINCIPAL_MAX);

        float annualInterest = (float) readNumber("Annual Interest Rate: ", 1, 30);

        int years = (int) readNumber("Period (Years): ", 1, 30);

        printMortgage(principal, annualInterest, years);

        printPaymentSchedule(principal, annualInterest, years);
    }

    private static double readNumber(String prompt, double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextFloat();
            if(value >= min && value <= max) {
                break;
            }
            System.out.println("Enter a value between " + min + " and " + max);
        }
        return value;
    }

    private static double calculateMortgage(int principal, float annualInterest, int years) {

        float monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;

        double totalNumberOfPayments = years * MONTHS_IN_YEAR;

        // calculate the monthly interest per month
        double paymentsPerMonth = Math.pow(1 + monthlyInterest, totalNumberOfPayments);
        // calculate the mortgage value
        double mortgage =  principal * (monthlyInterest * paymentsPerMonth) / (paymentsPerMonth - 1);

        return mortgage;
    }

    private static void printMortgage(int principal, float annualInterest, int years) {
        // calculate the mortgage value
        double mortgage = calculateMortgage(principal, annualInterest, years);

        System.out.println("\nMORTGAGE \n----------");
        System.out.println("Monthly Payments: " + NumberFormat.getCurrencyInstance().format(mortgage));
    }

    private static double calculateBalance(int principal, float annualInterest, int years, double numberOfPaymentsMade) {

        float monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;

        double totalNumberOfPayments = years * MONTHS_IN_YEAR;

        double balance = principal
                * ( Math.pow(1 + monthlyInterest, totalNumberOfPayments)
                - Math.pow(1 + monthlyInterest, numberOfPaymentsMade))
                / (Math.pow(1+ monthlyInterest, totalNumberOfPayments) - 1);

        return balance;
    }

    private static void printPaymentSchedule(int principal, float annualInterest, int years) {
        System.out.println("\nPAYMENT SCHEDULE \n------------");
        for (int month = 1; month <= years * MONTHS_IN_YEAR; month++) {
            double remainingBalance = calculateBalance(principal, annualInterest, years, month);

            System.out.println(NumberFormat.getCurrencyInstance().format(remainingBalance));
        }
    }

}


