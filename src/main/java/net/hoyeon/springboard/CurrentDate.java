package net.hoyeon.springboard;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CurrentDate {
    public static String curDate(){
        LocalDate currentDate = LocalDate.now();
        String currentDateString = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return currentDateString;
    }
}
