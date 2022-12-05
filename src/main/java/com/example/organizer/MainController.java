package com.example.organizer;

import com.example.organizer.CustomView.LessonNanoView;
import com.example.organizer.CustomView.ReminderView;
import com.example.organizer.Repositories.ReminderRepo;
import com.example.organizer.Service.LessonService;
import com.example.organizer.model.Lesson;
import com.example.organizer.model.Reminder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private static int weekCount;
    @FXML
    private Button butBack;
    @FXML
    private Button butClose;
    @FXML
    private Button butNext;
    @FXML
    private MenuItem mnTimeTable;
    @FXML
    private VBox vb0;
    @FXML
    private VBox vb1;
    @FXML
    private VBox vb2;
    @FXML
    private VBox vb3;
    @FXML
    private VBox vb4;
    @FXML
    private VBox vb5;
    @FXML
    private Button butAdd;
    @FXML
    private TextField twLessonName;
    @FXML
    private TextArea taQuest;
    @FXML
    private DatePicker dpDate;
    @FXML
    private ChoiceBox<String> cbSwitch;
    @FXML
    private ChoiceBox<String> cbSwitchSetting;
    @FXML
    private ChoiceBox<String> cbHours;
    @FXML
    private ChoiceBox<String> cbMinuts;
    @FXML
    private ChoiceBox<String> cbDayOfWeek;
    @FXML
    private Label lbSetting;
    @FXML
    private Label lbTime;
    @FXML
    private Label ldtime1;
    @FXML
    private Label lbDayOfWeek;
    @FXML
    private VBox vbReminders;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        butClose.setOnAction(SciencesController::closeThis);
        butClose.setDisable(true);
        butBack.setOnAction(event -> {
            weekCount--;
            SciencesController.updateMainByEvent(event);
        });
        butNext.setOnAction(event -> {
            weekCount++;
            SciencesController.updateMainByEvent(event);

        });
        mnTimeTable.setOnAction(event -> {
            SciencesController.toNewTimeTableEdit(SciencesController.getUser());
            butClose.setDisable(false);
            butClose.fire();
        });
        cbSwitch.setOnAction(event -> {
            disOrEnable();
        });

        cbSwitchSetting.setOnAction(event -> {
            disOrEnableDayOfWeek();
        });
        butAdd.setOnAction(event -> {
            formationReminder();
            SciencesController.updateMainByEvent(event);
        });
        setWeek();
        setDataOfNewReminder();
        setReminders();
    }
    private void formationReminder(){
        if(Objects.equals(twLessonName.getText(), "")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Введите название предмета");
            alert.show();
            return;
        }
        LocalDate localDate = dpDate.getValue();
        System.out.println(localDate);
        if(localDate == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Введите дату сдачи");
            alert.show();
            return;
        }
        if(Objects.equals(cbSwitch.getValue(), "ДА") && Objects.equals(cbSwitchSetting.getValue(), "Каждую неделю") && Objects.equals(cbDayOfWeek.getValue(), "Выбрать")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Введите день недели когда вам напоминать");
            alert.show();
            return;
        }
        Reminder reminder;
        if(Objects.equals(cbSwitch.getValue(), "НЕТ")){
            reminder = new Reminder(twLessonName.getText(),taQuest.getText(), localDate.toString(), false);
        }else if (Objects.equals(cbSwitchSetting.getValue(), "Каждую неделю")){
            reminder = new Reminder(twLessonName.getText(),taQuest.getText(), localDate.toString(), true,cbSwitchSetting.getValue(),cbHours.getValue()+":"+cbMinuts.getValue(),cbDayOfWeek.getValue());
        }else {
            reminder = new Reminder(twLessonName.getText(),taQuest.getText(), localDate.toString(), true,cbSwitchSetting.getValue(),cbHours.getValue()+":"+cbMinuts.getValue(),null);
        }
        if(ReminderRepo.reminderTableIsExistsByUser(SciencesController.getUser())){
            ReminderRepo.createReminderTableByUser(SciencesController.getUser());
        }
        ReminderRepo.addReminderByIdUser(reminder,SciencesController.user.getId());
        setDataOfNewReminder();
    }
    private void setReminders(){
        if(ReminderRepo.reminderTableIsExistsByUser(SciencesController.getUser())){
            ReminderRepo.createReminderTableByUser(SciencesController.getUser());
        }
        ArrayList<Reminder> reminderArrayList = null;

        reminderArrayList = ReminderRepo.getAllRemindersByUser(SciencesController.user.getId());

        for (Reminder reminder : reminderArrayList) {
            ReminderView customControl = new ReminderView(reminder);
            vbReminders.getChildren().add(customControl);
        }

    }
    private void setDataOfNewReminder(){
        ReminderEditController.setDataOfcb(twLessonName, taQuest, cbSwitch, cbSwitchSetting, cbHours, cbMinuts, cbDayOfWeek);
    }
    private void setWeek(){
        VBox[] vBoxes = {
                vb0,vb1,vb2,vb3,vb4,vb5
        };
        ArrayList<Lesson> lessonList = LessonService.getLessonsThisWeek(weekCount);
        for (Lesson lesson : lessonList) {
                vBoxes[lesson.getDayOfWeek()].getChildren().add(new LessonNanoView(lesson));
                vBoxes[lesson.getDayOfWeek()].setSpacing(2);
        }
    }

    public static void setWeekCount(int weekCount) {
        MainController.weekCount = weekCount;
    }
    private void disOrEnableDayOfWeek(){
        ReminderEditController.disOrEnableDayOfWeek(cbSwitchSetting, cbDayOfWeek, lbDayOfWeek);
    }
    private void disOrEnable(){
        ReminderEditController.disOrEnable(cbSwitch, lbSetting, lbDayOfWeek, ldtime1, lbTime, cbSwitchSetting, cbHours, cbMinuts, cbDayOfWeek);
    }
}
