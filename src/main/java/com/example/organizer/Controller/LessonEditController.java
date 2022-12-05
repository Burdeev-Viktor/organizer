package com.example.organizer.Controller;

import com.example.organizer.Const;
import com.example.organizer.Repositories.LessonRepo;
import com.example.organizer.model.Lesson;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.organizer.Controller.BuildLessonController.errorChecking;
import static com.example.organizer.Controller.BuildLessonController.settingsChoiceBoxes;

public class LessonEditController implements Initializable {
    private static Stage tableStage;
    @FXML
    private Button butClose;
    @FXML
    private Button butDel;
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

    public static void setEventTimetable(Stage stage) {
        LessonEditController.tableStage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
            LessonRepo.updateLessonById(lesson, this.lesson.getId(), SciencesController.getUser().getId());
            SciencesController.updateTimeTableEdit(SciencesController.getUser(), tableStage);
            SciencesController.closeThis(event);
        });
        butDel.setOnAction(event -> {
            LessonRepo.deleteLessonById(SciencesController.getUser(), this.lesson);
            SciencesController.updateTimeTableEdit(SciencesController.getUser(), tableStage);
            SciencesController.closeThis(event);
        });
    }

    public void setInfo(Lesson lesson) {
        this.lesson = lesson;
        settingsChoiceBoxes(cbNumberOfWeek, cbDayOfWeek, cbType);
        twName.setText(lesson.getName());
        twTeacher.setText(lesson.getTeacher());
        twRoom.setText(lesson.getRoom());
        cbType.setValue(lesson.getType());
        switch (lesson.getDayOfWeek()) {
            case 0 -> cbDayOfWeek.setValue(Const.CHOICE_BOX_SIX_DAYS_OF_WEEK[0]);
            case 1 -> cbDayOfWeek.setValue(Const.CHOICE_BOX_SIX_DAYS_OF_WEEK[1]);
            case 2 -> cbDayOfWeek.setValue(Const.CHOICE_BOX_SIX_DAYS_OF_WEEK[2]);
            case 3 -> cbDayOfWeek.setValue(Const.CHOICE_BOX_SIX_DAYS_OF_WEEK[3]);
            case 4 -> cbDayOfWeek.setValue(Const.CHOICE_BOX_SIX_DAYS_OF_WEEK[4]);
            case 5 -> cbDayOfWeek.setValue(Const.CHOICE_BOX_SIX_DAYS_OF_WEEK[5]);
        }
        switch (lesson.getNumberOfWeek()) {
            case 0 -> cbNumberOfWeek.setValue(Const.CHOICE_BOX_NUMBER_OF_WEEK[0]);
            case 1 -> cbNumberOfWeek.setValue(Const.CHOICE_BOX_NUMBER_OF_WEEK[1]);
            case 2 -> cbNumberOfWeek.setValue(Const.CHOICE_BOX_NUMBER_OF_WEEK[2]);
        }
    }
}
