package com.example.organizer;


import com.example.organizer.CustomView.LessonSmallView;
import com.example.organizer.Repositories.LessonRepo;
import com.example.organizer.model.Lesson;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TimetableEditController implements Initializable {

    @FXML
    private Button butAdd;
    @FXML
    private Button butToMain;
    @FXML
    private VBox vb00;
    @FXML
    private VBox vb01;
    @FXML
    private VBox vb02;
    @FXML
    private VBox vb03;
    @FXML
    private VBox vb04;
    @FXML
    private VBox vb05;
    @FXML
    private VBox vb10;
    @FXML
    private VBox vb11;
    @FXML
    private VBox vb12;
    @FXML
    private VBox vb13;
    @FXML
    private VBox vb14;
    @FXML
    private VBox vb15;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setInfo();
        butAdd.setOnAction(SciencesController::toAddLesson);
        butToMain.setOnAction(event -> {
            SciencesController.toMain(event,SciencesController.getUser());
        });

    }

    public void setInfo(){
        VBox[][] vBoxes = {
                {vb00,vb01,vb02,vb03,vb04,vb05},
                {vb10,vb11,vb12,vb13,vb14,vb15}
        };
        ArrayList<Lesson> lessonList = LessonRepo.getAllLessonsByUser(SciencesController.user.getId());
        for (Lesson lesson : lessonList) {
            if (lesson.getNumberOfWeek() < 2){
                vBoxes[lesson.getNumberOfWeek()][lesson.getDayOfWeek()].getChildren().add(new LessonSmallView(lesson));
                vBoxes[lesson.getNumberOfWeek()][lesson.getDayOfWeek()].setSpacing(5);
            }else {
                vBoxes[0][lesson.getDayOfWeek()].getChildren().add(new LessonSmallView(lesson));
                vBoxes[0][lesson.getDayOfWeek()].setSpacing(5);
                vBoxes[1][lesson.getDayOfWeek()].getChildren().add(new LessonSmallView(lesson));
                vBoxes[1][lesson.getDayOfWeek()].setSpacing(5);
            }
        }
    }

}
