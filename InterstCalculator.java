/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.interstcalculator;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author TMacRae2026
 */
public class InterstCalculator {

    public static void main(String[] args) {
        
        //instance of InterestRateCalculator class
        InterestRateCalculator ir = new InterestRateCalculator();
        //get inital loan amount
        System.out.print("Input inital ammount: ");
        double ammount = getInputDouble();
        //Get # of months and start calculation
        System.out.print("Input the number of months compounded: ");
        ArrayList<String> results = ir.calculateMonthlyPayments(ammount, getInputInt());
        for(String s : results) {
            System.out.println(s);
        }
        
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
    private static final double RATE = 0.035;
    
    public ArrayList<String> calculateMonthlyPayments(double amount, int months) {
        double remainingAmount = amount;
        double totalPaid = 0;
        DecimalFormat df = new DecimalFormat("$#,##0.00");

        System.out.println("\nMonthly Payment Schedule:");
        
        // Calculate payments for each month
        ArrayList results = new ArrayList<String>();
        for (int month = 1; month <= months; month++) {
            double monthlyPayment = remainingAmount / (months - month + 1);
            double interestPayment = remainingAmount * RATE / 12; //interest for current payment
            
            double payment = monthlyPayment + interestPayment;
            totalPaid += payment;
            remainingAmount -= monthlyPayment; //reduce the principal by the monthly payment
            
            //System.out.println(String.format("Month %d: %s", month, df.format(payment)));
            results.add(String.format("Month %d: %s", month, df.format(payment)));
        }
        
        //output overall results.
        /*System.out.println("\nTotal amount paid: " + df.format(totalPaid));
        System.out.println("Original amount borrowed: " + df.format(amount));
        System.out.println("Total interest paid: " + df.format(totalPaid - amount));*/
        results.add("\nTotal amount paid: " + df.format(totalPaid));
        results.add("Original amount borrowed: " + df.format(amount));
        results.add("Total interest paid: " + df.format(totalPaid - amount));
        return results;
    }
}
