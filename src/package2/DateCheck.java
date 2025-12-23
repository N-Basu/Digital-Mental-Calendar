package package2;

import java.time.DateTimeException;
import java.time.LocalDate;

public class DateCheck {

    //No future dates allowed in the calendar calculator
    public static Boolean notFuture (int month, int day, int year) {
        try {
            LocalDate input = LocalDate.of(year, month, day);
            LocalDate today = LocalDate.now();

            boolean future = input.isAfter(today);

            if (future) {
                return false;
            }
            return true;
        }
        catch (DateTimeException e) {
            return false;
        }
    }

    public static Boolean isPresent (int month, int day, int year) {
        try {
            LocalDate input = LocalDate.of(year, month, day);
            LocalDate today = LocalDate.now();
            return input.isEqual(today);
        }
        
        catch (DateTimeException e) {
            return false;
        }
    }

    //No earlier than 1582
    //1582 was the year the internationally accepted Gregorian calendar was created
    //This code pinpoints dates in the Gregorian calendar only
    public static boolean isValidYear (int year) {
        return year >= 1582;
    }

    //Make sure the date is accurate, especially with February and the 30-day months!!
    public static boolean isValidDate (int month, int date, int year) {
        if (month < 1 || month > 12) {
            return false;
        }
        if (date < 1) {
            return false;
        }

        int daysInMonth;

        //February conditional (28-29 days)
        if (month == 2) {
            //leap year
            if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                daysInMonth = 29;
            }
            else {
                daysInMonth = 28;
            }
        }

        //Conditional for April, June, September, and November (30 days)
        else if (month == 4 || month == 6 || month == 9 || month == 11) {
            daysInMonth = 30;
        }

        //All other months
        else {
            daysInMonth = 31;
        }


        if (date > daysInMonth) {
            return false;
        }

        return true;
    }

}
