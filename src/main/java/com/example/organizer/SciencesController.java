package com.example.organizer;

import com.example.organizer.Repositories.LessonRepo;
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
        Parent root = null;
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
        Parent root = null;
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
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(SciencesController.class.getResource("timetable-edit.fxml"));
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(!LessonRepo.timetableIsExistsByUser(user)){
            LessonRepo.createTimetableByUser(user);
        }
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root,1000,700));
        stage.setTitle("Расписание");
        stage.show();
    }
    public static void toNewTimeTableEdit( User user){
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(SciencesController.class.getResource("timetable-edit.fxml"));
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(!LessonRepo.timetableIsExistsByUser(user)){
            LessonRepo.createTimetableByUser(user);
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root,1000,700));
        stage.setTitle("Расписание");
        stage.show();
    }
    public static void toMain(ActionEvent event,User user){
        if(!LessonRepo.timetableIsExistsByUser(user)){
            toTimeTableEdit(event,user);
            return;
        }
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(SciencesController.class.getResource("main.fxml"));
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MainController.setWeekCount(0);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root,1000,700));
        stage.setTitle("Расписание");
        stage.show();
    }
    public static void updateMain(ActionEvent event,User user){
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(SciencesController.class.getResource("main.fxml"));
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        double wight = stage.getWidth();
        double height = stage.getHeight();
        stage.setScene(new Scene(root,wight,height));
        stage.setTitle("Расписание");
        stage.show();
    }

    public static void toAddLesson(ActionEvent event){
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(SciencesController.class.getResource("build-lesson.fxml"));
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BuildLessonController.setEventTimetable(event);
        Stage stage = new Stage();
        stage.setScene(new Scene(root,600,400));
        stage.setTitle("Новая пара");
        stage.show();
    }
    public static void closeThis(ActionEvent event){
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

}
