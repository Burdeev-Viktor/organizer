package com.example.organizer.SecondTherd;

import com.example.organizer.Repositories.ReminderRepo;
import com.example.organizer.SciencesController;
import com.example.organizer.Service.ReminderService;
import com.example.organizer.model.Reminder;

import java.util.ArrayList;
import java.util.Objects;

public class CheckingClass extends Thread{
    private static CheckingClass checkingClass;
    private volatile boolean running;



    public void run(){
        running = true;
        while (running){
            long timeSleep = 3600000;
            ArrayList<Reminder> reminderArrayList = ReminderRepo.getRemindersEnable(SciencesController.getUser());
            String today = ReminderService.getTodaysDayOfWeek();
            for (int i = 0; i < reminderArrayList.size(); i++){
                if(!(Objects.equals(reminderArrayList.get(i).getDatOfWeek(), today) || Objects.equals(reminderArrayList.get(i).getSettingSwitch(), "Каждый день"))){
                    reminderArrayList.remove(i);
                    i--;
                }
            }
            for (Reminder reminder : reminderArrayList) {
                long k = ReminderService.getMilSeconds(reminder.getTime());
                if (k < 0) {
                    continue;
                }
                if (k < 60000) {
                    SystemTrayClass.sendMassage(reminder.getLessonName(), reminder.getQuest());
                    continue;
                }
                if (k < timeSleep) {
                    timeSleep = ReminderService.getMilSeconds(reminder.getTime());
                }
            }
            try {
                Thread.sleep(timeSleep);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
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

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
