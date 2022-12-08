package com.example.organizer.Controller;

import com.example.organizer.Const;
import com.example.organizer.Repositories.LessonRepo;
import com.example.organizer.Controller.SciencesController;
import com.example.organizer.model.Lesson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class BuildLessonController implements Initializable {
    private static ActionEvent eventTimetable;
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

    public static void setEventTimetable(ActionEvent eventTimetable) {
        BuildLessonController.eventTimetable = eventTimetable;
    }

    static void settingsChoiceBoxes(ChoiceBox<String> cbNumberOfWeek, ChoiceBox<String> cbDayOfWeek, ChoiceBox<String> cbType) {
        String[] array = Const.CHOICE_BOX_NUMBER_OF_WEEK;
        cbNumberOfWeek.getItems().addAll(array);
        cbNumberOfWeek.setStyle("-fx-font-style: Arial Rounded MT Bold");
        cbNumberOfWeek.setStyle("-fx-font-size: 18");
        cbNumberOfWeek.setValue(Const.DEFAULT_VALUE_CHOICE_BOX);

        array = Const.CHOICE_BOX_SIX_DAYS_OF_WEEK;
        cbDayOfWeek.getItems().addAll(array);
        cbDayOfWeek.setStyle("-fx-font-style: Arial Rounded MT Bold");
        cbDayOfWeek.setStyle("-fx-font-size: 18");
        cbDayOfWeek.setValue(Const.DEFAULT_VALUE_CHOICE_BOX);

        array = Const.TYPE_OF_LESSON;
        cbType.getItems().addAll(array);
        cbType.setStyle("-fx-font-style: Arial Rounded MT Bold");
        cbType.setStyle("-fx-font-size: 18");
        cbType.setValue(Const.DEFAULT_VALUE_CHOICE_BOX);
    }

    public static boolean errorChecking(TextField twName, TextField twTeacher, TextField twRoom, ChoiceBox<String> cbDayOfWeek, ChoiceBox<String> cbNumberOfWeek, ChoiceBox<String> cbType) {
        return !(!Objects.equals(twName.getText(), "") ||
                !Objects.equals(twTeacher.getText(), "") ||
                !Objects.equals(twRoom.getText(), "") ||
                !Objects.equals(cbDayOfWeek.getValue(), Const.DEFAULT_VALUE_CHOICE_BOX) ||
                !Objects.equals(cbNumberOfWeek.getValue(), Const.DEFAULT_VALUE_CHOICE_BOX) ||
                !Objects.equals(cbType.getValue(), Const.DEFAULT_VALUE_CHOICE_BOX));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        settingsChoiceBoxes(cbNumberOfWeek, cbDayOfWeek, cbType);
        butClose.setOnAction(SciencesController::closeThis);
        butSave.setOnAction(event -> {
            if (errorChecking(twName, twTeacher, twRoom, cbDayOfWeek, cbNumberOfWeek, cbType)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(Const.MESSAGE_ERROR_NOT_ALL_DATA);
                alert.show();
                return;
            }
            Lesson lesson = new Lesson(twName.getText(), twTeacher.getText(), twRoom.getText(), cbType.getValue(), -1, -1);
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
            LessonRepo.addLessonByIdUser(lesson, SciencesController.getUser().getId());
            SciencesController.toTimeTableEdit(eventTimetable, SciencesController.getUser());
            SciencesController.closeThis(event);
        });

    }
}
