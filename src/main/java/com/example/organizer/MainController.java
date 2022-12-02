package com.example.organizer;

import com.example.organizer.CustomView.LessonNanoView;
import com.example.organizer.CustomView.LessonSmallView;
import com.example.organizer.Repositories.LessonRepo;
import com.example.organizer.Service.LessonService;
import com.example.organizer.model.Lesson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
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
    private MenuBar mnBar;
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        butClose.setOnAction(SciencesController::closeThis);
        butClose.setDisable(true);
        butBack.setOnAction(event -> {
            weekCount--;
            SciencesController.updateMain(event,SciencesController.getUser());
        });
        butNext.setOnAction(event -> {
            weekCount++;
            SciencesController.updateMain(event,SciencesController.getUser());

        });
        mnTimeTable.setOnAction(event -> {
            SciencesController.toNewTimeTableEdit(SciencesController.getUser());
            butClose.setDisable(false);
            butClose.fire();
        });
        setWeek();

    }
    public void setWeek(){
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
}
