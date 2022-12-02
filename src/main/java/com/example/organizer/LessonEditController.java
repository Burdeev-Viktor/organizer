package com.example.organizer;

import com.example.organizer.Repositories.LessonRepo;
import com.example.organizer.model.Lesson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LessonEditController implements Initializable {
    private static Stage tableStage;
    public static void setEventTimetable(Stage stage) {
        LessonEditController.tableStage = stage;
    }
    @FXML
    private Button butClose;
    @FXML
    private Button butSave;
    @FXML
    private TextField twName;
    @FXML
    private TextField twTeacher;
    @FXML
    private TextField twRoom;
    @FXML
    private ChoiceBox<String> cbType;
    @FXML
    private ChoiceBox<String> cbDayOfWeek;
    @FXML
    private ChoiceBox<String> cbNumberOfWeek;

    private Lesson lesson;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        butClose.setOnAction(SciencesController::closeThis);
        butSave.setOnAction(event -> {
            if(
                    !(!Objects.equals(twName.getText(), "") ||
                            !Objects.equals(twTeacher.getText(), "") ||
                            !Objects.equals(twRoom.getText(), "") ||
                            !Objects.equals(cbDayOfWeek.getValue(), "Выбрать") ||
                            !Objects.equals(cbNumberOfWeek.getValue(), "Выбрать") ||
                            !Objects.equals(cbType.getValue(), "Выбрать"))
            ){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Введите все данные!");
                alert.show();
                return;
            }
            Lesson lesson = new Lesson(twName.getText(),twTeacher.getText(),twRoom.getText(),cbType.getValue(),-1,-1);
            switch (cbDayOfWeek.getValue()) {
                case "Понедельник" -> lesson.setDayOfWeek(0);
                case "Вторник" -> lesson.setDayOfWeek(1);
                case "Среда" -> lesson.setDayOfWeek(2);
                case "Четверг" -> lesson.setDayOfWeek(3);
                case "Пятница" -> lesson.setDayOfWeek(4);
                case "Суббота" -> lesson.setDayOfWeek(5);
            }
            switch (cbNumberOfWeek.getValue()) {
                case "Первая" -> lesson.setNumberOfWeek(0);
                case "Вторая" -> lesson.setNumberOfWeek(1);
                case "Каждую" -> lesson.setNumberOfWeek(2);
            }
            LessonRepo.updateLessonById(lesson, this.lesson.getId(), SciencesController.getUser().getId());
            SciencesController.updateTimeTableEdit(SciencesController.getUser(),tableStage);
            SciencesController.closeThis(event);
        });
    }

    public void setInfo(Lesson lesson){
        this.lesson = lesson;
        String[] array = new String[]{"Первая","Вторая","Каждую"};
        cbNumberOfWeek.getItems().addAll(array);
        cbNumberOfWeek.setStyle("-fx-font-style: Arial Rounded MT Bold");
        cbNumberOfWeek.setStyle("-fx-font-size: 18");
        cbNumberOfWeek.setValue("Выбрать");

        array = new String[]{"Понедельник","Вторник","Среда","Четверг","Пятница","Суббота"};
        cbDayOfWeek.getItems().addAll(array);
        cbDayOfWeek.setStyle("-fx-font-style: Arial Rounded MT Bold");
        cbDayOfWeek.setStyle("-fx-font-size: 18");

        array = new String[]{"Лекция","Лабораторная","Практика","Консультация"};
        cbType.getItems().addAll(array);
        cbType.setStyle("-fx-font-style: Arial Rounded MT Bold");
        cbType.setStyle("-fx-font-size: 18");

        twName.setText(lesson.getName());
        twTeacher.setText(lesson.getTeacher());
        twRoom.setText(lesson.getRoom());
        cbType.setValue(lesson.getType());
        switch (lesson.getDayOfWeek()) {
            case 0 -> {
                cbDayOfWeek.setValue("Понедельник");
                break;
            }
            case 1 -> {
                cbDayOfWeek.setValue("Вторник");
                break;
            }
            case 2 -> {
                cbDayOfWeek.setValue("Среда");
                break;
            }
            case 3 -> {
                cbDayOfWeek.setValue("Четверг");
                break;
            }
            case 4 -> {
                cbDayOfWeek.setValue("Пятница");
                break;
            }
            case 5 -> {
                cbDayOfWeek.setValue("Суббота");
                break;
            }

        }
        switch (lesson.getNumberOfWeek()) {
            case 0 -> {
                cbNumberOfWeek.setValue("Первая");
                break;
            }
            case 1 -> {
                cbNumberOfWeek.setValue("Вторая");
                break;
            }
            case 2 -> {
                cbNumberOfWeek.setValue("Каждую");
                break;
            }
        }
    }
}
