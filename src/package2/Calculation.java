package package2;

public class Calculation {
    
    //Get the last two digits
    //Ex. 2006 -> 06, 1991 -> 91
    public static int yearLastNums (int year) {
        int yr = year % 100;
        return yr;
    }

    public static int yearLastDivide (int yearLastNums) {
        int num = yearLastNums / 4;
        return num;
    }

    //Check for tricky leap years especially when handling January and February dates
    public static boolean isLeapYear (int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    //Get the century digits
    //Ex. 2008 -> 20, 1990 -> 19
    public static int centValue (int year) {
        int num = 0;
        int century = year / 100;
        int rem = century % 4;

        if (rem == 1) {
            num = 4;
        }
        else if (rem == 2) {
            num = 2;
        }
        else if (rem == 3) {
            num = 0;
        }
        else {
            num = 6;
        }

        return num;
    }

    public static int monthValue (int month) {
        int num = 0;
        if (month == 1 || month == 10) {
            num = 0;
        }
        else if (month == 2 || month == 3 || month == 11) {
            num = 3;
        }
        else if (month == 4 || month == 7) {
            num = 6;
        }
        else if (month == 5) {
            num = 1;
        }
        else if (month == 6) {
            num = 4;
        }
        else if (month == 8) {
            num = 2;
        }
        else if (month == 9 || month == 12) {
            num = 5;
        }
        return num;
    }

    public static int finalCombo (int month, int date, int year) {   
        
        int mv = monthValue(month);
        int cv = centValue(year);
        int ylast = yearLastNums(year);
        int yld = yearLastDivide(ylast);
        
        int sum = 0;
        sum += mv;
        sum += cv;
        sum += ylast;
        sum += yld;
        sum += date;
        
        //For calculating tricky January and February dates on leap years
        if (isLeapYear(year) && (month == 1 || month == 2)) {
            sum--;
        }

        int rem = sum % 7;
        return rem;
    }

    public static String dayOfTheWeek (int rem) {
        String day = " ";
        if (rem == 1) {
            day = "MONDAY";
        }
        else if (rem == 2) {
            day = "TUESDAY";
        }
        else if (rem == 3) {
            day = "WEDNESDAY";
        }
        else if (rem == 4) {
            day = "THURSDAY";
        }
        else if (rem == 5) {
            day = "FRIDAY";
        }
        else if (rem == 6) {
            day = "SATURDAY";
        }
        else {
            day = "SUNDAY";
        }
        return day;
    }

}
