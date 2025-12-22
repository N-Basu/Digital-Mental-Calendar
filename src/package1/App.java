package package1;
import package2.Calculation;
import package2.DateCheck;

import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        try (Scanner scnr = new Scanner(System.in)) {

            System.out.println("Welcome to the Calendar Calculator!\n"); 
            System.out.println("In this calculator, you will pinpoint the day of the week of a standard Gregorian calendar date!\n");
            
            while (true) {
                System.out.print("Please enter a date from 1582 to present (MM/DD/YYYY) or enter Q to quit: ");
                String userDate = scnr.nextLine();

                if (userDate.equalsIgnoreCase("q")) {
                    System.out.println("Exiting...");
                    break;
                }

                String[] dateSplit = userDate.split("/");

                try {

                    for (String part : dateSplit) {
                        Integer.parseInt(part);
                    }

                    if (dateSplit.length != 3) {
                        System.out.println("Invalid date! Please use the MM/DD/YYYY format!");
                        continue;
                    }

                    int month = Integer.parseInt(dateSplit[0]);
                    int day = Integer.parseInt(dateSplit[1]);
                    int year = Integer.parseInt(dateSplit[2]);

                    if (!DateCheck.isValidDate(month, day, year)) {
                        System.out.println("Invalid date! Please try again!\n");
                        continue;
                    }

                    if (!DateCheck.isValidYear(year)) {
                        System.out.println("Invalid year! Year must be 1582 to the present!\n");
                        continue;
                    }

                    if (!DateCheck.notFuture(month, day, year)) {
                        System.out.println("Invalid date! Date cannot be in the future!\n");
                        continue;
                    }

                    System.out.println("Calculating day of the week ...\n");

                    int finalNum = Calculation.finalCombo(month, day, year);
                    String dotw = Calculation.dayOfTheWeek(finalNum);

                    if (DateCheck.isPresent(month, day, year)) {
                        System.out.println(userDate + " is a " + dotw + "!\n");
                        continue;
                    }

                    System.out.println(userDate + " was a " + dotw + "!\n");
                }

                catch (NumberFormatException e) {
                    System.out.println("Invalid input! Make sure the month, day, and year are all numbers");
                }

            }
        }
    }    
}