package com.example.organizer;

import com.example.organizer.Repositories.LessonRepo;
import com.example.organizer.model.Lesson;
import com.example.organizer.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;



public class SciencesController {
    static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        SciencesController.user = user;
    }

    public static void toSignUp(ActionEvent event){
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(SciencesController.class.getResource("sign-up.fxml"));
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root,770,400));
        stage.setTitle("Регистрация");
        stage.show();
    }
    public static void toSignIn(ActionEvent event){
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(SciencesController.class.getResource("sign-in.fxml"));
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root,700,400));
        stage.setTitle("Вход");
        stage.show();
    }
    public static void toTimeTableEdit(ActionEvent event, User user){
        if(LessonRepo.timetableIsExistsByUser(user)){
            LessonRepo.createTimetableByUser(user);
        }
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(SciencesController.class.getResource("timetable-edit.fxml"));
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root,1000,700));
        stage.setTitle("Расписание");
        LessonEditController.setEventTimetable(stage);
        stage.show();
    }
    public static void toNewTimeTableEdit( User user){
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(SciencesController.class.getResource("timetable-edit.fxml"));
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(LessonRepo.timetableIsExistsByUser(user)){
            LessonRepo.createTimetableByUser(user);
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root,1000,700));
        stage.setTitle("Расписание");
        LessonEditController.setEventTimetable(stage);
        stage.show();
    }
    public static void updateTimeTableEdit( User user,Stage stage){
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(SciencesController.class.getResource("timetable-edit.fxml"));
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(LessonRepo.timetableIsExistsByUser(user)){
            LessonRepo.createTimetableByUser(user);
        }
        stage.setScene(new Scene(root,1000,700));
        stage.setTitle("Расписание");
        LessonEditController.setEventTimetable(stage);
        stage.show();
    }
    public static void toEditLesson(Lesson lesson){
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(SciencesController.class.getResource("lesson-edit.fxml"));
            root = loader.load();
            LessonEditController lessonEditController = loader.getController();
            lessonEditController.setInfo(lesson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(LessonRepo.timetableIsExistsByUser(user)){
            LessonRepo.createTimetableByUser(user);
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root,600,450));
        stage.setTitle("Изменить");
        stage.show();
    }
    public static void toMain(ActionEvent event,User user){
        if(LessonRepo.timetableIsExistsByUser(user)){
            toTimeTableEdit(event,user);
            return;
        }
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(SciencesController.class.getResource("main.fxml"));
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MainController.setWeekCount(0);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root,1000,700));
        stage.setMinHeight(700);
        stage.setMinWidth(1000);
        stage.setTitle("Расписание");
        stage.show();
    }
    public static void updateMain(ActionEvent event){
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(SciencesController.class.getResource("main.fxml"));
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        double wight = stage.getWidth() - 16;
        double height = stage.getHeight() - 39;
        stage.setScene(new Scene(root,wight,height));
        stage.setTitle("Расписание");
        stage.show();
    }

    public static void toAddLesson(ActionEvent event){
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(SciencesController.class.getResource("build-lesson.fxml"));
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BuildLessonController.setEventTimetable(event);
        Stage stage = new Stage();
        stage.setScene(new Scene(root,600,450));
        stage.setTitle("Новая пара");
        stage.show();
    }
    public static void closeThis(ActionEvent event){
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

}
