package com.example.organizer.SecondTherd;

<<<<<<< HEAD
import com.example.organizer.Const;
import com.example.organizer.Controller.SciencesController;
import com.example.organizer.Repositories.ReminderRepo;
=======
import com.example.organizer.Repositories.ReminderRepo;
import com.example.organizer.SciencesController;
>>>>>>> origin/master
import com.example.organizer.Service.ReminderService;
import com.example.organizer.model.Reminder;

import java.util.ArrayList;
import java.util.Objects;

<<<<<<< HEAD
public class CheckingClass extends Thread {
=======
public class CheckingClass extends Thread{
>>>>>>> origin/master
    private static CheckingClass checkingClass;
    private volatile boolean running;


<<<<<<< HEAD
    private CheckingClass() {
        running = true;
    }

    public static void running() {
        checkingClass.start();
    }


    public static CheckingClass getCheckingClass() {
        if (checkingClass == null) {
            checkingClass = new CheckingClass();
        }
        return checkingClass;
    }

    public void run() {
        running = true;
        while (running) {
            long timeSleep = Const.HOUR_IN_MILLISECONDS;
            ArrayList<Reminder> reminderArrayList = ReminderRepo.getRemindersEnable(SciencesController.getUser());
            String today = ReminderService.getTodayDayOfWeek();
            for (int i = 0; i < reminderArrayList.size(); i++) {
                if (!(Objects.equals(reminderArrayList.get(i).getDatOfWeek(), today) || Objects.equals(reminderArrayList.get(i).getSettingSwitch(), "Каждый день"))) {
=======

    public void run(){
        running = true;
        while (running){
            long timeSleep = 3600000;
            ArrayList<Reminder> reminderArrayList = ReminderRepo.getRemindersEnable(SciencesController.getUser());
            String today = ReminderService.getTodaysDayOfWeek();
            for (int i = 0; i < reminderArrayList.size(); i++){
                if(!(Objects.equals(reminderArrayList.get(i).getDatOfWeek(), today) || Objects.equals(reminderArrayList.get(i).getSettingSwitch(), "Каждый день"))){
>>>>>>> origin/master
                    reminderArrayList.remove(i);
                    i--;
                }
            }
            for (Reminder reminder : reminderArrayList) {
                long k = ReminderService.getMilSeconds(reminder.getTime());
                if (k < 0) {
                    continue;
                }
<<<<<<< HEAD
                if (k <= Const.MINUTE_IN_MILLISECONDS) {
=======
                if (k < 60000) {
>>>>>>> origin/master
                    SystemTrayClass.sendMassage(reminder.getLessonName(), reminder.getQuest());
                    continue;
                }
                if (k < timeSleep) {
<<<<<<< HEAD
                    timeSleep = ReminderService.getMilSeconds(reminder.getTime()) - Const.MINUTE_IN_MILLISECONDS / 2;
=======
                    timeSleep = ReminderService.getMilSeconds(reminder.getTime());
>>>>>>> origin/master
                }
            }
            try {
                Thread.sleep(timeSleep);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
<<<<<<< HEAD
=======
    public static void running(){
        checkingClass.start();
    }


    public static CheckingClass getCheckingClass() {
        if (checkingClass == null){
            checkingClass = new CheckingClass();
        }
        return checkingClass;
    }
    private CheckingClass(){
        running = true;
    }
>>>>>>> origin/master

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
