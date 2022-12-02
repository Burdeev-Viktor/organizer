package com.example.organizer.Service;

import com.example.organizer.Repositories.LessonRepo;
import com.example.organizer.SciencesController;
import com.example.organizer.model.Lesson;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class LessonService {
    public static ArrayList<Lesson> getLessonsThisWeek(int week) {
        LocalDateTime localeDate = LocalDateTime.now();
        localeDate = localeDate.plusWeeks(week);
        LocalDateTime firstSeptember = LocalDateTime.of(LocalDateTime.now().getYear(), 9, 1, 1, 1);
        ArrayList<Lesson> lessonsList;
        if (ChronoUnit.WEEKS.between(firstSeptember, localeDate) % 2 == 0) {
            lessonsList = LessonRepo.getLessonsByUserByWeek(SciencesController.getUser().getId(), 0);
        } else {
            lessonsList = LessonRepo.getLessonsByUserByWeek(SciencesController.getUser().getId(), 1);
        }
        System.out.println(ChronoUnit.WEEKS.between(firstSeptember, localeDate));
        return lessonsList;
    }


}
