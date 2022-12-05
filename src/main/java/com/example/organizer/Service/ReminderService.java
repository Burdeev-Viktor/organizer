package com.example.organizer.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

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
    public static String getCurrentTime(){
        Calendar c = Calendar.getInstance();
        int hours = c.get(Calendar.HOUR_OF_DAY);
        int minutes = c.get(Calendar.MINUTE);
        String result = hours+":"+minutes;
        return result;
    }
    public static long getMilSeconds(String dateStart){
        SimpleDateFormat format = new SimpleDateFormat("hh:mm");
        String dateStop = ReminderService.getCurrentTime();
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assert d1 != null;
        assert d2 != null;
        return d1.getTime()-d2.getTime();
    }
    public static String getTodaysDayOfWeek() {
        final Calendar c = Calendar.getInstance();
        String result = null;
        int i = c.get(Calendar.DAY_OF_WEEK);
        switch (i) {
            case 1 -> result = "Воскресенье";
            case 2 -> result = "Понедельник";
            case 3 -> result = "Вторник";
            case 4 -> result = "Среда";
            case 5 -> result = "Четверг";
            case 6 ->result = "Пятница";
            case 7 ->result = "Суббота";
        }
        return result;
    }
}
