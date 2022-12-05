package com.example.organizer;

import com.example.organizer.Repositories.ReminderRepo;
import com.example.organizer.Service.ReminderService;
import com.example.organizer.model.Reminder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;


public class ReminderEditController implements Initializable {
    private static Stage mainStage;
    public static void setEventTimetable(Stage stage) {
        ReminderEditController.mainStage = stage;
    }
    @FXML
    private Button butSave;
    @FXML
    private Button butClose;
    @FXML
    private Button butDel;
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
    private Reminder reminder;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbSwitch.setOnAction(event -> {
            disOrEnable();
        });
        cbSwitchSetting.setOnAction(event -> {
            disOrEnableDayOfWeek();
        });
        butSave.setOnAction(event -> {
            if(formationReminder()){
                SciencesController.closeThis(event);
            }
        });
        butClose.setOnAction(SciencesController::closeThis);
        butDel.setOnAction(event -> {
            ReminderRepo.deleteReminderById(SciencesController.getUser(),reminder);
            SciencesController.updateMainByStage(ReminderEditController.mainStage);
            butClose.fire();
        });

    }
    private void disOrEnableDayOfWeek(){
        disOrEnableDayOfWeek(cbSwitchSetting, cbDayOfWeek, lbDayOfWeek);
    }
    private boolean formationReminder(){
        if(Objects.equals(twLessonName.getText(), "")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Введите название предмета");
            alert.show();
            return false;
        }
        LocalDate localDate = dpDate.getValue();
        System.out.println(localDate);
        if(localDate == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Введите дату сдачи");
            alert.show();
            return false;
        }
        if(Objects.equals(cbSwitch.getValue(), "ДА") && Objects.equals(cbSwitchSetting.getValue(), "Каждую неделю") && Objects.equals(cbDayOfWeek.getValue(), "Выбрать")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Введите день недели когда вам напоминать");
            alert.show();
            return false;
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
        ReminderRepo.updateRminderById(reminder,this.reminder.getId(),SciencesController.getUser().getId());
        SciencesController.updateMainByStage(ReminderEditController.mainStage);
        return true;
    }
    static void disOrEnableDayOfWeek(ChoiceBox<String> cbSwitchSetting, ChoiceBox<String> cbDayOfWeek, Label lbDayOfWeek) {
        if(Objects.equals(cbSwitchSetting.getValue(), "Каждый день")){
            cbDayOfWeek.setVisible(false);
            cbDayOfWeek.setDisable(true);
            lbDayOfWeek.setVisible(false);
        }else {
            cbDayOfWeek.setVisible(true);
            cbDayOfWeek.setDisable(false);
            lbDayOfWeek.setVisible(true);
        }
    }

    private void disOrEnable(){
        disOrEnable(cbSwitch, lbSetting, lbDayOfWeek, ldtime1, lbTime, cbSwitchSetting, cbHours, cbMinuts, cbDayOfWeek);
    }
    public void setInfo(Reminder reminder){
        setDataOfNewReminder();
        this.reminder = reminder;
        twLessonName.setText(reminder.getLessonName());
        dpDate.setValue(ReminderService.getLocalDateByString(reminder.getDate()));
        taQuest.setText(reminder.getQuest());
        if (reminder.isSwitchR()) {
            cbSwitch.setValue("ДА");
        }else {
            cbSwitch.setValue("НЕТ");
        }
        if (reminder.getSettingSwitch() == null){
            cbSwitchSetting.setValue("Каждую неделю");
        }else {
            cbSwitchSetting.setValue(reminder.getSettingSwitch());
        }
        cbHours.setValue(ReminderService.getHours(reminder.getTime()));
        cbMinuts.setValue(ReminderService.getMinutes(reminder.getTime()));
        if(reminder.getDatOfWeek() == null){
            cbDayOfWeek.setValue("Выбрать");
        }else {
            cbDayOfWeek.setValue(reminder.getDatOfWeek());
        }
    }
    private void setDataOfNewReminder(){
        setDataOfcb(twLessonName, taQuest, cbSwitch, cbSwitchSetting, cbHours, cbMinuts, cbDayOfWeek);
    }

    static void setDataOfcb(TextField twLessonName, TextArea taQuest, ChoiceBox<String> cbSwitch, ChoiceBox<String> cbSwitchSetting, ChoiceBox<String> cbHours, ChoiceBox<String> cbMinuts, ChoiceBox<String> cbDayOfWeek) {
        twLessonName.setText("");
        taQuest.setText("");
        String[] arrayItemsOfCb = {"ДА","НЕТ"};
        cbSwitch.getItems().addAll(arrayItemsOfCb);
        cbSwitch.setValue(arrayItemsOfCb[0]);

        arrayItemsOfCb = new String[]{"Каждый день", "Каждую неделю"};
        cbSwitchSetting.getItems().addAll(arrayItemsOfCb);
        cbSwitchSetting.setValue(arrayItemsOfCb[0]);

        for (int  i = 0; i < 24;i++){
            if(Integer.toString(i).length() < 1){
                cbHours.getItems().add("0"+i);
                continue;
            }
            cbHours.getItems().add(Integer.toString(i));
        }
        cbHours.setValue("00");
        for (int  i = 0; i < 60; i+=5){
            if(Integer.toString(i).length() < 1){
                cbMinuts.getItems().add("0"+i);
                continue;
            }
            cbMinuts.getItems().add(Integer.toString(i));
        }
        cbMinuts.setValue("00");
        arrayItemsOfCb = new String[]{"Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота","Воскресенье"};
        cbDayOfWeek.getItems().addAll(arrayItemsOfCb);
        cbDayOfWeek.setValue("Выбрать");
    }

    static void disOrEnable(ChoiceBox<String> cbSwitch, Label lbSetting, Label lbDayOfWeek, Label ldtime1, Label lbTime, ChoiceBox<String> cbSwitchSetting, ChoiceBox<String> cbHours, ChoiceBox<String> cbMinuts, ChoiceBox<String> cbDayOfWeek) {
        if(Objects.equals(cbSwitch.getValue(), "НЕТ")){
            lbSetting.setVisible(false);
            lbDayOfWeek.setVisible(false);
            ldtime1.setVisible(false);
            lbTime.setVisible(false);
            cbSwitchSetting.setVisible(false);
            cbSwitchSetting.setDisable(true);
            cbHours.setVisible(false);
            cbHours.setDisable(true);
            cbMinuts.setVisible(false);
            cbMinuts.setDisable(true);
            cbDayOfWeek.setVisible(false);
            cbDayOfWeek.setDisable(true);
        }else {
            lbSetting.setVisible(true);
            lbDayOfWeek.setVisible(true);
            ldtime1.setVisible(true);
            lbTime.setVisible(true);
            cbSwitchSetting.setVisible(true);
            cbSwitchSetting.setDisable(false);
            cbHours.setVisible(true);
            cbHours.setDisable(false);
            cbMinuts.setVisible(true);
            cbMinuts.setDisable(false);
            cbDayOfWeek.setVisible(true);
            cbDayOfWeek.setDisable(false);
        }
    }
}
