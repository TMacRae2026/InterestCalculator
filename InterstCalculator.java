/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.interstcalculator;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *
 * @author TMacRae2026
 */
public class InterstCalculator {

    public static void main(String[] args) {
        
        //instance of InterestRateCalculator class
        InterestRateCalculator ir = new InterestRateCalculator();
        //get ir rate
        System.out.print("Input interest rate: ");
        ir.setRate(getInputDouble());
        //get inital loan amount
        System.out.print("Input inital ammount: ");
        double ammount = getInputDouble();
        //Get # of months and start calculation
        System.out.print("Input the number of months compounded: ");
        ir.calculateMonthlyPayments(ammount, getInputInt());
        
    }
    
    //prevents errors in user input
    static double getInputDouble() {
        Scanner scan = new Scanner(System.in);
        double result = 0;
        while(true) {
            try {
                result = scan.nextDouble();
                return result;
            } catch(Exception e) { //catch when user inputs something thats not a double
                System.out.println("Please enter a valid double");
                scan.next();
            }
            
        }
    }
    static int getInputInt() {
        Scanner scan = new Scanner(System.in);
        int result = 0;
        while(true) {
            try {
                result = scan.nextInt();
                return result;
            } catch(Exception e) { //catch when user inputs something thats not a int
                System.out.println("Please enter a valid intiger");
                scan.next();
            }
        }
    }
}

class InterestRateCalculator {
    private double Rate;
    
    public void setRate(double percent) {
        Rate = percent/100;
    }
    
    public void calculateMonthlyPayments(double amount, int months) {
        double remainingAmount = amount;
        double totalPaid = 0;
        DecimalFormat df = new DecimalFormat("$#,##0.00");

        System.out.println("\nMonthly Payment Schedule:");
        
        // Calculate payments for each month
        for (int month = 1; month <= months; month++) {
            double monthlyPayment = remainingAmount / (months - month + 1);
            double interestPayment = remainingAmount * Rate / 12; //interest for current payment
            
            double payment = monthlyPayment + interestPayment;
            totalPaid += payment;
            remainingAmount -= monthlyPayment; //reduce the principal by the monthly payment
            
            System.out.println(String.format("Month %d: %s", month, df.format(payment)));
        }

        //output overall results.
        System.out.println("\nTotal amount paid: " + df.format(totalPaid));
        System.out.println("Original amount borrowed: " + df.format(amount));
        System.out.println("Total interest paid: " + df.format(totalPaid - amount));
    }
}