package com.example.organizer.Service;

import java.time.LocalDate;

public class ReminderService {
    public static LocalDate getLocalDateByString(String date){
        String year = date.substring(0,4);
        String mouth = date.substring(5,7);
        String day = date.substring(8);
        LocalDate localDate = LocalDate.of(Integer.parseInt(year),Integer.parseInt(mouth),Integer.parseInt(day));
        return localDate;
    }
    public static String getHours(String time){
        return time.substring(0,2);
    }
    public static String getMinutes(String time){
        return time.substring(3);
    }
}
